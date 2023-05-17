package com.sn.ticket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sn.ticket.entity.SysMenu;

import java.util.List;

/**
 *
 */
public interface SysMenuService extends IService<SysMenu> {

    //生成树形菜单
    List<SysMenu> buildTreeMenu(List<SysMenu> sysMenuList);
}
