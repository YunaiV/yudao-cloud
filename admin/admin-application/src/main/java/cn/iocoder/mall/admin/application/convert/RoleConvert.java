package cn.iocoder.mall.admin.application.convert;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.bo.RoleBO;
import cn.iocoder.mall.admin.api.bo.RolePageBO;
import cn.iocoder.mall.admin.application.vo.RolePageVO;
import cn.iocoder.mall.admin.application.vo.RoleVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleConvert {

    RoleConvert INSTANCE = Mappers.getMapper(RoleConvert.class);

    @Mappings({})
    RoleVO convert(RoleBO roleBO);

    @Mappings({})
    CommonResult<RoleVO> convert(CommonResult<RoleBO> resourceBO);

    @Mappings({})
    CommonResult<RolePageVO> convert2(CommonResult<RolePageBO> resourceBO);

}