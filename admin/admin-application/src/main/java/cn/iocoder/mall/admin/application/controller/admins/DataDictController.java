package cn.iocoder.mall.admin.application.controller.admins;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.DataDictService;
import cn.iocoder.mall.admin.api.bo.DataDictBO;
import cn.iocoder.mall.admin.api.dto.DataDictAddDTO;
import cn.iocoder.mall.admin.application.convert.DataDictConvert;
import cn.iocoder.mall.admin.application.vo.DataDictVO;
import cn.iocoder.mall.admin.sdk.context.AdminSecurityContextHolder;
import com.alibaba.dubbo.config.annotation.Reference;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admins/data_dict")
@Api("数据字典模块")
public class DataDictController {

    @Reference(validation = "true")
    private DataDictService dataDictService;

    @GetMapping("/list")
    @ApiOperation(value = "数据字典全列表")
    public CommonResult<List<DataDictVO>> list() {
        CommonResult<List<DataDictBO>> result = dataDictService.selectDataDictList();
        return DataDictConvert.INSTANCE.convert(result);
    }

    @PostMapping("/add")
    @ApiOperation(value = "创建数据字典")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "enumValue", value = "大类枚举值", required = true, example = "gender"),
            @ApiImplicitParam(name = "value", value = "小类数值", required = true, example = "1"),
            @ApiImplicitParam(name = "displayName", value = "展示名", required = true, example = "男"),
            @ApiImplicitParam(name = "sort", required = true, value = "排序值", defaultValue = "10"),
            @ApiImplicitParam(name = "memo", value = "备注", example = "你猜我猜不猜"),
    })
    public CommonResult<DataDictVO> add(@RequestParam("enumValue") String enumValue,
                                        @RequestParam("value") String value,
                                        @RequestParam("displayName") String displayName,
                                        @RequestParam("sort") Integer sort,
                                        @RequestParam(value = "memo", required = false) String memo) {
        // 创建 DataDictAddDTO 对象
        DataDictAddDTO dataDictAddDTO = new DataDictAddDTO().setEnumValue(enumValue).setValue(value).setDisplayName(displayName)
                .setSort(sort).setMemo(memo);
        // 保存数据字典
        CommonResult<DataDictBO> result = dataDictService.addDataDict(AdminSecurityContextHolder.getContext().getAdminId(), dataDictAddDTO);
        // 返回结果
        return DataDictConvert.INSTANCE.convert2(result);
    }

}