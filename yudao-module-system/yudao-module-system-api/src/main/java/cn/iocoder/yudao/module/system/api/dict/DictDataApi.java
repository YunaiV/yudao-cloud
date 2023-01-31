package cn.iocoder.yudao.module.system.api.dict;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.system.api.dict.dto.DictDataRespDTO;
import cn.iocoder.yudao.module.system.enums.ApiConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@FeignClient(name = ApiConstants.NAME) // TODO 芋艿：fallbackFactory =
@Api(tags = "RPC 服务 - 字典数据")
public interface DictDataApi {

    String PREFIX = ApiConstants.PREFIX + "/dict-data";

    @GetMapping(PREFIX + "/valid")
    @ApiOperation("校验字典数据们是否有效")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "dictType", value = "字典类型", example = "SEX", required = true, dataTypeClass = String.class),
        @ApiImplicitParam(name = "values", value = "字典数据值的数组", example = "1,2", required = true, allowMultiple = true)
    })
    CommonResult<Boolean> validateDictDatas(@RequestParam("dictType") String dictType,
                                            @RequestParam("values") Collection<String> values);

    @GetMapping(PREFIX + "/get")
    @ApiOperation("获得指定的字典数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dictType", value = "字典类型", example = "SEX", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "value", value = "字典数据值", example = "1", required = true, dataTypeClass = String.class)
    })
    CommonResult<DictDataRespDTO> getDictData(@RequestParam("dictType") String dictType,
                                              @RequestParam("value") String value);

    @GetMapping(PREFIX + "/parse")
    @ApiOperation("解析获得指定的字典数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dictType", value = "字典类型", example = "SEX", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "label", value = "字典标签", example = "男", required = true, dataTypeClass = String.class)
    })
    CommonResult<DictDataRespDTO> parseDictData(@RequestParam("dictType") String dictType,
                                                @RequestParam("label") String label);

}
