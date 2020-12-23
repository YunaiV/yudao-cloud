package cn.iocoder.mall.systemservice.convert.permission;

import cn.iocoder.mall.systemservice.dal.mysql.dataobject.permission.ResourceDO;
import cn.iocoder.mall.systemservice.rpc.permission.dto.ResourceCreateDTO;
import cn.iocoder.mall.systemservice.rpc.permission.dto.ResourceUpdateDTO;
import cn.iocoder.mall.systemservice.rpc.permission.vo.ResourceVO;
import cn.iocoder.mall.systemservice.service.permission.bo.ResourceBO;
import cn.iocoder.mall.systemservice.service.permission.bo.ResourceCreateBO;
import cn.iocoder.mall.systemservice.service.permission.bo.ResourceUpdateBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ResourceConvert {

    ResourceConvert INSTANCE = Mappers.getMapper(ResourceConvert.class);

    ResourceDO convert(ResourceCreateBO bean);

    ResourceBO convert(ResourceDO bean);

    ResourceDO convert(ResourceUpdateBO bean);

    ResourceCreateBO convert(ResourceCreateDTO bean);

    ResourceVO convert(ResourceBO bean);

    ResourceUpdateBO convert(ResourceUpdateDTO bean);

    List<ResourceBO> convertList(List<ResourceDO> list);
    List<ResourceVO> convertList02(List<ResourceBO> list);

}
