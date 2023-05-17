package com.sn.ticket.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName tb_lottery_draw
 */
@TableName(value ="tb_lottery_draw")
@Data
public class LotteryDraw implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer drawId;

    /**
     * 期号
     */
    private String drawNum;

    /**
     * 开奖日期
     */
    private String drawTime;

    /**
     * 开奖结果
     */
    private String drawResult;

    /**
     * 奖池奖金
     */
    private String drawPool;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}