package cn.iocoder.mall.admin.application.convert;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.admin.api.bo.role.RoleBO;
import cn.iocoder.mall.admin.api.bo.admin.AdminBO;
import cn.iocoder.mall.admin.application.vo.admin.AdminInfoVO;
import cn.iocoder.mall.admin.application.vo.admin.AdminRoleVO;
import cn.iocoder.mall.admin.application.vo.admin.AdminVO;
import cn.iocoder.mall.admin.sdk.context.AdminSecurityContext;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper
public interface AdminConvert {

    AdminConvert INSTANCE = Mappers.getMapper(AdminConvert.class);

    @Mappings({})
    AdminInfoVO convert(AdminSecurityContext adminSecurityContext);

    @Mappings({})
    AdminVO convert(AdminBO adminBO);

    @Mappings({})
    CommonResult<AdminVO> convert2(CommonResult<AdminBO> result);

    @Mappings({})
    List<AdminRoleVO> convert(List<RoleBO> roleList);

    @Mappings({})
    PageResult<AdminVO> convertAdminVOPage(PageResult<AdminBO> page);

    List<AdminVO.Role> convertAdminVORoleList(Collection<RoleBO> list);

}
