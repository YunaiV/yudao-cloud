package cn.iocoder.mall.system.biz.convert.authorization;

import cn.iocoder.mall.system.biz.bo.authorization.ResourceBO;
import cn.iocoder.mall.system.biz.bo.authorization.ResourceTreeNodeBO;
import cn.iocoder.mall.system.biz.dataobject.authorization.ResourceDO;
import cn.iocoder.mall.system.biz.dto.authorization.ResourceAddDTO;
import cn.iocoder.mall.system.biz.dto.authorization.ResourceUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ResourceConvert {

    ResourceConvert INSTANCE = Mappers.getMapper(ResourceConvert.class);

    ResourceBO convert(ResourceDO bean);

    @Mapping(source = "bean", target = "node")
    ResourceTreeNodeBO convertTreeNode(ResourceDO bean);

    List<ResourceBO> convertList(List<ResourceDO> beans);

    ResourceDO convert(ResourceAddDTO bean);

    ResourceDO convert(ResourceUpdateDTO bean);

}
