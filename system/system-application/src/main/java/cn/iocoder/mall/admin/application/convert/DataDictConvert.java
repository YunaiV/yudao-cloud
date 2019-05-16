package cn.iocoder.mall.admin.application.convert;

import cn.iocoder.mall.admin.api.bo.datadict.DataDictBO;
import cn.iocoder.mall.admin.application.vo.datadict.DataDictEnumVO;
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
