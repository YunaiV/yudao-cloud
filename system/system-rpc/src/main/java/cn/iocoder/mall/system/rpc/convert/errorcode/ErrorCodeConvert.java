package cn.iocoder.mall.system.rpc.convert.errorcode;

import cn.iocoder.mall.system.biz.bo.errorcode.ErrorCodeBO;
import cn.iocoder.mall.system.biz.dto.errorcode.ErrorCodeAddDTO;
import cn.iocoder.mall.system.rpc.request.errorcode.ErrorCodeAddRequest;
import cn.iocoder.mall.system.rpc.response.errorcode.ErrorCodeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ErrorCodeConvert {

    ErrorCodeConvert INSTANCE = Mappers.getMapper(ErrorCodeConvert.class);

    List<ErrorCodeResponse> convert(List<ErrorCodeBO> bean);

    ErrorCodeAddDTO convert(ErrorCodeAddRequest errorCodeAddRequest);

    List<ErrorCodeAddDTO> convertList(List<ErrorCodeAddRequest> bean);

}
