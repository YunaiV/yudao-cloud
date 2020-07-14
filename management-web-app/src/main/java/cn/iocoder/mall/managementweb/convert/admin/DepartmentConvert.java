package cn.iocoder.mall.managementweb.convert.admin;

import cn.iocoder.mall.managementweb.controller.admin.vo.DepartmentTreeNodeVO;
import cn.iocoder.mall.managementweb.controller.admin.vo.DepartmentVO;
import cn.iocoder.mall.systemservice.rpc.admin.dto.DepartmentCreateDTO;
import cn.iocoder.mall.systemservice.rpc.admin.dto.DepartmentUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DepartmentConvert {

    DepartmentConvert INSTANCE = Mappers.getMapper(DepartmentConvert.class);

    DepartmentCreateDTO convert(cn.iocoder.mall.managementweb.controller.admin.dto.DepartmentCreateDTO bean);

    DepartmentUpdateDTO convert(cn.iocoder.mall.managementweb.controller.admin.dto.DepartmentUpdateDTO bean);

    List<DepartmentVO> convertList(List<cn.iocoder.mall.systemservice.rpc.admin.vo.DepartmentVO> list);

    DepartmentVO convert(cn.iocoder.mall.systemservice.rpc.admin.vo.DepartmentVO bean);

    DepartmentTreeNodeVO convertTreeNode(cn.iocoder.mall.systemservice.rpc.admin.vo.DepartmentVO bean);

}
