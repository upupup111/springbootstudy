package com.study.bicycle.util;

import com.study.bicycle.controller.ex.EmptyListException;
import com.study.bicycle.entity.Bicycle;
import com.study.bicycle.entity.User;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PoiUtils {
    /**
     * 导出excel
     *
     * @param
     * @return
     * @throws IOException
     */
    public static void exportUserExcel(List<Bicycle> list, HttpServletResponse response) throws IOException {
        // 创建一个excel文件(.xls)
        HSSFWorkbook workbook = new HSSFWorkbook();

        HSSFSheet sheet = workbook.createSheet("单车列表");//表单名

        HSSFCellStyle style = workbook.createCellStyle();//设置样式
        style.setAlignment(HorizontalAlignment.CENTER);

        HSSFRow row = null;

        row = sheet.createRow(0);//创建第一行
        row.setHeight((short) (30 * 20));
        row.createCell(0).setCellValue("单车列表");//设置第一个单元格的值(标题)
        HSSFCell cell = row.getCell(0);
        cell.setCellStyle(style);

        CellRangeAddress rowRegion = new CellRangeAddress(0, 0, 0, 2);//第一行到第一行，第一列到第三列
        sheet.addMergedRegion(rowRegion);//单元格合并
        row = sheet.createRow(1);
        row.setHeight((short) (22.50 * 20));//设置行高
        row.createCell(0).setCellValue("Id");//为第一个单元格设值
        row.createCell(1).setCellValue("账号");//为第二个单元格设值
        row.createCell(2).setCellValue("密码");//为第三个单元格设值

        for (int i = 0; i < list.size(); i++) {//循环给列表赋值
            row = sheet.createRow(i + 2);
            Bicycle bicycle = list.get(i);
            row.createCell(0).setCellValue(bicycle.getId());
            row.createCell(1).setCellValue(bicycle.getBicycleNo());
            row.createCell(2).setCellValue(bicycle.getColor());
        }
        sheet.setDefaultRowHeight((short) (16.5 * 20));//设置默认行高值
        //列宽自适应
        for (int i = 0; i < 3; i++) {
            sheet.autoSizeColumn(i);//调整每一列宽度
            sheet.setColumnWidth(i, sheet.getColumnWidth(i) * 17 / 10);//解决自动设置列宽中文失效的问题
        }
        String title = "用户记录";
//        String fileName = new String(title.getBytes("UTF-8"),"ISO-8859-1");
        String fileName = URLEncoder.encode(title, "UTF-8");

        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");//设置响应正文类型
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");//默认名称
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Access-Control-Allow-Origin", "*");
        //格式化单元格日期信息
//        HSSFDataFormat dataFormat = workbook.createDataFormat();
//        short dataformat = dataFormat.getFormat("yyyy-MM-dd HH:mm:ss");

//        style.setDataFormat(dataformat);

        OutputStream os = response.getOutputStream();//获取输出流
        workbook.write(os);
        os.flush();
        os.close();
//        workbook.close();
    }

    /**
     * 导入excel
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static List<Bicycle> parseFileList(MultipartFile file, User user) throws IOException, EmptyListException {
        String filename = file.getOriginalFilename();
        if (filename == null) {
            throw new EmptyListException("请上传正确的Excel表");
        }
        List<Bicycle> bicycles = new ArrayList<>();
        boolean is2007 = filename.toLowerCase().endsWith(".xlsx");
        if (!is2007 && !filename.toLowerCase().endsWith(".xls")) {
            throw new EmptyListException("请上传正确的Excel表");
        }
        Workbook workBook = getWorkBook(file.getInputStream(), is2007);
        Sheet sheet = workBook.getSheetAt(0);
        for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row row = sheet.getRow(rowNum);//获取列的信息
            if (row.getLastCellNum() != 2) {
                throw new EmptyListException("请在第" + (rowNum + 1) + "行按照正确的格式书写数据");
            }
            bicycles.add(processBicycleRow(row,user));
        }

        return bicycles;
    }

    /**
     * 判断是那种类型的excel
     *
     * @param inputStream
     * @param is2007
     * @return
     * @throws IOException
     */
    private static Workbook getWorkBook(InputStream inputStream, boolean is2007) throws IOException {
        return is2007 ? new XSSFWorkbook(inputStream) : new HSSFWorkbook(inputStream);
    }

    /**
     * 解析excel的每一行
     *
     * @param row
     * @return
     */
    private static Bicycle processBicycleRow(Row row,User user) {
        Bicycle bicycle = new Bicycle();

        for (int i = 0; i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);//获取行的信息
            String value = getCellStringVal(cell);
            switch (i) {
                case 0:
                    bicycle.setBicycleNo(value);
                    break;
                case 1:
                    bicycle.setColor(value);
                    break;
            }
            bicycle.setAddUser(user);
        }

        return bicycle;
    }

    /**
     * 格式化数字，避免科学计数法
     */
    private static DecimalFormat decimalFormat = new DecimalFormat("0");

    /**
     * 获取每个单元格的值
     *
     * @param cell
     * @return
     */
    private static String getCellStringVal(Cell cell) {
        if (cell == null) {
            return "";
        }
        CellType cellType = cell.getCellTypeEnum();
        switch (cellType) {
            case NUMERIC:
                return decimalFormat.format(cell.getNumericCellValue());
            case STRING:
                return cell.getStringCellValue();
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            case ERROR:
                return String.valueOf(cell.getErrorCellValue());
            default:
                return "";
        }
    }
}
