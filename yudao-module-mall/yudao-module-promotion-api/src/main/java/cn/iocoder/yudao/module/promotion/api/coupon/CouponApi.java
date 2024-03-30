package cn.iocoder.yudao.module.promotion.api.coupon;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.promotion.api.coupon.dto.CouponRespDTO;
import cn.iocoder.yudao.module.promotion.api.coupon.dto.CouponUseReqDTO;
import cn.iocoder.yudao.module.promotion.api.coupon.dto.CouponValidReqDTO;
import cn.iocoder.yudao.module.promotion.enums.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@FeignClient(name = ApiConstants.NAME) // TODO 芋艿：fallbackFactory =
@Tag(name = "RPC 服务 - 优惠劵")
public interface CouponApi {

    String PREFIX = ApiConstants.PREFIX + "/coupon";

    @PutMapping(PREFIX + "/use")
    @Operation(summary = "使用优惠劵")
    CommonResult<Boolean> useCoupon(@RequestBody @Valid CouponUseReqDTO useReqDTO);

    @PutMapping(PREFIX + "/return-used")
    @Parameter(name = "id", description = "优惠券编号", required = true, example = "1")
    CommonResult<Boolean> returnUsedCoupon(@RequestParam("id") Long id);

    @GetMapping(PREFIX + "/validate")
    @Operation(summary = "校验优惠劵")
    CommonResult<CouponRespDTO> validateCoupon(@Valid @SpringQueryMap CouponValidReqDTO validReqDTO);

}
