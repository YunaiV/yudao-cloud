package cn.iocoder.mall.system.rest.convert.systemlog;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.system.biz.bo.systemlog.AccessLogBO;
import cn.iocoder.mall.system.rest.response.systemlog.AccessLogPageResponse;
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
    PageResult<AccessLogPageResponse> convert(PageResult<AccessLogBO> result);



}
