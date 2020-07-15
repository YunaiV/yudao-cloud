package cn.iocoder.mall.systemservice.rpc.datadict;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.systemservice.rpc.datadict.dto.DataDictCreateDTO;
import cn.iocoder.mall.systemservice.rpc.datadict.dto.DataDictUpdateDTO;
import cn.iocoder.mall.systemservice.rpc.datadict.vo.DataDictVO;

import java.util.List;

/**
* 数据字典 Rpc 接口
*/
public interface DataDictRpc {

    /**
    * 创建数据字典
    *
    * @param createDTO 创建数据字典 DTO
    * @return 数据字典编号
    */
    CommonResult<Integer> createDataDict(DataDictCreateDTO createDTO);

    /**
    * 更新数据字典
    *
    * @param updateDTO 更新数据字典 DTO
    */
    CommonResult<Boolean> updateDataDict(DataDictUpdateDTO updateDTO);

    /**
    * 删除数据字典
    *
    * @param dataDictId 数据字典编号
    */
    CommonResult<Boolean> deleteDataDict(Integer dataDictId);

    /**
    * 获得数据字典
    *
    * @param dataDictId 数据字典编号
    * @return 数据字典
    */
    CommonResult<DataDictVO> getDataDict(Integer dataDictId);

    /**
     * 获得全部数据字典
     *
     * @return 数据字典列表
     */
    CommonResult<List<DataDictVO>> listDataDicts();

    /**
    * 获得数据字典列表
    *
    * @param dataDictIds 数据字典编号列表
    * @return 数据字典列表
    */
    CommonResult<List<DataDictVO>> listDataDicts(List<Integer> dataDictIds);

}
