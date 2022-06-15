package cn.iocoder.yudao.module.system.api.dict;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.system.enums.ApiConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@FeignClient(name = ApiConstants.NAME) // TODO 芋艿：fallbackFactory =
@Api(tags = "RPC 服务 - 字典数据")
public interface DictDataApi {

    String PREFIX = ApiConstants.PREFIX + "/dict-data";

    @GetMapping(PREFIX + "/valid")
    @ApiOperation("校验字典数据们是否有效")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "dictType", value = "字典类型", required = true, dataTypeClass = String.class),
        @ApiImplicitParam(name = "values", value = "字典数据值的数组", required = true, allowMultiple = true)
    })
    CommonResult<Boolean> validDictDatas(String dictType, Collection<String> values);

}
