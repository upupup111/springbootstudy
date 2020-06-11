package com.study.bicycle.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
public class Bicycle extends BaseEntity implements Serializable {
    /**
     * serialVersionUID 序列化版本控制id
     */
    private static final long serialVersionUID = 1L;
    /**
     * 已租
     */
    public static final Integer RENT = 1;
    /**
     * 未租
     */
    public static final Integer NOT_RENT = 0;
    /**
     * 已删除
     */
    public static final Integer DELETE = 1;
    /**
     * 未删除
     */
    public static final Integer NOT_DELETE = 0;
    private String color;// 单车颜色
    private Integer isRent;// 是否租赁
    @ManyToOne
    private User addUser;// 添加人
    @NotBlank(message = "单位编码不能为空")
    private String bicycleNo;
    @ManyToOne
    private User rentUser;//租用人

    private Integer isDelete = 0;//是否删除(0:未删除,1:删除)
    @Override
    public String toString() {
        return "Bicycle{" +
                "color='" + color + '\'' +
                ", isRent=" + isRent +
                ", addUser=" + addUser +
                ", bicycleNo='" + bicycleNo + '\'' +
                ", id=" + id +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bicycle)) return false;
        if (!super.equals(o)) return false;
        Bicycle bicycle = (Bicycle) o;
        return Objects.equals(color, bicycle.color) &&
                Objects.equals(isRent, bicycle.isRent) &&
                Objects.equals(addUser, bicycle.addUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), color, isRent, addUser);
    }
}
