package cn.iocoder.mall.managementweb.convert.permission;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.permission.vo.RoleVO;
import cn.iocoder.mall.systemservice.rpc.permission.dto.RolePageDTO;
import cn.iocoder.mall.systemservice.rpc.permission.dto.RoleUpdateDTO;
import cn.iocoder.mall.systemservice.rpc.permission.vo.RoleCreateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleConvert {

    RoleConvert INSTANCE = Mappers.getMapper(RoleConvert.class);

    RoleCreateDTO convert(cn.iocoder.mall.managementweb.controller.permission.dto.RoleCreateDTO bean);

    RoleUpdateDTO convert(cn.iocoder.mall.managementweb.controller.permission.dto.RoleUpdateDTO bean);

    RoleVO convert(cn.iocoder.mall.systemservice.rpc.permission.vo.RoleVO bean);

    List<RoleVO> convertList(List<cn.iocoder.mall.systemservice.rpc.permission.vo.RoleVO> list);

    RolePageDTO convert(cn.iocoder.mall.managementweb.controller.permission.dto.RolePageDTO bean);

    PageResult<RoleVO> convertPage(PageResult<cn.iocoder.mall.systemservice.rpc.permission.vo.RoleVO> page);

}
