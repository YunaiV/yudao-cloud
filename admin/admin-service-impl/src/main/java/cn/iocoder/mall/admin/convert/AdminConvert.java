package cn.iocoder.mall.admin.convert;

import cn.iocoder.mall.admin.api.bo.AdminBO;
import cn.iocoder.mall.admin.api.dto.AdminAddDTO;
import cn.iocoder.mall.admin.api.dto.AdminUpdateDTO;
import cn.iocoder.mall.admin.dataobject.AdminDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AdminConvert {

    AdminConvert INSTANCE = Mappers.getMapper(AdminConvert.class);

    @Mappings({})
    AdminBO convert(AdminDO adminDO);

    @Mappings({})
    AdminDO convert(AdminAddDTO adminAddDTO);

    @Mappings({})
    AdminDO convert(AdminUpdateDTO adminUpdateDTO);

    @Mappings({})
    List<AdminBO> convert(List<AdminDO> adminBOs);

}