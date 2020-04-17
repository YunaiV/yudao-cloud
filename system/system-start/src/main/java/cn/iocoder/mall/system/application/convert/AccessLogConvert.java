package cn.iocoder.mall.system.application.convert;

import cn.iocoder.mall.system.api.bo.systemlog.AccessLogBO;
import cn.iocoder.mall.system.api.bo.systemlog.AccessLogPageBO;
import cn.iocoder.mall.system.application.vo.log.AccessLogPageVo;
import cn.iocoder.mall.system.application.vo.log.AccessLogVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author:ycjx
 * @descriptio
 * @create:2019-06-23 17:36
 */
@Mapper
public interface AccessLogConvert {


    AccessLogConvert INSTANCE = Mappers.getMapper(AccessLogConvert.class);

    @Mappings({})
    AccessLogPageVo convert(AccessLogPageBO result);

    @Mappings({})
    AccessLogVo convert(AccessLogBO result);



}
