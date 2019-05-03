package cn.iocoder.mall.admin.convert;

import cn.iocoder.mall.admin.api.dto.AdminAccessLogAddDTO;
import cn.iocoder.mall.admin.dataobject.AdminAccessLogDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminAccessLogConvert {

    AdminAccessLogConvert INSTANCE = Mappers.getMapper(AdminAccessLogConvert.class);

    @Mappings({})
    AdminAccessLogDO convert(AdminAccessLogAddDTO adminAccessLogAddDTO);

}