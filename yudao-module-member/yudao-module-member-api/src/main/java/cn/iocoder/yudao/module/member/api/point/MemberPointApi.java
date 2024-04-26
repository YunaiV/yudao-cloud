package cn.iocoder.yudao.module.member.api.point;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.member.enums.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Min;

@FeignClient(name = ApiConstants.NAME) // TODO 芋艿：fallbackFactory =
@Tag(name = "RPC 服务 - 用户积分")
public interface MemberPointApi {

    String PREFIX = ApiConstants.PREFIX + "/point";

    @PostMapping(PREFIX + "/add")
    @Operation(summary = "增加用户积分")
    @Parameters({
            @Parameter(name = "userId", description = "会员编号", required = true, example = "1024"),
            @Parameter(name = "point", description = "积分", required = true, example = "100"),
            @Parameter(name = "bizType", description = "业务类型", required = true, example = "1"),
            @Parameter(name = "bizId", description = "业务编号", required = true, example = "1")
    })
    CommonResult<Boolean> addPoint(@RequestParam("userId") Long userId,
                                   @RequestParam("point") @Min(value = 1L, message = "积分必须是正数") Integer point,
                                   @RequestParam("bizType") Integer bizType,
                                   @RequestParam("bizId") String bizId);

    @PostMapping(PREFIX + "/reducePoint")
    @Operation(summary = "减少用户积分")
    @Parameters({
            @Parameter(name = "userId", description = "会员编号", required = true, example = "1024"),
            @Parameter(name = "point", description = "积分", required = true, example = "100"),
            @Parameter(name = "bizType", description = "业务类型", required = true, example = "1"),
            @Parameter(name = "bizId", description = "业务编号", required = true, example = "1")
    })
    CommonResult<Boolean> reducePoint(@RequestParam("userId") Long userId,
                                      @RequestParam("point") @Min(value = 1L, message = "积分必须是正数") Integer point,
                                      @RequestParam("bizType") Integer bizType,
                                      @RequestParam("bizId") String bizId);

}
