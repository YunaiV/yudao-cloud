package cn.iocoder.yudao.module.system.api.sensitiveword;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.system.enums.ApiConstants;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = ApiConstants.NAME) // TODO 芋艿：fallbackFacx`tory =
@Tag(name = "RPC 服务 - 敏感词")
public interface SensitiveWordApi {

    String PREFIX = ApiConstants.PREFIX + "/sensitive-word";

    @GetMapping(PREFIX + "/validate-text")
    @Operation(summary = "获得文本所包含的不合法的敏感词数组")
    @Parameters({
            @Parameter(name = "text", description = "文本", example = "傻瓜", required = true),
            @Parameter(name = "tags", description = "标签数组", example = "product,life", required = true)
    })
    CommonResult<List<String>> validateText(@RequestParam("text") String text,
                                            @RequestParam("tags") List<String> tags);

    @GetMapping(PREFIX + "/is-text-valid")
    @Operation(summary = "判断文本是否包含敏感词")
    @Parameters({
            @Parameter(name = "text", description = "文本", example = "傻瓜", required = true),
            @Parameter(name = "tags", description = "标签数组", example = "product,life", required = true)
    })
    CommonResult<Boolean> isTextValid(@RequestParam("text") String text,
                                      @RequestParam("tags") List<String> tags);

}