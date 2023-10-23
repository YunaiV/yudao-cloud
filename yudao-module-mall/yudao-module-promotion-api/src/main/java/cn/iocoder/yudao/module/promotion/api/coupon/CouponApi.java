package cn.iocoder.yudao.module.promotion.api.coupon;

import cn.iocoder.yudao.module.promotion.api.coupon.dto.CouponRespDTO;
import cn.iocoder.yudao.module.promotion.api.coupon.dto.CouponUseReqDTO;
import cn.iocoder.yudao.module.promotion.api.coupon.dto.CouponValidReqDTO;
import cn.iocoder.yudao.module.promotion.enums.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@FeignClient(name = ApiConstants.NAME) // TODO 芋艿：fallbackFactory =
@Tag(name = "RPC 服务 - 优惠劵")
public interface CouponApi {

    String PREFIX = ApiConstants.PREFIX + "/coupon";

    @PutMapping(PREFIX + "/use")
    @Operation(summary = "使用优惠劵")
    void useCoupon(@RequestBody @Valid CouponUseReqDTO useReqDTO);

    @PutMapping(PREFIX + "/return-used")
    @Parameter(name = "id", description = "优惠券编号", required = true, example = "1")
    void returnUsedCoupon(@RequestParam("id") Long id);

    @GetMapping(PREFIX + "/get")
    @Operation(summary = "校验优惠劵")
    CouponRespDTO validateCoupon(@Valid CouponValidReqDTO validReqDTO);

}
