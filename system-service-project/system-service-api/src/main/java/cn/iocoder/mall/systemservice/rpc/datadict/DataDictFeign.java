package cn.iocoder.mall.systemservice.rpc.datadict;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.systemservice.rpc.admin.dto.DepartmentCreateDTO;
import cn.iocoder.mall.systemservice.rpc.admin.dto.DepartmentUpdateDTO;
import cn.iocoder.mall.systemservice.rpc.admin.vo.DepartmentVO;
import cn.iocoder.mall.systemservice.rpc.datadict.dto.DataDictCreateDTO;
import cn.iocoder.mall.systemservice.rpc.datadict.dto.DataDictUpdateDTO;
import cn.iocoder.mall.systemservice.rpc.datadict.vo.DataDictVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
* 部门 Rpc 接口
*/
@FeignClient("system-service")
public interface DataDictFeign {
    @PostMapping("/system/datadict/createDataDict")
    public CommonResult<Integer> createDataDict(@RequestBody DataDictCreateDTO createDTO) ;

    @PostMapping("/system/datadict/updateDataDict")
    public CommonResult<Boolean> updateDataDict(@RequestBody DataDictUpdateDTO updateDTO);

    @GetMapping("/system/datadict/deleteDataDict")
    public CommonResult<Boolean> deleteDataDict(@RequestParam("dataDictId") Integer dataDictId);

    @GetMapping("/system/datadict/getDataDict")
    public CommonResult<DataDictVO> getDataDict(@RequestParam("dataDictId") Integer dataDictId);

    @GetMapping("/system/datadict/listAllDataDicts")
    public CommonResult<List<DataDictVO>> listDataDicts() ;

    @GetMapping("/system/datadict/listDataDicts")
    public CommonResult<List<DataDictVO>> listDataDicts(@RequestParam("dataDictIds") List<Integer> dataDictIds);


}
