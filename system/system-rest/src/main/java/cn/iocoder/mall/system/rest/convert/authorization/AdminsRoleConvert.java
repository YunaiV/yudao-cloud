package cn.iocoder.mall.system.rest.convert.authorization;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.system.biz.bo.authorization.RoleBO;
import cn.iocoder.mall.system.biz.dto.authorization.RoleAddDTO;
import cn.iocoder.mall.system.biz.dto.authorization.RolePageDTO;
import cn.iocoder.mall.system.biz.dto.authorization.RoleUpdateDTO;
import cn.iocoder.mall.system.rest.request.authorization.AdminsRoleAddRequest;
import cn.iocoder.mall.system.rest.request.authorization.AdminsRolePageRequest;
import cn.iocoder.mall.system.rest.request.authorization.AdminsRoleUpdateRequest;
import cn.iocoder.mall.system.rest.response.authorization.AdminsRolePageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminsRoleConvert {

    AdminsRoleConvert INSTANCE = Mappers.getMapper(AdminsRoleConvert.class);

    RoleAddDTO convert(AdminsRoleAddRequest bean);

    RoleUpdateDTO convert(AdminsRoleUpdateRequest bean);

    RolePageDTO convert(AdminsRolePageRequest bean);

    PageResult<AdminsRolePageResponse> convertPage(PageResult<RoleBO> bean);

}
