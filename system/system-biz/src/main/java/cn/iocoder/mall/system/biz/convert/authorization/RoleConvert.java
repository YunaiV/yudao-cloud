package cn.iocoder.mall.system.biz.convert.authorization;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.system.biz.bo.authorization.RoleBO;
import cn.iocoder.mall.system.biz.dataobject.authorization.RoleDO;
import cn.iocoder.mall.system.biz.dto.authorization.RoleAddDTO;
import cn.iocoder.mall.system.biz.dto.authorization.RoleUpdateDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleConvert {

    RoleConvert INSTANCE = Mappers.getMapper(RoleConvert.class);

    RoleBO convert(RoleDO bean);

    List<RoleBO> convertList(List<RoleDO> beans);

    @Mapping(source = "records", target = "list")
    PageResult<RoleBO> convertPage(IPage<RoleDO> page);

    RoleDO convert(RoleAddDTO bean);

    RoleDO convert(RoleUpdateDTO bean);

}
