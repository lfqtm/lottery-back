package com.sn.ticket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sn.ticket.entity.SysUser;

/**
 *
 */
public interface SysUserService extends IService<SysUser> {

    //根据用户名查询用户
    SysUser getByUsername(String username);

    //获取权限字符串
    String getUserAuthorityInfo(Long userId);

}
