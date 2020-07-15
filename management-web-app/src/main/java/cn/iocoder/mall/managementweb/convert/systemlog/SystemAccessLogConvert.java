package cn.iocoder.mall.managementweb.convert.systemlog;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.systemlog.vo.SystemAccessLogVO;
import cn.iocoder.mall.systemservice.rpc.systemlog.vo.SystemAccessLogPageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SystemAccessLogConvert {

    SystemAccessLogConvert INSTANCE = Mappers.getMapper(SystemAccessLogConvert.class);

    SystemAccessLogPageDTO convert(cn.iocoder.mall.managementweb.controller.systemlog.dto.SystemAccessLogPageDTO bean);

    PageResult<SystemAccessLogVO> convertPage(PageResult<cn.iocoder.mall.systemservice.rpc.systemlog.vo.SystemAccessLogVO> page);

}
