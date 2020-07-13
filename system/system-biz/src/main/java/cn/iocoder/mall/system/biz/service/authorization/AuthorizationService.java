package cn.iocoder.mall.system.biz.service.authorization;

import cn.iocoder.mall.system.biz.dto.authorization.AuthorizationCheckPermissionsDTO;

/**
 * 授权模块 - Service 接口
 */
public interface AuthorizationService {

    /**
     * 校验指定账号是否有指定权限。如果没有，则抛出 {@link ServiceException} 异常
     *
     * @param checkPermissionsDTO 校验权限 DTO
     */
    void checkPermissions(AuthorizationCheckPermissionsDTO checkPermissionsDTO);

}
