package cn.iocoder.yudao.module.promotion.api.combination;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.promotion.api.combination.dto.CombinationRecordCreateReqDTO;
import cn.iocoder.yudao.module.promotion.api.combination.dto.CombinationRecordCreateRespDTO;
import cn.iocoder.yudao.module.promotion.api.combination.dto.CombinationRecordRespDTO;
import cn.iocoder.yudao.module.promotion.api.combination.dto.CombinationValidateJoinRespDTO;
import cn.iocoder.yudao.module.promotion.enums.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@FeignClient(name = ApiConstants.NAME) // TODO 芋艿：fallbackFactory =
@Tag(name = "RPC 服务 - 拼团记录")
public interface CombinationRecordApi {

    String PREFIX = ApiConstants.PREFIX + "/combination-record";

    @GetMapping(PREFIX + "/validate")
    @Operation(summary = "校验是否满足拼团条件")
    @Parameters({
            @Parameter(name = "userId", description = "用户编号", required = true, example = "1024"),
            @Parameter(name = "activityId", description = "活动编号", required = true, example = "2048"),
            @Parameter(name = "headId", description = "团长编号", required = true, example = "4096"),
            @Parameter(name = "skuId", description = "SKU 编号", required = true, example = "8192"),
            @Parameter(name = "count", description = "数量", required = true, example = "1"),
    })
    CommonResult<Boolean> validateCombinationRecord(@RequestParam("userId") Long userId,
                                                    @RequestParam("activityId") Long activityId,
                                                    @RequestParam("headId") Long headId,
                                                    @RequestParam("skuId") Long skuId,
                                                    @RequestParam("count") Integer count);

    @PostMapping(PREFIX + "/create")
    @Operation(summary = "创建开团记录")
    CommonResult<CombinationRecordCreateRespDTO> createCombinationRecord(
            @RequestBody @Valid CombinationRecordCreateReqDTO reqDTO);

    @GetMapping(PREFIX + "/get-by-order-id")
    @Operation(summary = "基于订单编号，查询拼团记录")
    @Parameters({
            @Parameter(name = "userId", description = "用户编号", required = true, example = "1024"),
            @Parameter(name = "orderId", description = "订单编号", required = true, example = "2048"),
    })
    CommonResult<CombinationRecordRespDTO> getCombinationRecordByOrderId(@RequestParam("userId") Long userId,
                                                                         @RequestParam("orderId") Long orderId);

    @GetMapping(PREFIX + "/validate-join")
    @Operation(summary = "【下单前】校验是否满足拼团活动条件") // 如果校验失败，则抛出业务异常
    @Parameters({
            @Parameter(name = "userId", description = "用户编号", required = true, example = "1024"),
            @Parameter(name = "activityId", description = "活动编号", required = true, example = "2048"),
            @Parameter(name = "headId", description = "团长编号", example = "4096"), // 如果新发起的团，headId 不用传递
            @Parameter(name = "skuId", description = "SKU 编号", required = true, example = "8192"),
            @Parameter(name = "count", description = "数量", required = true, example = "1"),
    })
    CommonResult<CombinationValidateJoinRespDTO> validateJoinCombination(@RequestParam("userId") Long userId,
                                                                         @RequestParam("activityId") Long activityId,
                                                                         @RequestParam(value = "headId", required = false) Long headId,
                                                                         @RequestParam("skuId") Long skuId,
                                                                         @RequestParam("count") Integer count);

}
