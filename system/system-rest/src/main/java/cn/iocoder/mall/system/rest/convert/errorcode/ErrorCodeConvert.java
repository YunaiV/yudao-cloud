package cn.iocoder.mall.system.rest.convert.errorcode;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.system.biz.bo.authorization.RoleBO;
import cn.iocoder.mall.system.biz.bo.errorcode.ErrorCodeBO;
import cn.iocoder.mall.system.biz.dto.authorization.RoleAddDTO;
import cn.iocoder.mall.system.biz.dto.authorization.RolePageDTO;
import cn.iocoder.mall.system.biz.dto.authorization.RoleUpdateDTO;
import cn.iocoder.mall.system.biz.dto.errorcode.ErrorCodeAddDTO;
import cn.iocoder.mall.system.biz.dto.errorcode.ErrorCodePageDTO;
import cn.iocoder.mall.system.biz.dto.errorcode.ErrorCodeUpdateDTO;
import cn.iocoder.mall.system.rest.convert.authorization.AdminsRoleConvert;
import cn.iocoder.mall.system.rest.request.authorization.AdminsRoleAddRequest;
import cn.iocoder.mall.system.rest.request.authorization.AdminsRolePageRequest;
import cn.iocoder.mall.system.rest.request.authorization.AdminsRoleUpdateRequest;
import cn.iocoder.mall.system.rest.request.errorcode.ErrorCodeAddRequest;
import cn.iocoder.mall.system.rest.request.errorcode.ErrorCodePageRequest;
import cn.iocoder.mall.system.rest.request.errorcode.ErrorCodePageResponse;
import cn.iocoder.mall.system.rest.request.errorcode.ErrorCodeUpdateRequest;
import cn.iocoder.mall.system.rest.response.authorization.AdminsRolePageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author ding
 */
@Mapper
public interface ErrorCodeConvert {
    ErrorCodeConvert INSTANCE = Mappers.getMapper(ErrorCodeConvert.class);

    ErrorCodeAddDTO convert(ErrorCodeAddRequest bean);

    ErrorCodeUpdateDTO convert(ErrorCodeUpdateRequest bean);

    ErrorCodePageDTO convert(ErrorCodePageRequest bean);

    PageResult<ErrorCodePageResponse> convertPage(PageResult<ErrorCodeBO> bean);
}
