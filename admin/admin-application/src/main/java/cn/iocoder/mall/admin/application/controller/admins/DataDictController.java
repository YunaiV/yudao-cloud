package cn.iocoder.mall.admin.application.controller.admins;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.DataDictService;
import cn.iocoder.mall.admin.api.bo.DataDictBO;
import cn.iocoder.mall.admin.api.dto.DataDictAddDTO;
import cn.iocoder.mall.admin.api.dto.DataDictUpdateDTO;
import cn.iocoder.mall.admin.application.convert.DataDictConvert;
import cn.iocoder.mall.admin.application.vo.DataDictEnumVO;
import cn.iocoder.mall.admin.application.vo.DataDictVO;
import cn.iocoder.mall.admin.sdk.context.AdminSecurityContextHolder;
import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Multimaps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping("/tree")
    @ApiOperation(value = "数据字典树结构", notes = "该接口返回的信息更为精简。一般用于前端缓存数据字典到本地。")
    public CommonResult<List<DataDictEnumVO>> tree() {
        // 查询数据字典全列表
        CommonResult<List<DataDictBO>> result = dataDictService.selectDataDictList();
        if (result.isError()) {
            return CommonResult.error(result);
        }
        // 构建基于 enumValue 聚合的 Multimap
        ImmutableListMultimap<String, DataDictBO> dataDictMap = Multimaps.index(result.getData(), DataDictBO::getEnumValue); // KEY 是 enumValue ，VALUE 是 DataDictBO 数组
        // 构建返回结果
        List<DataDictEnumVO> dataDictEnumVOs = new ArrayList<>(dataDictMap.size());
        dataDictMap.keys().forEach(enumValue -> {
            DataDictEnumVO dataDictEnumVO = new DataDictEnumVO().setEnumValue(enumValue)
                    .setValues(DataDictConvert.INSTANCE.convert2(dataDictMap.get(enumValue)));
            dataDictEnumVOs.add(dataDictEnumVO);
        });
        return CommonResult.success(dataDictEnumVOs);
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

    @PostMapping("/update")
    @ApiOperation(value = "更新数据字典")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "编号", required = true, example = "100"),
            @ApiImplicitParam(name = "value", value = "小类数值", required = true, example = "1"),
            @ApiImplicitParam(name = "displayName", value = "展示名", required = true, example = "男"),
            @ApiImplicitParam(name = "sort", required = true, value = "排序值", defaultValue = "10"),
            @ApiImplicitParam(name = "memo", value = "备注", example = "你猜我猜不猜"),
    })
    public CommonResult<Boolean> update(@RequestParam("id") Integer id,
                                        @RequestParam("value") String value,
                                        @RequestParam("displayName") String displayName,
                                        @RequestParam("sort") Integer sort,
                                        @RequestParam(value = "memo", required = false) String memo) {
        // 创建 DataDictAddDTO 对象
        DataDictUpdateDTO dataDictUpdateDTO = new DataDictUpdateDTO().setId(id).setValue(value).setDisplayName(displayName)
                .setSort(sort).setMemo(memo);
        // 更新数据字典
        return dataDictService.updateDataDict(AdminSecurityContextHolder.getContext().getAdminId(), dataDictUpdateDTO);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除数据字典")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "100")
    public CommonResult<Boolean> delete(@RequestParam("id") Integer id) {
        return dataDictService.deleteDataDict(AdminSecurityContextHolder.getContext().getAdminId(), id);
    }

}