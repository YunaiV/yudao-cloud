package cn.iocoder.mall.admin.api;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.bo.datadict.DataDictBO;
import cn.iocoder.mall.admin.api.dto.datadict.DataDictAddDTO;
import cn.iocoder.mall.admin.api.dto.datadict.DataDictUpdateDTO;

import java.util.Collection;
import java.util.List;

public interface DataDictService {

    CommonResult<List<DataDictBO>> selectDataDictList();

    CommonResult<DataDictBO> addDataDict(Integer adminId, DataDictAddDTO dataDictAddDTO);

    CommonResult<Boolean> updateDataDict(Integer adminId, DataDictUpdateDTO dataDictUpdateDTO);

    CommonResult<Boolean> deleteDataDict(Integer adminId, Integer dataDictId);

    /**
     * 获取字典值 - 单个
     *
     *  注意: dictValue:Object 为了方便调用，会自动转换为 dictValue:String
     *
     * @param dictKey
     * @param dictValue
     * @return
     */
    CommonResult<DataDictBO> getDataDict(String dictKey, Object dictValue);
    CommonResult<List<DataDictBO>> getDataDict(String dictKey);

    /**
     * 获取字典值 - 多个
     *
     *  注意：dictValueList:? 为了方便调用，会自动转换为 Set:String
     *
     * @param dictKey
     * @param dictValueList
     * @return
     */
    CommonResult<List<DataDictBO>> getDataDictList(String dictKey, Collection<?> dictValueList);
}
