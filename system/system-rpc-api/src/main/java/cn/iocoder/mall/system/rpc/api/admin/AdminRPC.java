package cn.iocoder.mall.system.rpc.api.admin;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.system.rpc.response.admin.AdminResponse;

/**
 * Admin RPC 接口
 */
public interface AdminRPC {

    CommonResult<AdminResponse> getAdmin(Integer id);

    CommonResult<AdminResponse> getAdminByAccountId(Integer accountId);

}
