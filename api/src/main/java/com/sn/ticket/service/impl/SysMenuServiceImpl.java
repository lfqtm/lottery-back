package com.sn.ticket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sn.ticket.entity.SysMenu;
import com.sn.ticket.mapper.SysMenuMapper;
import com.sn.ticket.service.SysMenuService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>
implements SysMenuService {

    //生成树形菜单
    public List<SysMenu> buildTreeMenu(List<SysMenu> sysMenuList) {
        List<SysMenu> resultMenuList=new ArrayList<>();

        for (SysMenu sysMenu : sysMenuList) {
            for (SysMenu e : sysMenuList) {
                //寻找子节点
                if(e.getParentId().equals(sysMenu.getId())){
                    sysMenu.getChildren().add(e);
                }
            }
            if(sysMenu.getParentId()==0L){
                resultMenuList.add(sysMenu);
            }
        }
        return resultMenuList;
    }
}




