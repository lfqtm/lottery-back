package com.sn.ticket.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sn.ticket.entity.LotteryDraw;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sn.ticket.dto.PagerModel;

/**
* @author qlk
* @description 针对表【tb_lottery_draw】的数据库操作Service
* @createDate 2023-04-29 19:41:19
*/
public interface LotteryDrawService extends IService<LotteryDraw> {

    PagerModel<LotteryDraw> listPage(Page<LotteryDraw> page, QueryWrapper<LotteryDraw> queryWrapper);
}
