package cn.iocoder.mall.managementweb.convert.systemlog;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.systemlog.vo.SystemExceptionLogDetailVO;
import cn.iocoder.mall.managementweb.controller.systemlog.vo.SystemExceptionLogVO;
import cn.iocoder.mall.systemservice.rpc.admin.vo.AdminVO;
import cn.iocoder.mall.systemservice.rpc.systemlog.dto.SystemExceptionLogPageDTO;
import cn.iocoder.mall.systemservice.rpc.systemlog.dto.SystemExceptionLogProcessDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SystemExceptionLogConvert {

    SystemExceptionLogConvert INSTANCE = Mappers.getMapper(SystemExceptionLogConvert.class);

    SystemExceptionLogPageDTO convert(cn.iocoder.mall.managementweb.controller.systemlog.dto.SystemExceptionLogPageDTO bean);

    PageResult<SystemExceptionLogVO> convertPage(PageResult<cn.iocoder.mall.systemservice.rpc.systemlog.vo.SystemExceptionLogVO> page);

    SystemExceptionLogDetailVO convert(cn.iocoder.mall.systemservice.rpc.systemlog.vo.SystemExceptionLogVO bean);

    SystemExceptionLogDetailVO.Admin convert(AdminVO bean);

    SystemExceptionLogProcessDTO convert(cn.iocoder.mall.managementweb.controller.systemlog.dto.SystemExceptionLogProcessDTO bean);

}
