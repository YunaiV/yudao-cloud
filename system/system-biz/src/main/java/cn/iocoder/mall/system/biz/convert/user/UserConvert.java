package cn.iocoder.mall.system.biz.convert.user;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.system.biz.bo.ouath2.OAuth2AuthenticateBO;
import cn.iocoder.mall.system.biz.bo.user.UserAuthenticateBO;
import cn.iocoder.mall.system.biz.bo.user.UserBO;
import cn.iocoder.mall.system.biz.dataobject.admin.AdminDO;
import cn.iocoder.mall.system.biz.dataobject.user.UserDO;
import cn.iocoder.mall.system.biz.dto.user.UserUpdateDTO;
import cn.iocoder.mall.system.biz.dto.user.UserUpdateStatusDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserBO convert(UserDO bean);

    /**
     * 用户分页列表 - DOPage转换BO
     * @param userDOPage
     * @return
     */
    @Mapping(source = "records", target = "list")
    PageResult<UserBO> convertToPage(IPage<UserDO> userDOPage);

    /**
     * 更新用户信息  - DTO转换DO
     * @param userUpdateDTO
     * @return
     */
    UserDO convertToUserDO(UserUpdateDTO userUpdateDTO);

    /**
     * 更新用户状态  - DTO转换DO
     * @param userUpdateStatusDTO
     * @return
     */
    UserDO convertToUserDO(UserUpdateStatusDTO userUpdateStatusDTO);

}
