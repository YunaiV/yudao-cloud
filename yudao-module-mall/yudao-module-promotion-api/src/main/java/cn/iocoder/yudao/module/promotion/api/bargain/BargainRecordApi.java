package cn.iocoder.yudao.module.promotion.api.bargain;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.promotion.api.bargain.dto.BargainValidateJoinRespDTO;
import cn.iocoder.yudao.module.promotion.enums.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = ApiConstants.NAME) // TODO 芋艿：fallbackFactory =
@Tag(name = "RPC 服务 - 砍价记录")
public interface BargainRecordApi {

    String PREFIX = ApiConstants.PREFIX + "/bargain-record";

    @GetMapping(PREFIX + "/validate-join")
    @Operation(summary = "【下单前】校验是否参与砍价活动") // 如果校验失败，则抛出业务异常
    @Parameters({
            @Parameter(name = "userId", description = "用户编号", required = true, example = "1024"),
            @Parameter(name = "bargainRecordId", description = "砍价记录编号", required = true, example = "2048"),
            @Parameter(name = "skuId", description = "SKU 编号", required = true, example = "4096"),
    })
    CommonResult<BargainValidateJoinRespDTO> validateJoinBargain(@RequestParam("userId") Long userId,
                                                                 @RequestParam("bargainRecordId") Long bargainRecordId,
                                                                 @RequestParam("skuId") Long skuId);

    @PutMapping(PREFIX + "/update-order-id")
    @Operation(summary = "更新砍价记录的订单编号") // 在砍价成功后，用户发起订单后，会记录该订单编号
    @Parameters({
            @Parameter(name = "id", description = "砍价记录编号", required = true, example = "1024"),
            @Parameter(name = "orderId", description = "订单编号", required = true, example = "2048"),
    })
    CommonResult<Boolean> updateBargainRecordOrderId(@RequestParam("id") Long id,
                                                     @RequestParam("oderId") Long orderId);

}
