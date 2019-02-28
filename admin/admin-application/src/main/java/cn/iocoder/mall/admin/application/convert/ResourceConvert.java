package cn.iocoder.mall.admin.application.convert;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.bo.ResourceBO;
import cn.iocoder.mall.admin.application.vo.AdminMenuTreeNodeVO;
import cn.iocoder.mall.admin.application.vo.ResourceTreeNodeVO;
import cn.iocoder.mall.admin.application.vo.ResourceVO;
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
    ResourceVO convert3(ResourceBO resourceBO);

    @Mappings({})
    CommonResult<ResourceVO> convert3(CommonResult<ResourceBO> resourceBO);

}