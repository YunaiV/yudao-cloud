package cn.iocoder.mall.managementweb.convert.admin;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.admin.dto.AdminUpdateInfoDTO;
import cn.iocoder.mall.managementweb.controller.admin.dto.AdminUpdateStatusDTO;
import cn.iocoder.mall.managementweb.controller.admin.vo.AdminPageItemVO;
import cn.iocoder.mall.systemservice.rpc.admin.dto.AdminCreateDTO;
import cn.iocoder.mall.systemservice.rpc.admin.dto.AdminPageDTO;
import cn.iocoder.mall.systemservice.rpc.admin.dto.AdminUpdateDTO;
import cn.iocoder.mall.systemservice.rpc.admin.vo.AdminVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminConvert {

    AdminConvert INSTANCE = Mappers.getMapper(AdminConvert.class);

    AdminCreateDTO convert(cn.iocoder.mall.managementweb.controller.admin.dto.AdminCreateDTO bean);

    AdminUpdateDTO convert(AdminUpdateInfoDTO bean);

    AdminUpdateDTO convert(AdminUpdateStatusDTO bean);

    AdminPageDTO convert(cn.iocoder.mall.managementweb.controller.admin.dto.AdminPageDTO bean);

    PageResult<AdminPageItemVO> convert(PageResult<AdminVO> pageResultData);

}
