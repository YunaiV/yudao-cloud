package cn.iocoder.mall.admin.application.convert;

import cn.iocoder.mall.admin.api.bo.ResourceBO;
import cn.iocoder.mall.admin.application.vo.AdminMenuTreeNodeVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ResourceConvert {

    ResourceConvert INSTANCE = Mappers.getMapper(ResourceConvert.class);

    @Mappings({})
    AdminMenuTreeNodeVO convert(ResourceBO resourceBO);

}
