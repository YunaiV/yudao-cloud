package cn.iocoder.mall.systemservice.convert.datadict;

import cn.iocoder.mall.systemservice.dal.mysql.dataobject.datadict.DataDictDO;
import cn.iocoder.mall.systemservice.rpc.datadict.dto.DataDictCreateDTO;
import cn.iocoder.mall.systemservice.rpc.datadict.dto.DataDictUpdateDTO;
import cn.iocoder.mall.systemservice.rpc.datadict.vo.DataDictVO;
import cn.iocoder.mall.systemservice.service.datadict.bo.DataDictBO;
import cn.iocoder.mall.systemservice.service.datadict.bo.DataDictCreateBO;
import cn.iocoder.mall.systemservice.service.datadict.bo.DataDictUpdateBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DataDictConvert {

    DataDictConvert INSTANCE = Mappers.getMapper(DataDictConvert.class);

    DataDictDO convert(DataDictCreateBO bean);

    DataDictBO convert(DataDictDO bean);

    DataDictDO convert(DataDictUpdateBO bean);

    List<DataDictBO> convertList(List<DataDictDO> list);

    DataDictCreateBO convert(DataDictCreateDTO bean);

    DataDictUpdateBO convert(DataDictUpdateDTO bean);

    DataDictVO convert(DataDictBO bean);

    List<DataDictVO> convertList02(List<DataDictBO> list);

}
