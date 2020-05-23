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


    @Mappings({}) // TODO FROM 芋艿 to 2447007062：注意空行哟；另外，如果不需要专门 mapping，可以不添加该注解，嘿嘿。
    PageResult<AccessLogPageResponse> convert(PageResult<AccessLogBO> result);



}
