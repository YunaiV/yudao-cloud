package cn.iocoder.mall.admin.application.convert;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.bo.DataDictBO;
import cn.iocoder.mall.admin.application.vo.DataDictVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DataDictConvert {

    DataDictConvert INSTANCE = Mappers.getMapper(DataDictConvert.class);

    @Mappings({})
    DataDictVO convert(DataDictBO dataDictBO);

    @Mappings({})
    List<DataDictVO> convert(List<DataDictBO> dataDictBOs);

    @Mappings({})
    CommonResult<List<DataDictVO>> convert(CommonResult<List<DataDictBO>> result);

    @Mappings({})
    CommonResult<DataDictVO> convert2(CommonResult<DataDictBO> result);

}