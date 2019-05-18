package cn.iocoder.mall.admin.service;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.mall.admin.api.OAuth2Service;
import cn.iocoder.mall.admin.api.bo.oauth2.OAuth2AccessTokenBO;
import cn.iocoder.mall.admin.api.bo.oauth2.OAuth2AuthenticationBO;
import cn.iocoder.mall.admin.api.constant.AdminErrorCodeEnum;
import cn.iocoder.mall.admin.api.dto.oauth2.OAuth2CreateTokenDTO;
import cn.iocoder.mall.admin.api.dto.oauth2.OAuth2GetTokenDTO;
import cn.iocoder.mall.admin.api.dto.oauth2.OAuth2RefreshTokenDTO;
import cn.iocoder.mall.admin.api.dto.oauth2.OAuth2RemoveTokenByUserDTO;
import cn.iocoder.mall.admin.convert.OAuth2Convert;
import cn.iocoder.mall.admin.dao.OAuth2AccessTokenMapper;
import cn.iocoder.mall.admin.dao.OAuth2RefreshTokenMapper;
import cn.iocoder.mall.admin.dataobject.OAuth2AccessTokenDO;
import cn.iocoder.mall.admin.dataobject.OAuth2RefreshTokenDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
    public OAuth2AccessTokenBO createToken(OAuth2CreateTokenDTO oauth2CreateTokenDTO) {
        Integer userId = oauth2CreateTokenDTO.getUserId();
        Integer userType = oauth2CreateTokenDTO.getUserType();
        // 创建刷新令牌
        OAuth2RefreshTokenDO oauth2RefreshTokenDO = createOAuth2RefreshToken(userId, userType);
        // 创建访问令牌
        OAuth2AccessTokenDO oauth2AccessTokenDO = createOAuth2AccessToken(userId, userType, oauth2RefreshTokenDO.getId());
        // 转换返回
        return OAuth2Convert.INSTANCE.convertToAccessTokenWithExpiresIn(oauth2AccessTokenDO);
    }

    @Override
    @Transactional
    public void removeToken(OAuth2RemoveTokenByUserDTO oauth2RemoveTokenByUserDTO) {
        Integer userId = oauth2RemoveTokenByUserDTO.getUserId();
        Integer userType = oauth2RemoveTokenByUserDTO.getUserType();
        // 设置 access token 失效
        oauth2AccessTokenMapper.updateToInvalid(userId, userType);
        // 设置 refresh token 失效
        oauth2RefreshTokenMapper.updateToInvalid(userId, userType);
    }

    @Override
    public OAuth2AccessTokenBO refreshToken(OAuth2RefreshTokenDTO oauth2RefreshTokenDTO) {
        OAuth2RefreshTokenDO refreshTokenDO = oauth2RefreshTokenMapper.selectById(oauth2RefreshTokenDTO.getRefreshToken());
        // 校验刷新令牌是否合法
        if (refreshTokenDO == null) { // 不存在
            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.OAUTH_INVALID_REFRESH_TOKEN_NOT_FOUND.getCode());
        }
        if (refreshTokenDO.getExpiresTime().getTime() < System.currentTimeMillis()) { // 已过期
            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.OAUTH_INVALID_REFRESH_TOKEN_EXPIRED.getCode());
        }
        if (!refreshTokenDO.getValid()) { // 无效
            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.OAUTH_INVALID_REFRESH_TOKEN_INVALID.getCode());
        }
        // 标记 refreshToken 对应的 accessToken 都不合法
        oauth2AccessTokenMapper.updateToInvalidByRefreshToken(oauth2RefreshTokenDTO.getRefreshToken());
        // 创建访问令牌
        OAuth2AccessTokenDO oauth2AccessTokenDO = createOAuth2AccessToken(refreshTokenDO.getUserId(), refreshTokenDO.getUserType(),
                refreshTokenDO.getId());
        // 转换返回
        return OAuth2Convert.INSTANCE.convertToAccessTokenWithExpiresIn(oauth2AccessTokenDO);
    }

    @Override
    public OAuth2AuthenticationBO getAuthentication(OAuth2GetTokenDTO oauth2GetTokenDTO) {
        OAuth2AccessTokenDO accessTokenDO = oauth2AccessTokenMapper.selectById(oauth2GetTokenDTO.getAccessToken());
        if (accessTokenDO == null) { // 不存在
            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.OAUTH2_INVALID_TOKEN_NOT_FOUND.getCode());
        }
        if (accessTokenDO.getExpiresTime().getTime() < System.currentTimeMillis()) { // 已过期
            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.OAUTH2_INVALID_TOKEN_EXPIRED.getCode());
        }
        if (!accessTokenDO.getValid()) { // 无效
            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.OAUTH2_INVALID_TOKEN_INVALID.getCode());
        }
        if (!oauth2GetTokenDTO.getUserType().equals(accessTokenDO.getUserType())) {
            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.OAUTH2_INVALID_TOKEN_INVALID.getCode());
        }
        // 转换返回
        return OAuth2Convert.INSTANCE.convertToAuthentication(accessTokenDO);
    }

    private OAuth2AccessTokenDO createOAuth2AccessToken(Integer userId, Integer userType, String refreshToken) {
        OAuth2AccessTokenDO accessToken = new OAuth2AccessTokenDO().setId(generateAccessToken())
                .setRefreshToken(refreshToken)
                .setUserId(userId).setUserType(userType)
                .setExpiresTime(new Date(System.currentTimeMillis() + accessTokenExpireTimeMillis))
                .setValid(true);
        oauth2AccessTokenMapper.insert(accessToken);
        return accessToken;
    }

    private OAuth2RefreshTokenDO createOAuth2RefreshToken(Integer userId, Integer userType) {
        OAuth2RefreshTokenDO refreshToken = new OAuth2RefreshTokenDO().setId(generateRefreshToken())
                .setUserId(userId).setUserType(userType)
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
