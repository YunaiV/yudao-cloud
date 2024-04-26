package cn.iocoder.yudao.module.promotion.api.bargain;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.promotion.enums.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = ApiConstants.NAME) // TODO 芋艿：fallbackFactory =
@Tag(name = "RPC 服务 - 砍价活动")
public interface BargainActivityApi {

    String PREFIX = ApiConstants.PREFIX + "/bargain-activity";

    @PutMapping(PREFIX + "/update-stock")
    @Operation(summary = "更新砍价活动库存")
    @Parameters({
            @Parameter(name = "id", description = "砍价活动编号", required = true, example = "1024"),
            @Parameter(name = "count", description = "购买数量", required = true, example = "1"),
    })
    CommonResult<Boolean> updateBargainActivityStock(@RequestParam("id") Long id,
                                                     @RequestParam("count") Integer count);

}
