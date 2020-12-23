package cn.iocoder.mall.managementweb.manager.datadict;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.managementweb.controller.datadict.dto.DataDictCreateDTO;
import cn.iocoder.mall.managementweb.controller.datadict.dto.DataDictUpdateDTO;
import cn.iocoder.mall.managementweb.controller.datadict.vo.DataDictSimpleVO;
import cn.iocoder.mall.managementweb.controller.datadict.vo.DataDictVO;
import cn.iocoder.mall.managementweb.convert.datadict.DataDictConvert;
import cn.iocoder.mall.systemservice.rpc.datadict.DataDictRpc;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/**
* 数据字典 Manager
*/
@Service
public class DataDictManager {

    private static final Comparator<cn.iocoder.mall.systemservice.rpc.datadict.vo.DataDictVO> COMPARATOR_ENUM_VALUE_SORT = Comparator
            .comparing(cn.iocoder.mall.systemservice.rpc.datadict.vo.DataDictVO::getEnumValue)
            .thenComparingInt(cn.iocoder.mall.systemservice.rpc.datadict.vo.DataDictVO::getSort);

    @Reference(version = "${dubbo.consumer.DataDictRpc.version}")
    private DataDictRpc dataDictRpc;

    /**
    * 创建数据字典
    *
    * @param createDTO 创建数据字典 DTO
    * @return 数据字典
    */
    public Integer createDataDict(DataDictCreateDTO createDTO) {
        CommonResult<Integer> createDataDictResult = dataDictRpc.createDataDict(DataDictConvert.INSTANCE.convert(createDTO));
        createDataDictResult.checkError();
        return createDataDictResult.getData();
    }

    /**
    * 更新数据字典
    *
    * @param updateDTO 更新数据字典 DTO
    */
    public void updateDataDict(DataDictUpdateDTO updateDTO) {
        CommonResult<Boolean> updateDataDictResult = dataDictRpc.updateDataDict(DataDictConvert.INSTANCE.convert(updateDTO));
        updateDataDictResult.checkError();
    }

    /**
    * 删除数据字典
    *
    * @param dataDictId 数据字典编号
    */
    public void deleteDataDict(Integer dataDictId) {
        CommonResult<Boolean> deleteDataDictResult = dataDictRpc.deleteDataDict(dataDictId);
        deleteDataDictResult.checkError();
    }

    /**
    * 获得数据字典
    *
    * @param dataDictId 数据字典编号
    * @return 数据字典
    */
    public DataDictVO getDataDict(Integer dataDictId) {
        CommonResult<cn.iocoder.mall.systemservice.rpc.datadict.vo.DataDictVO> getDataDictResult = dataDictRpc.getDataDict(dataDictId);
        getDataDictResult.checkError();
        return DataDictConvert.INSTANCE.convert(getDataDictResult.getData());
    }

    /**
    * 获得数据字典列表
    *
    * @param dataDictIds 数据字典编号列表
    * @return 数据字典列表
    */
    public List<DataDictVO> listDataDicts(List<Integer> dataDictIds) {
        CommonResult<List<cn.iocoder.mall.systemservice.rpc.datadict.vo.DataDictVO>> listDataDictResult = dataDictRpc.listDataDicts(dataDictIds);
        listDataDictResult.checkError();
        return DataDictConvert.INSTANCE.convertList(listDataDictResult.getData());
    }

    /**
     * 获得全部数据字典
     *
     * @return 数据字典列表
     */
    public List<DataDictVO> listDataDicts() {
        CommonResult<List<cn.iocoder.mall.systemservice.rpc.datadict.vo.DataDictVO>> listDataDictResult = dataDictRpc.listDataDicts();
        listDataDictResult.checkError();
        // 按照 enumValue 和 sort 排序
        listDataDictResult.getData().sort(COMPARATOR_ENUM_VALUE_SORT);
        return DataDictConvert.INSTANCE.convertList(listDataDictResult.getData());
    }

    /**
     * 获得全部数据字典
     *
     * 精简返回字段
     *
     * @return 数据字典列表
     */
    public List<DataDictSimpleVO> listSimpleDataDicts() {
        CommonResult<List<cn.iocoder.mall.systemservice.rpc.datadict.vo.DataDictVO>> listDataDictResult = dataDictRpc.listDataDicts();
        listDataDictResult.checkError();
        // 按照 enumValue 和 sort 排序
        listDataDictResult.getData().sort(COMPARATOR_ENUM_VALUE_SORT);
        return DataDictConvert.INSTANCE.convertList02(listDataDictResult.getData());
    }

}
