package cn.iocoder.mall.managementweb.convert.errorcode;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.errorcode.vo.ErrorCodeVO;
import cn.iocoder.mall.systemservice.rpc.errorcode.dto.ErrorCodeCreateDTO;
import cn.iocoder.mall.systemservice.rpc.errorcode.dto.ErrorCodePageDTO;
import cn.iocoder.mall.systemservice.rpc.errorcode.dto.ErrorCodeUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ErrorCodeConvert {

    ErrorCodeConvert INSTANCE = Mappers.getMapper(ErrorCodeConvert.class);

    ErrorCodeCreateDTO convert(cn.iocoder.mall.managementweb.controller.errorcode.dto.ErrorCodeCreateDTO bean);

    ErrorCodeUpdateDTO convert(cn.iocoder.mall.managementweb.controller.errorcode.dto.ErrorCodeUpdateDTO bean);

    ErrorCodeVO convert(cn.iocoder.mall.systemservice.rpc.errorcode.vo.ErrorCodeVO bean);

    List<ErrorCodeVO> convertList(List<cn.iocoder.mall.systemservice.rpc.errorcode.vo.ErrorCodeVO> list);

    PageResult<ErrorCodeVO> convertPage(PageResult<cn.iocoder.mall.systemservice.rpc.errorcode.vo.ErrorCodeVO> page);

    ErrorCodePageDTO convert(cn.iocoder.mall.managementweb.controller.errorcode.dto.ErrorCodePageDTO bean);

}
