package cn.iocoder.mall.admin.application.convert;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.bo.resource.ResourceBO;
import cn.iocoder.mall.admin.application.vo.admin.AdminMenuTreeNodeVO;
import cn.iocoder.mall.admin.application.vo.resource.ResourceTreeNodeVO;
import cn.iocoder.mall.admin.application.vo.resource.ResourceVO;
import cn.iocoder.mall.admin.application.vo.role.RoleResourceTreeNodeVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ResourceConvert {

    ResourceConvert INSTANCE = Mappers.getMapper(ResourceConvert.class);

    @Mappings({})
    AdminMenuTreeNodeVO convert(ResourceBO resourceBO);

    @Mappings({})
    ResourceTreeNodeVO convert2(ResourceBO resourceBO);

    @Mappings({})
    RoleResourceTreeNodeVO convert4(ResourceBO resourceBO);

    @Mappings({})
    ResourceVO convert3(ResourceBO resourceBO);

    @Mappings({})
    CommonResult<ResourceVO> convert3(CommonResult<ResourceBO> resourceBO);

}
