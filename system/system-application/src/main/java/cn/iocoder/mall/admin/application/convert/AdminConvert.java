package cn.iocoder.mall.admin.application.convert;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.bo.admin.AdminBO;
import cn.iocoder.mall.admin.api.bo.admin.AdminPageBO;
import cn.iocoder.mall.admin.api.bo.RoleBO;
import cn.iocoder.mall.admin.application.vo.AdminInfoVO;
import cn.iocoder.mall.admin.application.vo.AdminPageVO;
import cn.iocoder.mall.admin.application.vo.AdminRoleVO;
import cn.iocoder.mall.admin.application.vo.AdminVO;
import cn.iocoder.mall.admin.sdk.context.AdminSecurityContext;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

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
    CommonResult<AdminPageVO> convert(CommonResult<AdminPageBO> result);

    @Mappings({})
    List<AdminRoleVO> convert(List<RoleBO> roleList);

}
