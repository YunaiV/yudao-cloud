package cn.iocoder.mall.managementweb.convert.datadict;

import cn.iocoder.mall.managementweb.controller.datadict.vo.DataDictSimpleVO;
import cn.iocoder.mall.managementweb.controller.datadict.vo.DataDictVO;
import cn.iocoder.mall.systemservice.rpc.datadict.dto.DataDictCreateDTO;
import cn.iocoder.mall.systemservice.rpc.datadict.dto.DataDictUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DataDictConvert {

    DataDictConvert INSTANCE = Mappers.getMapper(DataDictConvert.class);

    DataDictCreateDTO convert(cn.iocoder.mall.managementweb.controller.datadict.dto.DataDictCreateDTO bean);

    DataDictUpdateDTO convert(cn.iocoder.mall.managementweb.controller.datadict.dto.DataDictUpdateDTO bean);

    DataDictVO convert(cn.iocoder.mall.systemservice.rpc.datadict.vo.DataDictVO bean);

    List<DataDictVO> convertList(List<cn.iocoder.mall.systemservice.rpc.datadict.vo.DataDictVO> list);

    List<DataDictSimpleVO> convertList02(List<cn.iocoder.mall.systemservice.rpc.datadict.vo.DataDictVO> list);

}
