package cn.iocoder.mall.systemservice.rpc.admin;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.systemservice.rpc.admin.dto.AdminCreateDTO;
import cn.iocoder.mall.systemservice.rpc.admin.dto.AdminPageDTO;
import cn.iocoder.mall.systemservice.rpc.admin.dto.AdminUpdateDTO;
import cn.iocoder.mall.systemservice.rpc.admin.dto.AdminVerifyPasswordDTO;
import cn.iocoder.mall.systemservice.rpc.admin.vo.AdminVO;

/**
 * 管理员 RPC 接口
 */
public interface AdminRpc {

    CommonResult<AdminVO> verifyPassword(AdminVerifyPasswordDTO verifyPasswordDTO);

    CommonResult<Integer> createAdmin(AdminCreateDTO createDTO);

    CommonResult<Boolean> updateAdmin(AdminUpdateDTO updateDTO);

    CommonResult<PageResult<AdminVO>> pageAdmin(AdminPageDTO pageDTO);

    CommonResult<AdminVO> getAdmin(Integer adminId);

}
