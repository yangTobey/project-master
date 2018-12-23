package com.spring.boot.entity;


import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

/**
 * Created by Yang on 2018/12/22.
 */
public class UserEntity {
    /**
     * 登录账号
     */
    //@NotNull(message = "登录账号不能为空！")
    @Size(min = 6,max = 18,message = "登录账号长度不符合要求！")
    @Pattern(regexp = "([A-Z]|[a-z]|[0-9]){0,18}",message = "登录账号不能为空，或者存在非法字符")
    private String account;

    /**
     * 登陆密码
     */
    //@NotNull(message = "登录密码不能为空")
    @Size(min = 6,max = 18,message = "登录账号长度不符合要求！")
    @Pattern(regexp = "([A-Z]|[a-z]|[0-9]){0,18}",message = "登录密码不能为空，或者存在非法字符")
    private String password;

    /**
     * 所属公司id
     */
    @NotNull(message = "公司id格式不正确，或者不符合常理！！！")
   /* @Min(value = 0,message = "最小为0")
    @Max(value = Integer.MAX_VALUE,message = "公司id过大，不合理")*/
    @Range(min=1, max=Integer.MAX_VALUE, message = "公司id格式错误")
    private Long companyId;

    /**
     * 权限id组合
     */
    @NotNull(message = "角色id格式不正确，或者不符合常理！！！")
    private String roleIds;
    /**
     * 所属部门id
     */
    @NotNull(message = "部门id格式不正确，或者不符合常理！！！")
   /* @Min(value = 0,message = "最小为0")
    @Max(value = Integer.MAX_VALUE,message = "部门id过大，不合理")*/
    @Range(min=1, max=Integer.MAX_VALUE, message = "部门id格式错误")
    private Long departmentId;

    /**
     * 用户名
     */
    @NotNull(message = "用户名称不能为空！！")
    private String userName;

    /**
     * 管辖分公司id组合
     */
    @NotNull(message = "权限公司不能为空！！")
    private String permsCompanyId;
}
