package com.study.bicycle.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
//
@MappedSuperclass
@Data
public class BaseEntity {

    //映射数据库表中的字段名
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    protected Integer id; //主键id

//    protected Date createTime;//创建时间
//    protected Date updateTime;//修改时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    protected Timestamp createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @UpdateTimestamp
    protected Timestamp updateTime;
}
