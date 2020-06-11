package com.study.bicycle.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Data
@Entity(name="rentcar_record")
public class RentRecord extends BaseEntity{
    public static final Integer BEING_USED = 1;
    public static final Integer NOT_USED = 2;
    @ManyToOne
    private User rentUser;//租用人
    //时间格式化注解
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp rentTime;//租用时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp stillTime;//还车时间
    @ManyToOne
    @JoinColumn(name = "bicycleNo", referencedColumnName = "bicycleNo", insertable = false, updatable = false)
    private Bicycle bicycle;//租车编号
    @Column
    private String bicycleNo;
    private Integer status;//租赁状态(1:正在租赁,2:退租完成)

    private String money;

    @Override
    public String toString() {
        return "RentRecord{" +
                "rentUser=" + rentUser +
                ", rentTime=" + rentTime +
                ", stillTime=" + stillTime +
                ", bicycle=" + bicycle +
                ", bicycleNo='" + bicycleNo + '\'' +
                ", status=" + status +
                ", money='" + money + '\'' +
                ", id=" + id +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
