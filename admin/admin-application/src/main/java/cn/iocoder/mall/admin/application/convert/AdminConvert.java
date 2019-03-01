package cn.iocoder.mall.admin.application.convert;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.bo.AdminBO;
import cn.iocoder.mall.admin.api.bo.AdminPageBO;
import cn.iocoder.mall.admin.application.vo.AdminInfoVO;
import cn.iocoder.mall.admin.application.vo.AdminPageVO;
import cn.iocoder.mall.admin.application.vo.AdminVO;
import cn.iocoder.mall.admin.sdk.context.AdminSecurityContext;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminConvert {

    AdminConvert INSTANCE = Mappers.getMapper(AdminConvert.class);

    @Mappings({})
    AdminInfoVO convert(AdminSecurityContext adminSecurityContext);

    @Mappings({})
    AdminVO convert(AdminBO adminBO);

    @Mappings({})
    CommonResult<AdminPageVO> convert(CommonResult<AdminPageBO> result);

}