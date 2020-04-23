package cn.iocoder.mall.system.rpc.api.authorization;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.system.rpc.request.authorization.AuthorizationCheckPermissionsRequest;

public interface AuthorizationRPC {

    CommonResult<Boolean> checkPermissions(AuthorizationCheckPermissionsRequest checkPermissionsRequest);

}
