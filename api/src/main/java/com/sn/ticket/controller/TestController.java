package com.sn.ticket.controller;

import com.sn.ticket.entity.R;
import com.sn.ticket.entity.SysUser;
import com.sn.ticket.service.SysUserService;
import com.sn.ticket.util.JwtUtils;
import com.sn.ticket.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private SysUserService sysUserService;

    @GetMapping("/user/list")
//    @PreAuthorize("hasRole('ROLE_admin')")
    @PreAuthorize("hasAuthority('system:user:list')")
    public R userList(@RequestHeader(required = false) String token){
        log.info("token={}",token);
        if(StringUtil.isNotEmpty(token)){
            Map<String,Object> resutMap=new HashMap<>();
            List<SysUser> userList = sysUserService.list();
            resutMap.put("userList",userList);
            return R.ok(resutMap);
        }else{
            return R.error(401,"没有权限访问");
        }
    }

    @RequestMapping("/login")
    public R login(){
        String token = JwtUtils.genJwtToken("java1234");
        return R.ok().put("token",token);
    }

}
