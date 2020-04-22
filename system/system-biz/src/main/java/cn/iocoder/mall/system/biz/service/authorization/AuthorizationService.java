package cn.iocoder.mall.system.biz.service.authorization;

import cn.iocoder.mall.system.biz.dto.authorization.AuthorizationCheckPermissionsDTO;

public interface AuthorizationService {

    void checkPermissions(AuthorizationCheckPermissionsDTO checkPermissionsDTO);

}
