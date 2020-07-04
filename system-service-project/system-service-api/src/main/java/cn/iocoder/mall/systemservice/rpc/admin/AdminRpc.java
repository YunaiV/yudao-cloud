package cn.iocoder.mall.systemservice.rpc.admin;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.systemservice.rpc.admin.dto.AdminVerifyPasswordDTO;
import cn.iocoder.mall.systemservice.rpc.admin.vo.AdminVO;

/**
 * 管理员 RPC 接口
 */
public interface AdminRpc {

    CommonResult<AdminVO> verifyPassword(AdminVerifyPasswordDTO verifyPasswordDTO);

}
