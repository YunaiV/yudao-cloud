package cn.iocoder.mall.admin.api;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.bo.DataDictBO;
import cn.iocoder.mall.admin.api.dto.DataDictAddDTO;
import cn.iocoder.mall.admin.api.dto.DataDictUpdateDTO;

import java.util.List;

public interface DataDictService {

    CommonResult<List<DataDictBO>> selectDataDictList();

    CommonResult<DataDictBO> addDataDict(Integer adminId, DataDictAddDTO dataDictAddDTO);

    CommonResult<Boolean> updateDataDict(Integer adminId, DataDictUpdateDTO dataDictUpdateDTO);

    CommonResult<Boolean> deleteDataDict(Integer adminId, Integer dataDictId);

}
