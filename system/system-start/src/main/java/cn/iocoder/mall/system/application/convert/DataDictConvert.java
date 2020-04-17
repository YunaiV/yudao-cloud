package cn.iocoder.mall.system.application.convert;

import cn.iocoder.mall.system.api.bo.datadict.DataDictBO;
import cn.iocoder.mall.system.application.vo.datadict.DataDictEnumVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DataDictConvert {

    DataDictConvert INSTANCE = Mappers.getMapper(DataDictConvert.class);

    @Mappings({})
    List<DataDictEnumVO.Value> convert2(List<DataDictBO> dataDictBOs);

}
