package com.study.bicycle.controller;

import com.study.bicycle.controller.ex.FileOutException;
import com.study.bicycle.entity.Bicycle;
import com.study.bicycle.entity.User;
import com.study.bicycle.service.IUserService;
import com.study.bicycle.service.IbicycleService;
import com.study.bicycle.util.JsonResult;
import com.study.bicycle.util.PoiUtils;
import org.apache.tomcat.util.net.NioChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("bicycle")
public class BicycleController extends BaseController{
    @Autowired
    private IbicycleService bicycleService;
    @Autowired
    private IUserService userService;
    @GetMapping("list")
    public JsonResult<List<Bicycle>> list(){
        log.info("#########################");
        log.info("########单车列表########");
        log.info("#########################");
        List<Bicycle> list =  bicycleService.findAll();
        return new JsonResult<>(list);
    }
    @GetMapping("rentover")
    public JsonResult<List<Bicycle>> rentOver(Integer userId){
        log.info("#########################");
        log.info("########单车已租列表########");
        log.info("#########################");
        List<Bicycle> list =  bicycleService.findByRent(userId);
        return new JsonResult<>(list);
    }
    @GetMapping("notrent")
    public JsonResult<List<Bicycle>> notRent(){
        log.info("#########################");
        log.info("########单车未租列表########");
        log.info("#########################");
        List<Bicycle> list =  bicycleService.findByNoRent();
        return new JsonResult<>(list);
    }
    @PostMapping("rent")
    public JsonResult<Void> rent(Integer id, Integer userId){
        log.info("#########################");
        log.info("########租用单车########");
        log.info("#########################");
        log.info("id={},userId={}",id,userId);
        bicycleService.rentBicycle(id,userId);
        return new JsonResult<>();
    }
    @PostMapping("returnCar")
    public JsonResult<Void> returnCar(Integer id,String money){
        log.info("#########################");
        log.info("########退还单车########");
        log.info("#########################");
        log.info("id={},money={}",id,money);
        bicycleService.returnBicycle(id,money);
        return new JsonResult<>();
    }
    @PostMapping("scrap")
    public JsonResult<Void> scrap(Integer id){
        log.info("#########################");
        log.info("########报废单车########");
        log.info("#########################");
        log.info("id={}",id);
        bicycleService.scrapBicycle(id);
        return new JsonResult<>();
    }
    @PostMapping("add")
    public JsonResult<Bicycle> add(@Valid Bicycle bicycle, Integer userId){
        log.info("#########################");
        log.info("########添加单车########");
        log.info("#########################");
        log.info("bicycle={},userId={}",bicycle,userId);
        bicycleService.addBicycle(bicycle,userId);
        return new JsonResult<>();
    }
    @GetMapping("isExist")
    public JsonResult<Boolean> isExist(String no){
        log.info("#########################");
        log.info("########是否重复编号########");
        log.info("#########################");
        log.info("no ={}",no);
        Boolean isExist = bicycleService.findByNo(no);
        return new JsonResult<>(isExist);
    }
    @GetMapping("money/{bicycleNo}")
    public JsonResult<String> money(@PathVariable String bicycleNo){
        log.info("#########################");
        log.info("########租金的价格########");
        log.info("#########################");
        log.info("bicycleNo ={}",bicycleNo);
        String money = bicycleService.getMoney(bicycleNo);
        return new JsonResult<>(money);
    }
    @PostMapping("import")
    public JsonResult<Integer> importFile(@RequestBody List<Bicycle> list) {
        log.info("#########################");
        log.info("########导入单车表########");
        log.info("#########################");
        log.info("单车表信息list={}" ,list);
        Integer row = bicycleService.createBicycleList(list);
        return new JsonResult<>(SUCCESS,row);
    }
    @PostMapping("import/preview")
    public JsonResult<List<Bicycle>> importPreview(@RequestPart("file") MultipartFile file,Integer id) {
        log.info("#########################");
        log.info("########导入单车预览########");
        log.info("#########################");
        log.info("originalFilename=" + file.getOriginalFilename() + ", size=" + file.getSize() + ", contentType=" + file.getContentType());
        User user = userService.findUserInfo(id);
        try {
            List<Bicycle> list = PoiUtils.parseFileList(file,user);
            log.info("单车表信息=" + list);
            return new JsonResult<>(list);
        } catch (IOException e) {
            throw new FileOutException("导入excel异常");
        }
   }
    @GetMapping("findByNoLike")
    public JsonResult<List<Bicycle>> findByNoLike(String bicycleNo){
        log.info("#########################");
        log.info("########模糊查询单车########");
        log.info("#########################");
        log.info("bicycleNo={}",bicycleNo);
        List<Bicycle> list = bicycleService.getBicycleLike("%"+bicycleNo+"%");
        return new JsonResult<>(list);
    }

    @GetMapping("findByNoLikeRent")
    public JsonResult<List<Bicycle>> findByNoLikeRent(String bicycleNo){
        log.info("#########################");
        log.info("########模糊查询已租单车########");
        log.info("#########################");
        log.info("bicycleNo={}",bicycleNo);
        List<Bicycle> list = bicycleService.getBicycleLikeRent(bicycleNo);
        return new JsonResult<>(list);
    }

    @GetMapping("findByNoLikeNoRent")
    public JsonResult<List<Bicycle>> findByNoLikeNoRent(String bicycleNo){
        log.info("#########################");
        log.info("########模糊查询未租单车########");
        log.info("#########################");
        log.info("bicycleNo={}",bicycleNo);
        List<Bicycle> list = bicycleService.getBicycleLikeNoRent(bicycleNo);
        return new JsonResult<>(list);
    }
}
