package cn.iocoder.mall.system.biz.convert.authorization;

import cn.iocoder.mall.system.biz.bo.authorization.RoleBO;
import cn.iocoder.mall.system.biz.dataobject.authorization.RoleDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleConvert {

    RoleConvert INSTANCE = Mappers.getMapper(RoleConvert.class);

    RoleBO convert(RoleDO bean);

    List<RoleBO> convertList(List<RoleDO> beans);

}
