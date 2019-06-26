package cn.iocoder.mall.admin.application.convert;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.admin.api.bo.admin.AdminBO;
import cn.iocoder.mall.admin.api.bo.systemlog.AccessLogBO;
import cn.iocoder.mall.admin.api.bo.systemlog.AccessLogPageBO;
import cn.iocoder.mall.admin.application.vo.log.AccessLogPageVo;
import cn.iocoder.mall.admin.application.vo.log.AccessLogVo;
import cn.iocoder.mall.admin.dataobject.AccessLogDO;
import cn.iocoder.mall.admin.dataobject.AdminDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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
