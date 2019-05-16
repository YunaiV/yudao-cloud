package cn.iocoder.mall.admin.convert;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.admin.api.bo.admin.AdminAuthenticationBO;
import cn.iocoder.mall.admin.api.bo.admin.AdminBO;
import cn.iocoder.mall.admin.api.dto.admin.AdminAddDTO;
import cn.iocoder.mall.admin.api.dto.admin.AdminUpdateDTO;
import cn.iocoder.mall.admin.dataobject.AdminDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AdminConvert {

    AdminConvert INSTANCE = Mappers.getMapper(AdminConvert.class);

    @Mappings({})
    AdminBO convert(AdminDO adminDO);

    @Mappings({})
    AdminAuthenticationBO convert2(AdminDO admin);

    @Mappings({})
    AdminDO convert(AdminAddDTO adminAddDTO);

    @Mappings({})
    AdminDO convert(AdminUpdateDTO adminUpdateDTO);

    @Mappings({})
    List<AdminBO> convert(List<AdminDO> adminBOs);

    @Mappings({
            @Mapping(source = "records", target = "list"),
    })
    PageResult<AdminBO> convert(IPage<AdminDO> page);

}
