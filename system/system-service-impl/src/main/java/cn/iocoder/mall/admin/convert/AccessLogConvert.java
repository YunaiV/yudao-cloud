package cn.iocoder.mall.admin.convert;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.admin.api.bo.systemlog.AccessLogBO;
import cn.iocoder.mall.admin.api.bo.systemlog.AccessLogPageBO;
import cn.iocoder.mall.admin.api.dto.systemlog.AccessLogAddDTO;
import cn.iocoder.mall.admin.api.dto.systemlog.ExceptionLogAddDTO;
import cn.iocoder.mall.admin.dataobject.AccessLogDO;
import cn.iocoder.mall.admin.dataobject.ExceptionLogDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccessLogConvert {

    AccessLogConvert INSTANCE = Mappers.getMapper(AccessLogConvert.class);

    @Mappings({})
    AccessLogDO convert(AccessLogAddDTO accessLogAddDTO);

    @Mappings({})
    ExceptionLogDO convert(ExceptionLogAddDTO exceptionLogAddDTO);

    @Mappings({
            @Mapping(source = "records", target = "list"),
    })
    PageResult<AccessLogBO> convert(IPage<AccessLogDO> page);

}
