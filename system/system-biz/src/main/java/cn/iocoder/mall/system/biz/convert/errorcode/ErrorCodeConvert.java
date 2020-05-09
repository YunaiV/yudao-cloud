package cn.iocoder.mall.system.biz.convert.errorcode;

import cn.iocoder.mall.system.biz.bo.errorcode.ErrorCodeBO;
import cn.iocoder.mall.system.biz.dataobject.errorcode.ErrorCodeDO;
import cn.iocoder.mall.system.biz.dto.errorcode.ErrorCodeAddDTO;
import cn.iocoder.mall.system.biz.dto.errorcode.ErrorCodeDTO;
import cn.iocoder.mall.system.biz.dto.errorcode.ErrorCodeUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author ding
 */
@Mapper
public interface ErrorCodeConvert {

    ErrorCodeConvert INSTANCE = Mappers.getMapper(ErrorCodeConvert.class);

    ErrorCodeDO convert(ErrorCodeDTO bean);

    ErrorCodeBO convert(ErrorCodeDO bean);

    List<ErrorCodeBO> convertList(List<ErrorCodeDO> beans);

//    @Mapping(source = "records", target = "list")
//    PageResult<ErrorCodeBO> convertPage(IPage<ErrorCodeDO> page);

    ErrorCodeDO convert(ErrorCodeAddDTO bean);

    ErrorCodeDO convert(ErrorCodeUpdateDTO bean);

}
