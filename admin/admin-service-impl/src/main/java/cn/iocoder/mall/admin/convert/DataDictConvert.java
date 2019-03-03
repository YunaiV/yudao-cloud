package cn.iocoder.mall.admin.convert;

import cn.iocoder.mall.admin.api.bo.DataDictBO;
import cn.iocoder.mall.admin.api.dto.DataDictAddDTO;
import cn.iocoder.mall.admin.api.dto.DataDictUpdateDTO;
import cn.iocoder.mall.admin.dataobject.DataDictDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DataDictConvert {

    DataDictConvert INSTANCE = Mappers.getMapper(DataDictConvert.class);

    DataDictDO convert(DataDictAddDTO dataDictAddDTO);

    DataDictDO convert(DataDictUpdateDTO dataDictUpdateDTO);

    DataDictBO convert(DataDictDO dataDictDO);

    List<DataDictBO> convert(List<DataDictDO> dataDictDOs);

}