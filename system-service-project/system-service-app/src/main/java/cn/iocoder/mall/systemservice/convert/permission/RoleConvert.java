package cn.iocoder.mall.systemservice.convert.permission;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.systemservice.dal.mysql.dataobject.permission.RoleDO;
import cn.iocoder.mall.systemservice.rpc.permission.dto.RolePageDTO;
import cn.iocoder.mall.systemservice.rpc.permission.dto.RoleUpdateDTO;
import cn.iocoder.mall.systemservice.rpc.permission.vo.RoleCreateDTO;
import cn.iocoder.mall.systemservice.rpc.permission.vo.RoleVO;
import cn.iocoder.mall.systemservice.service.permission.bo.RoleBO;
import cn.iocoder.mall.systemservice.service.permission.bo.RoleCreateBO;
import cn.iocoder.mall.systemservice.service.permission.bo.RolePageBO;
import cn.iocoder.mall.systemservice.service.permission.bo.RoleUpdateBO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleConvert {

    RoleConvert INSTANCE = Mappers.getMapper(RoleConvert.class);

    RoleDO convert(RoleCreateBO bean);

    RoleBO convert(RoleDO bean);

    RoleDO convert(RoleUpdateBO bean);

    RoleCreateBO convert(RoleCreateDTO bean);

    RoleUpdateBO convert(RoleUpdateDTO bean);

    RolePageBO convert(RolePageDTO bean);

    RoleVO convert(RoleBO bean);

    List<RoleBO> convertList(List<RoleDO> list);

    List<RoleVO> convertList02(List<RoleBO> list);

    @Mapping(source = "records", target = "list")
    PageResult<RoleBO> convertPage(IPage<RoleDO> page);

    PageResult<RoleVO> convertPage(PageResult<RoleBO> page);

}
