package cn.iocoder.yudao.module.member.api.level;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.member.api.level.dto.MemberLevelRespDTO;
import cn.iocoder.yudao.module.member.enums.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = ApiConstants.NAME) // TODO 芋艿：fallbackFactory =
@Tag(name = "RPC 服务 - 会员等级")
public interface MemberLevelApi {

    String PREFIX = ApiConstants.PREFIX + "/level";

    @GetMapping(PREFIX + "/get")
    @Operation(summary = "获得会员等级")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    CommonResult<MemberLevelRespDTO> getMemberLevel(@RequestParam("id") Long id);

    @PostMapping(PREFIX + "/add")
    @Operation(summary = "增加会员经验")
    @Parameters({
            @Parameter(name = "userId", description = "会员编号", required = true, example = "1024"),
            @Parameter(name = "experience", description = "经验值", required = true, example = "100"),
            @Parameter(name = "bizType", description = "业务类型", required = true, example = "1"),
            @Parameter(name = "bizId", description = "业务编号", required = true, example = "1")
    })
    CommonResult<Boolean> addExperience(@RequestParam("userId") Long userId,
                                        @RequestParam("experience") Integer experience,
                                        @RequestParam("bizType") Integer bizType,
                                        @RequestParam("bizId") String bizId);

    @PostMapping(PREFIX + "/reduce")
    @Operation(summary = "扣减会员经验")
    @Parameters({
            @Parameter(name = "userId", description = "会员编号", required = true, example = "1024"),
            @Parameter(name = "experience", description = "经验值", required = true, example = "100"),
            @Parameter(name = "bizType", description = "业务类型", required = true, example = "1"),
            @Parameter(name = "bizId", description = "业务编号", required = true, example = "1")
    })
    CommonResult<Boolean> reduceExperience(@RequestParam("userId") Long userId,
                                           @RequestParam("experience") Integer experience,
                                           @RequestParam("bizType") Integer bizType,
                                           @RequestParam("bizId") String bizId);

}
