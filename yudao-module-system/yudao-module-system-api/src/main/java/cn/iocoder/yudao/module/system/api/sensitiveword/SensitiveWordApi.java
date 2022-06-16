package cn.iocoder.yudao.module.system.api.sensitiveword;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.system.enums.ApiConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = ApiConstants.NAME) // TODO 芋艿：fallbackFacx`tory =
@Api(tags = "RPC 服务 - 敏感词")
public interface SensitiveWordApi {

    String PREFIX = ApiConstants.PREFIX + "/oauth2/sensitive-word";

    @GetMapping(PREFIX + "/validate-text")
    @ApiOperation("获得文本所包含的不合法的敏感词数组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "text", value = "文本", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "tags", value = "标签数组", required = true, allowMultiple = true)
    })
    CommonResult<List<String>> validateText(@RequestParam("text") String text,
                                            @RequestParam("tags") List<String> tags);

    @GetMapping(PREFIX + "/is-text-valid")
    @ApiOperation("判断文本是否包含敏感词")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "text", value = "文本", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "tags", value = "标签数组", required = true, allowMultiple = true)
    })
    CommonResult<Boolean> isTextValid(@RequestParam("text") String text,
                                      @RequestParam("tags") List<String> tags);

}
