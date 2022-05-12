package com.dee.studyadmin.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@Table
@NoArgsConstructor
public class Menu extends BaseEntity {

    @NotEmpty(message = "菜单名称不能为空！")
    String name;

    //默认url = 0
    String url = "0";

    @NotEmpty(message = "菜单编码不能为空！")
    String menuCode = "-1000";

    @NotNull(message = "父id不能为空！")
    Long parMenuId;

    @Transient
    String parMenuName;

    @Transient
    List<Menu> childMenus;

    //默认排序为0
    Long menuIndex = 0L;

    //默认为有效状态
    String status = "10";


    // 目录菜单：10，可点击跳转菜单：20， 按钮：30
    String menuType = "30";

    String icon;

    // 菜单等级
    Integer level;

    @Size(max = 255, message = "备注长度允许超过255个字符！")
    String description;

}
