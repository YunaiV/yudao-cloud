package cn.iocoder.mall.admin.service;

import cn.iocoder.common.framework.constant.DeletedStatusEnum;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.DataDictService;
import cn.iocoder.mall.admin.api.bo.datadict.DataDictBO;
import cn.iocoder.mall.admin.api.constant.AdminErrorCodeEnum;
import cn.iocoder.mall.admin.api.dto.datadict.DataDictAddDTO;
import cn.iocoder.mall.admin.api.dto.datadict.DataDictUpdateDTO;
import cn.iocoder.mall.admin.convert.DataDictConvert;
import cn.iocoder.mall.admin.dao.DataDictMapper;
import cn.iocoder.mall.admin.dataobject.DataDictDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 数据字典 Service
 */
@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.DataDictService.version}")
public class DataDictServiceImpl implements DataDictService {

    @Autowired
    private DataDictMapper dataDictMapper;

    @Override
    public CommonResult<List<DataDictBO>> selectDataDictList() {
        List<DataDictDO> dataDicts = dataDictMapper.selectList();
        return CommonResult.success(DataDictConvert.INSTANCE.convert(dataDicts));
    }

    @Override
    public CommonResult<DataDictBO> addDataDict(Integer adminId, DataDictAddDTO dataDictAddDTO) {
        // 校验数据字典重复
        if (dataDictMapper.selectByEnumValueAndValue(dataDictAddDTO.getEnumValue(), dataDictAddDTO.getValue()) != null) {
            return ServiceExceptionUtil.error(AdminErrorCodeEnum.DATA_DICT_EXISTS.getCode());
        }
        // 保存到数据库
        DataDictDO dataDict = DataDictConvert.INSTANCE.convert(dataDictAddDTO);
        dataDict.setCreateTime(new Date());
        dataDict.setDeleted(DeletedStatusEnum.DELETED_NO.getValue());
        dataDictMapper.insert(dataDict);
        // TODO 插入操作日志
        // 返回成功
        return CommonResult.success(DataDictConvert.INSTANCE.convert(dataDict));
    }

    @Override
    public CommonResult<Boolean> updateDataDict(Integer adminId, DataDictUpdateDTO dataDictUpdateDTO) {
        // 校验数据字典不存在
        DataDictDO existsDataDict = dataDictMapper.selectById(dataDictUpdateDTO.getId());
        if (existsDataDict == null) {
            return ServiceExceptionUtil.error(AdminErrorCodeEnum.DATA_DICT_NOT_EXISTS.getCode());
        }
        // 校验数据字典重复
        DataDictDO duplicateDataDict = dataDictMapper.selectByEnumValueAndValue(existsDataDict.getEnumValue(), dataDictUpdateDTO.getValue());
        if (duplicateDataDict != null && !duplicateDataDict.getId().equals(dataDictUpdateDTO.getId())) {
            return ServiceExceptionUtil.error(AdminErrorCodeEnum.DATA_DICT_EXISTS.getCode());
        }
        // 更新到数据库
        DataDictDO updateDataDict = DataDictConvert.INSTANCE.convert(dataDictUpdateDTO);
        dataDictMapper.update(updateDataDict);
        // TODO 插入操作日志
        // 返回成功
        return CommonResult.success(true);
    }

    // 一般情况下，不要删除数据字典。
    // 因为，业务数据正在使用该数据字典，删除后，可能有不可预知的问题。
    @Override
    public CommonResult<Boolean> deleteDataDict(Integer adminId, Integer dataDictId) {
        // 校验数据字典不存在
        DataDictDO existsDataDict = dataDictMapper.selectById(dataDictId);
        if (existsDataDict == null) {
            return ServiceExceptionUtil.error(AdminErrorCodeEnum.DATA_DICT_NOT_EXISTS.getCode());
        }
        // 更新到数据库
        DataDictDO updateDataDict = new DataDictDO().setId(dataDictId);
        updateDataDict.setDeleted(DeletedStatusEnum.DELETED_YES.getValue());
        dataDictMapper.update(updateDataDict);
        // TODO 插入操作日志
        // 返回成功
        return CommonResult.success(true);
    }

    @Override
    public CommonResult<DataDictBO> getDataDict(String dictKey, Object dictValue) {
        DataDictDO dataDictDO = dataDictMapper.selectByEnumValueAndValue(dictKey, String.valueOf(dictValue));
        DataDictBO dataDictBO = DataDictConvert.INSTANCE.convert(dataDictDO);
        return CommonResult.success(dataDictBO);
    }

    @Override
    public CommonResult<List<DataDictBO>> getDataDict(String dictKey) {
        List<DataDictDO> dataDictDOList = dataDictMapper.selectByEnumValue(dictKey);
        List<DataDictBO> dataDictBOList = DataDictConvert.INSTANCE.convert(dataDictDOList);
        return CommonResult.success(dataDictBOList);
    }

    @Override
    public CommonResult<List<DataDictBO>> getDataDictList(String dictKey, Collection<?> dictValueList) {
        Set<String> convertDictValueList = dictValueList.stream().map(o -> String.valueOf(o)).collect(Collectors.toSet());
        List<DataDictDO> dataDictDOList = dataDictMapper.selectByEnumValueAndValues(dictKey, convertDictValueList);
        List<DataDictBO> dataDictBOList = DataDictConvert.INSTANCE.convert(dataDictDOList);
        return CommonResult.success(dataDictBOList);
    }
}
