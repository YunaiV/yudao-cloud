package cn.iocoder.mall.admin.service;

import cn.iocoder.common.framework.dataobject.BaseDO;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.DataDictService;
import cn.iocoder.mall.admin.api.bo.DataDictBO;
import cn.iocoder.mall.admin.api.constant.AdminErrorCodeEnum;
import cn.iocoder.mall.admin.api.dto.DataDictAddDTO;
import cn.iocoder.mall.admin.api.dto.DataDictUpdateDTO;
import cn.iocoder.mall.admin.convert.DataDictConvert;
import cn.iocoder.mall.admin.dao.DataDictMapper;
import cn.iocoder.mall.admin.dataobject.DataDictDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 数据字典 Service
 */
@Service
@com.alibaba.dubbo.config.annotation.Service(validation = "true")
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
        dataDict.setCreateTime(new Date()).setDeleted(BaseDO.DELETED_NO);
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
        if (duplicateDataDict != null && duplicateDataDict.getId().equals(dataDictUpdateDTO.getId())) {
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
        updateDataDict.setDeleted(BaseDO.DELETED_YES);
        dataDictMapper.update(updateDataDict);
        // TODO 插入操作日志
        // 返回成功
        return CommonResult.success(true);
    }

}