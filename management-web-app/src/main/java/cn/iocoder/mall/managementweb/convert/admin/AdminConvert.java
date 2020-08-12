package cn.iocoder.mall.managementweb.convert.admin;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.admin.dto.AdminUpdateInfoDTO;
import cn.iocoder.mall.managementweb.controller.admin.dto.AdminUpdateStatusDTO;
import cn.iocoder.mall.managementweb.controller.admin.vo.AdminPageItemVO;
import cn.iocoder.mall.systemservice.rpc.admin.dto.AdminCreateDTO;
import cn.iocoder.mall.systemservice.rpc.admin.dto.AdminPageDTO;
import cn.iocoder.mall.systemservice.rpc.admin.dto.AdminUpdateDTO;
import cn.iocoder.mall.systemservice.rpc.admin.vo.AdminVO;
import cn.iocoder.mall.systemservice.rpc.admin.vo.DepartmentVO;
import cn.iocoder.mall.systemservice.rpc.permission.vo.RoleVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AdminConvert {

    AdminConvert INSTANCE = Mappers.getMapper(AdminConvert.class);

    AdminCreateDTO convert(cn.iocoder.mall.managementweb.controller.admin.dto.AdminCreateDTO bean);

    AdminUpdateDTO convert(AdminUpdateInfoDTO bean);

    @Mapping(source = "adminId", target = "id")
    AdminUpdateDTO convert(AdminUpdateStatusDTO bean);

    AdminPageDTO convert(cn.iocoder.mall.managementweb.controller.admin.dto.AdminPageDTO bean);

    PageResult<AdminPageItemVO> convert(PageResult<AdminVO> pageResultData);

    cn.iocoder.mall.managementweb.controller.admin.vo.AdminVO convert(AdminVO bean);

    AdminPageItemVO convert02(AdminVO adminVO);
    AdminPageItemVO.Department convert(DepartmentVO bean);
    List<AdminPageItemVO.Role> convertList(List<RoleVO> list);

}
