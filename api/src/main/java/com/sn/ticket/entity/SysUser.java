package com.sn.ticket.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * @TableName sys_user
 */
@TableName(value ="sys_user")
@Data
public class SysUser extends BaseEntity implements Serializable {

    /**
     * 用户名
     */
    @TableField(value = "username")
    @NotBlank(message ="用户名不能为空!")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "password")
    @NotBlank(message ="密码不能为空!")
    private String password;

    /**
     * 用户头像
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 用户邮箱
     */
    @TableField(value = "email")
    @NotBlank(message ="邮箱不能为空!")
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     * 手机号码
     */
    @TableField(value = "phonenumber")
    @NotBlank(message ="手机号码不能为空!")
    private String phonenumber;

    /**
     * 最后登录时间
     */
    @TableField(value = "login_date")
    private Date loginDate;

    /**
     * 帐号状态（0正常 1停用）
     */
    @TableField(value = "status")
    private String status;

    /**
     * 所属角色：多个角色逗号隔开
     */
    @TableField(exist = false)
    private String roles;

    /**
     * 旧密码
     */
    @TableField(exist = false)
    private String oldPassword;

    /**
     * 确认新密码
     */
    @TableField(exist = false)
    private String newPassword;

    /**
     * 所有角色集合
     */
    @TableField(exist = false)
    private List<SysRole> sysRoleList;

    /**
     * 删除标记（0:可用 1:已删除）
     */
    @TableLogic  //逻辑删除 默认效果 0 没有删除 1 已经删除
    @TableField("is_deleted")
    private Integer isDeleted;


}