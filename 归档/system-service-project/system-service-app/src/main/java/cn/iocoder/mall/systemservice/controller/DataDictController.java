package cn.iocoder.mall.systemservice.controller;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.systemservice.manager.datadict.DataDictManager;
import cn.iocoder.mall.systemservice.rpc.datadict.dto.DataDictCreateDTO;
import cn.iocoder.mall.systemservice.rpc.datadict.dto.DataDictUpdateDTO;
import cn.iocoder.mall.systemservice.rpc.datadict.vo.DataDictVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
 * Title:
 * Description:
 *
 * @author zhuyang
 * @version 1.0 2021/10/11
 */
@RestController
@RequestMapping("/system/datadict")
public class DataDictController {
    @Autowired
    private DataDictManager dataDictManager;

    @PostMapping("createDataDict")
    public CommonResult<Integer> createDataDict(@RequestBody DataDictCreateDTO createDTO) {
        return success(dataDictManager.createDataDict(createDTO));
    }

    @PostMapping("updateDataDict")
    public CommonResult<Boolean> updateDataDict(@RequestBody DataDictUpdateDTO updateDTO) {
        dataDictManager.updateDataDict(updateDTO);
        return success(true);
    }

    @GetMapping("deleteDataDict")
    public CommonResult<Boolean> deleteDataDict(@RequestParam("dataDictId") Integer dataDictId) {
        dataDictManager.deleteDataDict(dataDictId);
        return success(true);
    }

    @GetMapping("getDataDict")
    public CommonResult<DataDictVO> getDataDict(@RequestParam("dataDictId") Integer dataDictId) {
        return success(dataDictManager.getDataDict(dataDictId));
    }

    @GetMapping("listAllDataDicts")
    public CommonResult<List<DataDictVO>> listDataDicts() {
        return success(dataDictManager.listDataDicts());
    }

    @GetMapping("listDataDicts")
    public CommonResult<List<DataDictVO>> listDataDicts(@RequestParam("dataDictIds") List<Integer> dataDictIds) {
        return success(dataDictManager.listDataDicts(dataDictIds));
    }

}
