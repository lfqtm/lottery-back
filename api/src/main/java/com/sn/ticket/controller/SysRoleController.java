package com.sn.ticket.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sn.ticket.entity.*;
import com.sn.ticket.service.SysRoleMenuService;
import com.sn.ticket.service.SysRoleService;
import com.sn.ticket.service.SysUserRoleService;
import com.sn.ticket.util.StringUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Api(tags = "角色管理")
@RestController
@RequestMapping("/sys/role")
public class SysRoleController {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    //查询所有角色
    @GetMapping("/listAll")
    @PreAuthorize("hasAuthority('system:role:query')")
    public R listAll(){
        Map<String,Object> resultMap=new HashMap<>();
        List<SysRole> roleList = sysRoleService.list();
        resultMap.put("roleList",roleList);
        return R.ok(resultMap);
    }

    //根据条件分页查询角色信息
    @PostMapping("/list")
    @PreAuthorize("hasAuthority('system:role:query')")
    public R list(@RequestBody PageBean pageBean){
        String query=pageBean.getQuery().trim();
        Page<SysRole> pageResult = sysRoleService.page(new Page<>(pageBean.getPageNum(), pageBean.getPageSize()),new QueryWrapper<SysRole>().like(StringUtil.isNotEmpty(query),"name",query));
        List<SysRole> userList = pageResult.getRecords();
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("roleList",userList);
        resultMap.put("total",pageResult.getTotal());
        return R.ok(resultMap);
    }

    //添加或者修改
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('system:role:add')"+"||"+"hasAuthority('system:role:edit')")    public R save(@RequestBody SysRole sysRole){
        if(sysRole.getId()==null || sysRole.getId()==-1){   //添加
            sysRoleService.save(sysRole);
        }else{  //修改
            sysRoleService.updateById(sysRole);
        }
        return R.ok();
    }

    //根据id查询
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('system:role:query')")
    public R findById(@PathVariable(value = "id")Integer id){
        SysRole sysRole = sysRoleService.getById(id);
        Map<String,Object> map=new HashMap<>();
        map.put("sysRole",sysRole);
        return R.ok(map);
    }

    //删除
    @Transactional
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('system:role:delete')")
    public R delete(@RequestBody Long[] ids){
        sysRoleService.removeByIds(Arrays.asList(ids));
        sysUserRoleService.remove(new QueryWrapper<SysUserRole>().in("role_id",ids));
        return R.ok();
    }

    //获取当前角色的权限菜单id集合
    @GetMapping("/menus/{id}")
    @PreAuthorize("hasAuthority('system:role:menu')")
    public R menus(@PathVariable(value ="id")Integer id){
        List<SysRoleMenu> roleMenuList = sysRoleMenuService.list(new QueryWrapper<SysRoleMenu>().eq("role_id", id));
        List<Long> menuIdList = roleMenuList.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());
        return R.ok().put("menuIdList",menuIdList);
    }

    //更新角色权限信息
    @Transactional
    @PostMapping("/updateMenus/{id}")
    @PreAuthorize("hasAuthority('system:role:menu')")
    public R updateMenus(@PathVariable(value ="id")Long id, @RequestBody Long[] menuIds){
        //先根据role_id删除role_menu中的信息
        sysRoleMenuService.remove(new QueryWrapper<SysRoleMenu>().eq("role_id",id));
        ArrayList<SysRoleMenu> sysRoleMenuList = new ArrayList<>();
        Arrays.stream(menuIds).forEach(menuId->{
            SysRoleMenu roleMenu = new SysRoleMenu();
            roleMenu.setRoleId(id);
            roleMenu.setMenuId(menuId);
            sysRoleMenuList.add(roleMenu);
        });
        sysRoleMenuService.saveBatch(sysRoleMenuList);
        return R.ok();
    }

}
