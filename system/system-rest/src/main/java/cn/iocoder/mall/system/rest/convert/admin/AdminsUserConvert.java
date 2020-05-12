package cn.iocoder.mall.system.rest.convert.admin;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.system.biz.bo.user.UserBO;
import cn.iocoder.mall.system.biz.dto.user.UserPageDTO;
import cn.iocoder.mall.system.biz.dto.user.UserUpdateDTO;
import cn.iocoder.mall.system.biz.dto.user.UserUpdateStatusDTO;
import cn.iocoder.mall.system.rest.request.admin.AdminsUserPageRequest;
import cn.iocoder.mall.system.rest.request.admin.AdminsUserUpdateRequest;
import cn.iocoder.mall.system.rest.request.admin.AdminsUserUpdateStatusRequest;
import cn.iocoder.mall.system.rest.response.admin.AdminsUserPageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author: jiangweifan
 * @Date: 2020/5/12
 * @Description: 管理后台 - 用户数据转换
 */
@Mapper
public interface AdminsUserConvert {

    AdminsUserConvert INSTANCE = Mappers.getMapper(AdminsUserConvert.class);

    /**
     * 分页获取用户列表  - Request转DTO
     * @param adminsUserPageRequest
     * @return
     */
    UserPageDTO convertToPageDTO(AdminsUserPageRequest adminsUserPageRequest);

    /**
     * 更新用户信息  - Request转DTO
     * @param adminsUserUpdateRequest
     * @return
     */
    UserUpdateDTO convertToUpdateDTO(AdminsUserUpdateRequest adminsUserUpdateRequest);

    /**
     * 更新用户状态  - Request转DTO
     * @param adminsUserUpdateStatusRequest
     * @return
     */
    UserUpdateStatusDTO convertToUpdateStatusDTO(AdminsUserUpdateStatusRequest adminsUserUpdateStatusRequest);

    /**
     * 分页获取用户列表  - BO转Response
     * @param userPage
     * @return
     */
    PageResult<AdminsUserPageResponse> convertToPageResponse(PageResult<UserBO> userPage);
}
