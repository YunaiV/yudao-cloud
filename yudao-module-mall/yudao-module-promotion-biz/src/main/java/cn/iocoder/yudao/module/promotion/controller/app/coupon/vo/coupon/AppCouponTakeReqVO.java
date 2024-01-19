package cn.iocoder.yudao.module.promotion.controller.app.coupon.vo.coupon;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "用户 App - 优惠劵领取 Request VO")
@Data
public class AppCouponTakeReqVO {

    @Schema(description = "优惠劵模板编号", example = "1")
    @NotNull(message = "优惠劵模板编号不能为空")
    private Long templateId;

}
