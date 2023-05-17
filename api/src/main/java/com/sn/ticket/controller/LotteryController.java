package com.sn.ticket.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sn.ticket.entity.LotteryDraw;
import com.sn.ticket.dto.PagerModel;
import com.sn.ticket.dto.R;
import com.sn.ticket.service.LotteryDrawService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/lottery")
public class LotteryController {

    @Resource
    private LotteryDrawService lotteryDrawService;

    @GetMapping("/hello")
    public String hello() {
        return "hello lottery";
    }

    @GetMapping("/get7a1")
    public R<PagerModel<LotteryDraw>> listPage2(@RequestParam(value = "current", defaultValue = "1") Integer pageNum,
                                                @RequestParam(value = "size", defaultValue = "100") Integer pageSize) {
        // 条件构造器
        QueryWrapper<LotteryDraw> queryWrapper = new QueryWrapper<>();
        // 分页插件
        Page<LotteryDraw> page = new Page<>(pageNum, pageSize);
        // 查询数据
        return R.ok(lotteryDrawService.listPage(page, queryWrapper));
    }
}
