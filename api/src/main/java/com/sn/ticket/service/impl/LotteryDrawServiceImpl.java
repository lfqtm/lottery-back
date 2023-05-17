package com.sn.ticket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sn.ticket.entity.LotteryDraw;
import com.sn.ticket.dto.PagerModel;
import com.sn.ticket.service.LotteryDrawService;
import com.sn.ticket.mapper.LotteryDrawMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author qlk
* @description 针对表【tb_lottery_draw】的数据库操作Service实现
* @createDate 2023-04-29 19:41:19
*/
@Service
public class LotteryDrawServiceImpl extends ServiceImpl<LotteryDrawMapper, LotteryDraw>
    implements LotteryDrawService{

    @Resource
    private LotteryDrawMapper lotteryDrawMapper;

    @Override
    public PagerModel<LotteryDraw> listPage(Page<LotteryDraw> page, QueryWrapper<LotteryDraw> queryWrapper) {
        Page<LotteryDraw> lotteryIPage = lotteryDrawMapper.selectPage(page, queryWrapper);
        List<LotteryDraw> records = lotteryIPage.getRecords();
        return new PagerModel<>(lotteryIPage.getTotal(), records);
    }
}




