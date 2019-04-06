package cn.iocoder.mall.user.application.convert;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.user.application.vo.admins.AdminsUserPageVO;
import cn.iocoder.mall.user.application.vo.users.UsersUserVO;
import cn.iocoder.mall.user.api.bo.UserBO;
import cn.iocoder.mall.user.api.bo.UserPageBO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    @Mappings({})
    CommonResult<AdminsUserPageVO> convert(CommonResult<UserPageBO> result);

    @Mappings({})
    CommonResult<UsersUserVO> convert2(CommonResult<UserBO> result);

}