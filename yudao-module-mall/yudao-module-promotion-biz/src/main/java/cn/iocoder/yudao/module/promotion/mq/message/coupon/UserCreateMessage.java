package cn.iocoder.yudao.module.promotion.mq.message.coupon;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 会员用户创建消息
 *
 * @author owen
 */
@Data
public class UserCreateMessage {

    /**
     * 用户编号
     */
    @NotNull(message = "用户编号不能为空")
    private Long userId;

}
