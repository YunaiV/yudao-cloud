package cn.iocoder.mall.systemservice.convert.systemlog;

import cn.iocoder.mall.systemservice.dal.mysql.dataobject.systemlog.AccessLogDO;
import cn.iocoder.mall.systemservice.dal.mysql.dataobject.systemlog.ExceptionLogDO;
import cn.iocoder.mall.systemservice.rpc.systemlog.dto.AccessLogAddDTO;
import cn.iocoder.mall.systemservice.rpc.systemlog.dto.ExceptionLogAddDTO;
import cn.iocoder.mall.systemservice.service.systemlog.bo.AccessLogAddBO;
import cn.iocoder.mall.systemservice.service.systemlog.bo.ExceptionLogAddBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SystemLogConvert {

    SystemLogConvert INSTANCE = Mappers.getMapper(SystemLogConvert.class);

    AccessLogDO convert(AccessLogAddBO bean);

    ExceptionLogDO convert(ExceptionLogAddBO bean);

    AccessLogAddBO convert(AccessLogAddDTO bean);

    ExceptionLogAddBO convert(ExceptionLogAddDTO bean);

//    AccessLogDO convert(AccessLogAddDTO bean);
//
//    ExceptionLogDO convert(ExceptionLogAddDTO bean);
//
//    @Mapping(source = "records", target = "list")
//    PageResult<AccessLogBO> convertPage(IPage<AccessLogDO> page);
//
//    AccessLogBO convert(AccessLogDO bean);

}
