package cn.iocoder.yudao.module.promotion.api.reward;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.promotion.api.reward.dto.RewardActivityMatchRespDTO;
import cn.iocoder.yudao.module.promotion.enums.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

@FeignClient(name = ApiConstants.NAME) // TODO 芋艿：fallbackFactory =
@Tag(name = "RPC 服务 - 满减送")
public interface RewardActivityApi {

    String PREFIX = ApiConstants.PREFIX + "/reward-activity";

    @GetMapping(PREFIX + "/list-by-spu-id")
    @Operation(summary = "获得商品匹配的的满减送活动信息")
    @Parameter(name = "spuIds", description = "商品 SPU 编号数组", required = true, example = "[1, 2]")
    CommonResult<List<RewardActivityMatchRespDTO>> getMatchRewardActivityListBySpuIds(@RequestParam("spuIds") Collection<Long> spuIds);

}
