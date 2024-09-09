package cn.iocoder.yudao.module.promotion.api.coupon;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.promotion.api.coupon.dto.CouponRespDTO;
import cn.iocoder.yudao.module.promotion.api.coupon.dto.CouponUseReqDTO;
import cn.iocoder.yudao.module.promotion.enums.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@FeignClient(name = ApiConstants.NAME) // TODO 芋艿：fallbackFactory =
@Tag(name = "RPC 服务 - 优惠劵")
public interface CouponApi {

    String PREFIX = ApiConstants.PREFIX + "/coupon";

    @GetMapping(PREFIX + "/list-by-user-id")
    @Operation(summary = "获得用户的优惠劵列表")
    CommonResult<List<CouponRespDTO>> getCouponListByUserId(@RequestParam("userId") Long userId,
                                                            @RequestParam("status") Integer status);

    @PutMapping(PREFIX + "/use")
    @Operation(summary = "使用优惠劵")
    CommonResult<Boolean> useCoupon(@RequestBody @Valid CouponUseReqDTO useReqDTO);

    @PutMapping(PREFIX + "/return-used")
    @Operation(summary = "退还已使用的优惠券")
    @Parameter(name = "id", description = "优惠券编号", required = true, example = "1")
    CommonResult<Boolean> returnUsedCoupon(@RequestParam("id") Long id);

    @PostMapping(PREFIX + "/take-by-admin")
    @Operation(summary = "【管理员】给指定用户批量发送优惠券") // 返回：优惠券编号列表
    @Parameters({
            @Parameter(name = "giveCoupons", description = "key: 优惠劵模版编号，value：对应的数量", required = true),
            @Parameter(name = "userId", description = "用户编号", required = true)
    })
    CommonResult<List<Long>> takeCouponsByAdmin(@RequestBody Map<Long, Integer> giveCoupons,
                                                @RequestParam("userId") Long userId);

    @PostMapping(PREFIX + "/invalidate-by-admin")
    @Operation(summary = "【管理员】作废指定用户的指定优惠劵")
    @Parameters({
            @Parameter(name = "giveCouponIds", description = "赠送的优惠券编号", required = true),
            @Parameter(name = "userId", description = "用户编号", required = true)
    })
    CommonResult<Boolean> invalidateCouponsByAdmin(@RequestParam("赠送的优惠券编号") List<Long> giveCouponIds,
                                                   @RequestParam("用户编号") Long userId);

}
