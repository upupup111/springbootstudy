package com.study.bicycle.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
public class User extends BaseEntity implements Serializable{
    /**
     * serialVersionUID 序列化版本控制id
     */
    private static final long serialVersionUID = 1L;

    public static final Integer ADMIN = 1;

    public static final Integer USER = 0;

    private String phone;//手机号
    private String password;//密码
    private String name;//姓名
    private Integer age;//年龄
    private Integer gender;//性别(0:男,1:女)
    private String idCard;//身份证
    private Integer level;//级别(0:普通用户,1:管理员)

    @Override
    public String toString() {
        return "User{" +
                "phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", idCard='" + idCard + '\'' +
                ", level=" + level +
                ", id=" + id +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(password, user.password) &&
                Objects.equals(name, user.name) &&
                Objects.equals(age, user.age) &&
                Objects.equals(gender, user.gender) &&
                Objects.equals(idCard, user.idCard) &&
                Objects.equals(level, user.level) &&
                Objects.equals(createTime, user.createTime) &&
                Objects.equals(updateTime, user.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phone, password, name, age, gender, idCard, level, createTime, updateTime);
    }
}
