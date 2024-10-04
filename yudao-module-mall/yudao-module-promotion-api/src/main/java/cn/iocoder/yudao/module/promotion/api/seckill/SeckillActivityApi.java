package cn.iocoder.yudao.module.promotion.api.seckill;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.promotion.api.seckill.dto.SeckillValidateJoinRespDTO;
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
@Tag(name = "RPC 服务 - 秒杀活动")
public interface SeckillActivityApi {

    String PREFIX = ApiConstants.PREFIX + "/discount-activity";

    @PutMapping(PREFIX + "/update-stock-decr")
    @Operation(summary = "更新秒杀库存（减少）")
    @Parameters({
            @Parameter(name = "id", description = "活动编号", required = true, example = "1"),
            @Parameter(name = "skuId", description = "SKU 编号", required = true, example = "2"),
            @Parameter(name = "count", description = "数量", required = true, example = "3"),
    })
    CommonResult<Boolean> updateSeckillStockDecr(@RequestParam("id") Long id,
                                                 @RequestParam("skuId") Long skuId,
                                                 @RequestParam("count")Integer count);

    @PutMapping(PREFIX + "/update-stock-incr")
    @Operation(summary = "更新秒杀库存（增加）")
    @Parameters({
            @Parameter(name = "id", description = "活动编号", required = true, example = "1"),
            @Parameter(name = "skuId", description = "SKU 编号", required = true, example = "2"),
            @Parameter(name = "count", description = "数量", required = true, example = "3"),
    })
    CommonResult<Boolean> updateSeckillStockIncr(@RequestParam("id") Long id,
                                                 @RequestParam("skuId") Long skuId,
                                                 @RequestParam("count")Integer count);

    @GetMapping(PREFIX + "/validate-join")
    @Operation(summary = "【下单前】校验是否参与秒杀活动") // 如果校验失败，则抛出业务异常
    @Parameters({
            @Parameter(name = "activityId", description = "活动编号", required = true, example = "1"),
            @Parameter(name = "skuId", description = "SKU 编号", required = true, example = "2"),
            @Parameter(name = "count", description = "数量", required = true, example = "3"),
    })
    CommonResult<SeckillValidateJoinRespDTO> validateJoinSeckill(@RequestParam("activityId") Long activityId,
                                                                 @RequestParam("skuId") Long skuId,
                                                                 @RequestParam("count")Integer count);

}
