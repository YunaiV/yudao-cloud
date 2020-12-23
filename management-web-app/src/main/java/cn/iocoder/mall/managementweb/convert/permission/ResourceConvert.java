package cn.iocoder.mall.managementweb.convert.permission;

import cn.iocoder.mall.managementweb.controller.passport.vo.PassportAdminMenuTreeNodeVO;
import cn.iocoder.mall.managementweb.controller.permission.vo.ResourceTreeNodeVO;
import cn.iocoder.mall.managementweb.controller.permission.vo.ResourceVO;
import cn.iocoder.mall.systemservice.rpc.permission.dto.ResourceCreateDTO;
import cn.iocoder.mall.systemservice.rpc.permission.dto.ResourceUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ResourceConvert {

    ResourceConvert INSTANCE = Mappers.getMapper(ResourceConvert.class);

    ResourceCreateDTO convert(cn.iocoder.mall.managementweb.controller.permission.dto.ResourceCreateDTO bean);

    ResourceUpdateDTO convert(cn.iocoder.mall.managementweb.controller.permission.dto.ResourceUpdateDTO bean);

    ResourceVO convert(cn.iocoder.mall.systemservice.rpc.permission.vo.ResourceVO bean);

    List<ResourceVO> convertList(List<cn.iocoder.mall.systemservice.rpc.permission.vo.ResourceVO> list);

    ResourceTreeNodeVO convertTreeNode(cn.iocoder.mall.systemservice.rpc.permission.vo.ResourceVO bean);

    List<PassportAdminMenuTreeNodeVO> convert(List<ResourceTreeNodeVO> list);

}
