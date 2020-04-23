package cn.iocoder.mall.system.biz.convert.authorization;

import cn.iocoder.mall.system.biz.bo.authorization.ResourceBO;
import cn.iocoder.mall.system.biz.dataobject.authorization.ResourceDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ResourceConvert {

    ResourceConvert INSTANCE = Mappers.getMapper(ResourceConvert.class);

    ResourceBO convert(ResourceDO bean);

    List<ResourceBO> convertList(List<ResourceDO> beans);

}
