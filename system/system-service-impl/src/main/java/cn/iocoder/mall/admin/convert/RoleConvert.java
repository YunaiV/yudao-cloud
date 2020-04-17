package cn.iocoder.mall.admin.convert;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.system.api.bo.role.RoleBO;
import cn.iocoder.mall.system.api.dto.role.RoleAddDTO;
import cn.iocoder.mall.system.api.dto.role.RoleUpdateDTO;
import cn.iocoder.mall.admin.dataobject.RoleDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleConvert {

    RoleConvert INSTANCE = Mappers.getMapper(RoleConvert.class);

    @Mappings({})
    RoleDO convert(RoleAddDTO roleAddDTO);

    @Mappings({})
    RoleDO convert(RoleUpdateDTO roleUpdateDTO);

    @Mappings({})
    RoleBO convert(RoleDO roleDO);

    @Mappings({})
    List<RoleBO> convert(List<RoleDO> roleDOs);

    @Mappings({
            @Mapping(source = "records", target = "list"),
    })
    PageResult<RoleBO> convert(IPage<RoleDO> page);

}
