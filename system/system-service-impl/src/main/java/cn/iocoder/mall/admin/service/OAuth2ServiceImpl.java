package cn.iocoder.mall.admin.service;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.OAuth2Service;
import cn.iocoder.mall.admin.api.bo.oauth2.OAuth2AccessTokenBO;
import cn.iocoder.mall.admin.api.bo.oauth2.OAuth2AuthenticationBO;
import cn.iocoder.mall.admin.api.constant.AdminErrorCodeEnum;
import cn.iocoder.mall.admin.convert.OAuth2Convert;
import cn.iocoder.mall.admin.dao.OAuth2AccessTokenMapper;
import cn.iocoder.mall.admin.dao.OAuth2RefreshTokenMapper;
import cn.iocoder.mall.admin.dataobject.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.OAuth2Service.version:1.0.0}")
public class OAuth2ServiceImpl implements OAuth2Service {

    /**
     * 访问令牌过期时间，单位：毫秒
     */
    @Value("${modules.oauth2-code-service.access-token-expire-time-millis}")
    private int accessTokenExpireTimeMillis;
    /**
     * 刷新令牌过期时间，单位：毫秒
     */
    @Value("${modules.oauth2-code-service.refresh-token-expire-time-millis}")
    private int refreshTokenExpireTimeMillis;

    @Autowired
    private AdminServiceImpl adminService;
    @Autowired
    private OAuth2AccessTokenMapper oauth2AccessTokenMapper;
    @Autowired
    private OAuth2RefreshTokenMapper oauth2RefreshTokenMapper;
    @Autowired
    private RoleServiceImpl roleService;
    @Autowired
    private ResourceServiceImpl resourceService;

    @Override
    @Transactional
    public CommonResult<OAuth2AccessTokenBO> getAccessToken(String username, String password) {
        CommonResult<AdminDO> adminResult = adminService.validAdmin(username, password);
        // 校验失败，返回错误结果
        if (adminResult.isError()) {
            return CommonResult.error(adminResult);
        }
        AdminDO admin = adminResult.getData();
        // 创建刷新令牌
        OAuth2RefreshTokenDO oauth2RefreshTokenDO = createOAuth2RefreshToken(admin.getId());
        // 创建访问令牌
        OAuth2AccessTokenDO oauth2AccessTokenDO = createOAuth2AccessToken(admin.getId(), oauth2RefreshTokenDO.getId());
        // 转换返回
        return CommonResult.success(OAuth2Convert.INSTANCE.convertToAccessTokenWithExpiresIn(oauth2AccessTokenDO));
    }

    @Override
    public CommonResult<OAuth2AuthenticationBO> checkToken(String accessToken) {
        OAuth2AccessTokenDO accessTokenDO = oauth2AccessTokenMapper.selectByTokenId(accessToken);
        if (accessTokenDO == null) { // 不存在
            return ServiceExceptionUtil.error(AdminErrorCodeEnum.OAUTH_INVALID_TOKEN_NOT_FOUND.getCode());
        }
        if (accessTokenDO.getExpiresTime().getTime() < System.currentTimeMillis()) { // 已过期
            return ServiceExceptionUtil.error(AdminErrorCodeEnum.OAUTH_INVALID_TOKEN_EXPIRED.getCode());
        }
        if (!accessTokenDO.getValid()) { // 无效
            return ServiceExceptionUtil.error(AdminErrorCodeEnum.OAUTH_INVALID_TOKEN_INVALID.getCode());
        }
        // 获得管理员拥有的角色
        List<AdminRoleDO> adminRoleDOs = adminService.getAdminRoles(accessTokenDO.getAdminId());
        return CommonResult.success(OAuth2Convert.INSTANCE.convertToAuthentication(accessTokenDO, adminRoleDOs));
    }

    /**
     * 移除管理员对应的 Token
     *
     * @param adminId 管理员编号
     */
    @Transactional
    public void removeToken(Integer adminId) {
        // 设置 access token 失效
        oauth2AccessTokenMapper.updateToInvalidByAdminId(adminId);
        // 设置 refresh token 失效
        oauth2RefreshTokenMapper.updateToInvalidByAdminId(adminId);
    }

    @Override
    public CommonResult<Boolean> checkPermission(Integer adminId, Set<Integer> roleIds, String url) {
        // 如果未配置该资源，说明无需权限控制。
        ResourceDO resource = resourceService.getResourceByTypeAndHandler(ResourceDO.TYPE_OPERATION, url);
        if (resource == null) {
            return CommonResult.success(true);
        }
        // 资源存在，结果无角色，说明没有权限。
        if (roleIds == null || roleIds.isEmpty()) {
            return ServiceExceptionUtil.error(AdminErrorCodeEnum.OAUTH_INVALID_PERMISSION.getCode());
        }
        // 校验是否有资源对应的角色，即 RBAC 。
        List<RoleResourceDO> roleResourceDOs = roleService.getRoleByResourceId(resource.getId());
        for (RoleResourceDO roleResourceDO : roleResourceDOs) {
            if (roleIds.contains(roleResourceDO.getRoleId())) {
                return CommonResult.success(true);
            }
        }
        // 没有权限，返回错误
        return ServiceExceptionUtil.error(AdminErrorCodeEnum.OAUTH_INVALID_PERMISSION.getCode());
    }

    private OAuth2AccessTokenDO createOAuth2AccessToken(Integer adminId, String refreshToken) {
        OAuth2AccessTokenDO accessToken = new OAuth2AccessTokenDO().setId(generateAccessToken())
                .setRefreshToken(refreshToken)
                .setAdminId(adminId)
                .setExpiresTime(new Date(System.currentTimeMillis() + accessTokenExpireTimeMillis))
                .setValid(true);
        oauth2AccessTokenMapper.insert(accessToken);
        return accessToken;
    }

    private OAuth2RefreshTokenDO createOAuth2RefreshToken(Integer adminId) {
        OAuth2RefreshTokenDO refreshToken = new OAuth2RefreshTokenDO().setId(generateRefreshToken())
                .setAdminId(adminId)
                .setExpiresTime(new Date(System.currentTimeMillis() + refreshTokenExpireTimeMillis))
                .setValid(true);
        oauth2RefreshTokenMapper.insert(refreshToken);
        return refreshToken;
    }

    private String generateAccessToken() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    private String generateRefreshToken() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
