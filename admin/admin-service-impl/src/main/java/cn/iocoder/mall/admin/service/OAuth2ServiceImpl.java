package cn.iocoder.mall.admin.service;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.OAuth2Service;
import cn.iocoder.mall.admin.api.bo.OAuth2AccessTokenBO;
import cn.iocoder.mall.admin.api.bo.OAuth2AuthenticationBO;
import cn.iocoder.mall.admin.api.constant.AdminErrorCodeEnum;
import cn.iocoder.mall.admin.convert.OAuth2Convert;
import cn.iocoder.mall.admin.dao.OAuth2AccessTokenMapper;
import cn.iocoder.mall.admin.dao.OAuth2RefreshTokenMapper;
import cn.iocoder.mall.admin.dataobject.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@com.alibaba.dubbo.config.annotation.Service
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

    @Override
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

    @Override
    public CommonResult<Boolean> checkPermission(Integer adminId, Set<Integer> roleIds, String url) {
        // 避免传入的是空集合
        if (roleIds == null) {
            roleIds = Collections.emptySet();
        }
        // 校验权限
        List<RoleResourceDO> roleResourceDOs = roleService.getRoleByResourceHandler(url);
        if (roleResourceDOs.isEmpty()) { // 任何角色，都可以访问
            return CommonResult.success(true);
        }
        for (RoleResourceDO roleResourceDO : roleResourceDOs) {
            if (roleIds.contains(roleResourceDO.getId())) {
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