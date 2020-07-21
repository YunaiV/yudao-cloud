package cn.iocoder.mall.systemservice.service.datadict;

import cn.iocoder.common.framework.exception.util.ServiceExceptionUtil;
import cn.iocoder.mall.systemservice.convert.datadict.DataDictConvert;
import cn.iocoder.mall.systemservice.dal.mysql.dataobject.datadict.DataDictDO;
import cn.iocoder.mall.systemservice.dal.mysql.mapper.datadict.DataDictMapper;
import cn.iocoder.mall.systemservice.service.datadict.bo.DataDictBO;
import cn.iocoder.mall.systemservice.service.datadict.bo.DataDictCreateBO;
import cn.iocoder.mall.systemservice.service.datadict.bo.DataDictUpdateBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

import static cn.iocoder.mall.systemservice.enums.SystemErrorCodeConstants.*;

/**
* 数据字典 Service
*/
@Service
@Validated
public class DataDictService {

    @Autowired
    private DataDictMapper dataDictMapper;

    /**
    * 创建数据字典
    *
    * @param createBO 创建数据字典 BO
    * @return 数据字典
    */
    public DataDictBO createDataDict(@Valid DataDictCreateBO createBO) {
        // 校验数据字典重复
        checkDataDict(createBO.getEnumValue(), createBO.getValue(), null);
        // 插入到数据库
        DataDictDO dataDictDO = DataDictConvert.INSTANCE.convert(createBO);
        dataDictMapper.insert(dataDictDO);
        // 返回
        return DataDictConvert.INSTANCE.convert(dataDictDO);
    }

    /**
    * 更新数据字典
    *
    * @param updateBO 更新数据字典 BO
    */
    public void updateDataDict(@Valid DataDictUpdateBO updateBO) {
        // 校验更新的数据字典是否存在
        if (dataDictMapper.selectById(updateBO.getId()) == null) {
            throw ServiceExceptionUtil.exception(DATA_DICT_NOT_EXISTS);
        }
        // 校验数据字典重复
        checkDataDict(updateBO.getEnumValue(), updateBO.getValue(), updateBO.getId());
        // 更新到数据库
        DataDictDO updateObject = DataDictConvert.INSTANCE.convert(updateBO);
        dataDictMapper.updateById(updateObject);
    }

    /**
    * 删除数据字典
    *
    * @param dataDictId 数据字典编号
    */
    public void deleteDataDict(Integer dataDictId) {
        // 校验删除的数据字典是否存在
        if (dataDictMapper.selectById(dataDictId) == null) {
            throw ServiceExceptionUtil.exception(DATA_DICT_NOT_EXISTS);
        }
        // 标记删除
        dataDictMapper.deleteById(dataDictId);
    }

    /**
    * 获得数据字典
    *
    * @param dataDictId 数据字典编号
    * @return 数据字典
    */
    public DataDictBO getDataDict(Integer dataDictId) {
        DataDictDO dataDictDO = dataDictMapper.selectById(dataDictId);
        return DataDictConvert.INSTANCE.convert(dataDictDO);
    }

    /**
     * 获得全部数据字典
     *
     * @return 数据字典列表
     */
    public List<DataDictBO> listDataDicts() {
        List<DataDictDO> dataDictDOs = dataDictMapper.selectList(null);
        return DataDictConvert.INSTANCE.convertList(dataDictDOs);
    }

    /**
    * 获得数据字典列表
    *
    * @param dataDictIds 数据字典编号列表
    * @return 数据字典列表
    */
    public List<DataDictBO> listDataDicts(List<Integer> dataDictIds) {
        List<DataDictDO> dataDictDOs = dataDictMapper.selectBatchIds(dataDictIds);
        return DataDictConvert.INSTANCE.convertList(dataDictDOs);
    }

    /**
     * 校验数据字典是否合法
     *
     * 1. 校验相同大类下，是否有相同的小类
     *
     * @param enumValue 枚举大类
     * @param value 枚举小类
     * @param id 资源编号
     */
    private void checkDataDict(String enumValue, String value, Integer id) {
        DataDictDO dataDict = dataDictMapper.selectByEnumValueAndValue(enumValue, value);
        if (dataDict == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的资源
        if (id == null) {
            throw ServiceExceptionUtil.exception(DATA_DICT_EXISTS);
        }
        if (!dataDict.getId().equals(id)) {
            throw ServiceExceptionUtil.exception(DATA_DICT_EXISTS);
        }
    }

//    @Override
//    public CommonResult<DataDictBO> getDataDict(String dictKey, Object dictValue) {
//        DataDictDO dataDictDO = dataDictMapper.selectByEnumValueAndValue(dictKey, String.valueOf(dictValue));
//        DataDictBO dataDictBO = DataDictConvert.INSTANCE.convert(dataDictDO);
//        return CommonResult.success(dataDictBO);
//    }
//
//    @Override
//    public CommonResult<List<DataDictBO>> getDataDict(String dictKey) {
//        List<DataDictDO> dataDictDOList = dataDictMapper.selectByEnumValue(dictKey);
//        List<DataDictBO> dataDictBOList = DataDictConvert.INSTANCE.convert(dataDictDOList);
//        return CommonResult.success(dataDictBOList);
//    }
//
//    @Override
//    public CommonResult<List<DataDictBO>> getDataDictList(String dictKey, Collection<?> dictValueList) {
//        Set<String> convertDictValueList = dictValueList.stream().map(String::valueOf).collect(Collectors.toSet());
//        List<DataDictDO> dataDictDOList = dataDictMapper.selectByEnumValueAndValues(dictKey, convertDictValueList);
//        List<DataDictBO> dataDictBOList = DataDictConvert.INSTANCE.convert(dataDictDOList);
//        return CommonResult.success(dataDictBOList);
//    }

}
