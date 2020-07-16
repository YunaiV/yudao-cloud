package cn.iocoder.mall.managementweb.convert.systemlog;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.systemlog.vo.SystemExceptionLogVO;
import cn.iocoder.mall.systemservice.rpc.systemlog.dto.SystemExceptionLogPageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SystemExceptionLogConvert {

    SystemExceptionLogConvert INSTANCE = Mappers.getMapper(SystemExceptionLogConvert.class);

    SystemExceptionLogPageDTO convert(cn.iocoder.mall.managementweb.controller.systemlog.dto.SystemExceptionLogPageDTO bean);

    PageResult<SystemExceptionLogVO> convertPage(PageResult<cn.iocoder.mall.systemservice.rpc.systemlog.vo.SystemExceptionLogVO> page);

}
