package cn.iocoder.mall.managementweb.controller.datadict;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.managementweb.controller.datadict.dto.DataDictCreateDTO;
import cn.iocoder.mall.managementweb.controller.datadict.dto.DataDictUpdateDTO;
import cn.iocoder.mall.managementweb.controller.datadict.vo.DataDictSimpleVO;
import cn.iocoder.mall.managementweb.controller.datadict.vo.DataDictVO;
import cn.iocoder.mall.managementweb.manager.datadict.DataDictManager;
import cn.iocoder.security.annotations.RequiresPermissions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
* 数据字典 Controller
*/
@RestController
@RequestMapping("/data-dict")
@Api(tags = "数据字典")
@Validated
public class DataDictController {

    @Autowired
    private DataDictManager dataDictManager;

    @PostMapping("/create")
    @ApiOperation("创建数据字典")
    @RequiresPermissions("system:data-dict:create")
    public CommonResult<Integer> createDataDict(@Valid DataDictCreateDTO createDTO) {
        return success(dataDictManager.createDataDict(createDTO));
    }

    @PostMapping("/update")
    @ApiOperation("更新数据字典")
    @RequiresPermissions("system:data-dict:update")
    public CommonResult<Boolean> updateDataDict(@Valid DataDictUpdateDTO updateDTO) {
        dataDictManager.updateDataDict(updateDTO);
        return success(true);
    }

    @PostMapping("/delete")
    @ApiOperation("删除数据字典")
    @ApiImplicitParam(name = "dataDictId", value = "数据字典编号", required = true)
    @RequiresPermissions("system:data-dict:delete")
    public CommonResult<Boolean> deleteDataDict(@RequestParam("dataDictId") Integer dataDictId) {
        dataDictManager.deleteDataDict(dataDictId);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得数据字典")
    @ApiImplicitParam(name = "dataDictId", value = "数据字典编号", required = true)
    @RequiresPermissions("system:data-dict:list")
    public CommonResult<DataDictVO> getDataDict(@RequestParam("dataDictId") Integer dataDictId) {
        return success(dataDictManager.getDataDict(dataDictId));
    }

    @GetMapping("/list")
    @ApiOperation("获得数据字典列表")
    @ApiImplicitParam(name = "dataDictIds", value = "数据字典编号列表", required = true)
    @RequiresPermissions("system:data-dict:list")
    public CommonResult<List<DataDictVO>> listDataDicts(@RequestParam("dataDictIds") List<Integer> dataDictIds) {
        return success(dataDictManager.listDataDicts(dataDictIds));
    }

    @GetMapping("/list-all")
    @ApiOperation("获得全部数据字典列表")
    @RequiresPermissions("system:data-dict:list")
    public CommonResult<List<DataDictVO>> listDataDicts() {
        return success(dataDictManager.listDataDicts());
    }

    @GetMapping("/list-all-simple")
    @ApiOperation(value = "获得全部数据字典列表", notes = "一般用于管理后台缓存数据字典在本地")
    // 无需添加权限认证，因为前端全局都需要
    public CommonResult<List<DataDictSimpleVO>> listSimpleDataDicts() {
        return success(dataDictManager.listSimpleDataDicts());
    }

}
