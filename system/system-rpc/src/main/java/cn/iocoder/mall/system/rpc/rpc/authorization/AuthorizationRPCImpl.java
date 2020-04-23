package cn.iocoder.mall.system.rpc.rpc.authorization;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.system.biz.dto.authorization.AuthorizationCheckPermissionsDTO;
import cn.iocoder.mall.system.biz.service.authorization.AuthorizationService;
import cn.iocoder.mall.system.rpc.api.authorization.AuthorizationRPC;
import cn.iocoder.mall.system.rpc.convert.authorization.AuthorizationConvert;
import cn.iocoder.mall.system.rpc.request.authorization.AuthorizationCheckPermissionsRequest;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "${dubbo.provider.AuthorizationRPC.version}", validation = "true")
public class AuthorizationRPCImpl implements AuthorizationRPC {

    @Autowired
    private AuthorizationService authorizationService;

    @Override
    public CommonResult<Boolean> checkPermissions(AuthorizationCheckPermissionsRequest checkPermissionsRequest) {
        AuthorizationCheckPermissionsDTO checkPermissionsDTO = AuthorizationConvert.INSTANCE.convert(checkPermissionsRequest);
        authorizationService.checkPermissions(checkPermissionsDTO);
        return CommonResult.success(true);
    }

}
