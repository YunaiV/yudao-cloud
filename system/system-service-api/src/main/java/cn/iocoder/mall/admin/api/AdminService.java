package cn.iocoder.mall.admin.api;

import cn.iocoder.common.framework.constant.CommonStatusEnum;
import cn.iocoder.common.framework.validator.InEnum;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.bo.admin.AdminBO;
import cn.iocoder.mall.admin.api.bo.admin.AdminPageBO;
import cn.iocoder.mall.admin.api.dto.admin.AdminAddDTO;
import cn.iocoder.mall.admin.api.dto.admin.AdminPageDTO;
import cn.iocoder.mall.admin.api.dto.admin.AdminUpdateDTO;

import java.util.Set;

/**
 * 管理员 Service 接口
 */
public interface AdminService {

    CommonResult<AdminPageBO> getAdminPage(AdminPageDTO adminPageDTO);

    AdminBO addAdmin(Integer adminId, AdminAddDTO adminAddDTO);

    CommonResult<Boolean> updateAdmin(Integer adminId, AdminUpdateDTO adminUpdateDTO);

    CommonResult<Boolean> updateAdminStatus(Integer adminId, Integer updateAdminId,
                                            @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}") Integer status);

    CommonResult<Boolean> deleteAdmin(Integer adminId, Integer updateAdminId);

    CommonResult<Boolean> assignRole(Integer adminId, Integer updateAdminId, Set<Integer> roleIds);

}
