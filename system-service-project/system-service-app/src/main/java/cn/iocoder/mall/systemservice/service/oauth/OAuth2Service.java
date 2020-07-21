package cn.iocoder.mall.systemservice.service.oauth;

import cn.iocoder.common.framework.exception.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.util.StringUtils;
import cn.iocoder.mall.systemservice.convert.oauth.OAuth2Convert;
import cn.iocoder.mall.systemservice.dal.mysql.dataobject.oauth.OAuth2AccessTokenDO;
import cn.iocoder.mall.systemservice.dal.mysql.dataobject.oauth.OAuth2RefreshTokenDO;
import cn.iocoder.mall.systemservice.dal.mysql.mapper.oauth.OAuth2AccessTokenMapper;
import cn.iocoder.mall.systemservice.dal.mysql.mapper.oauth.OAuth2RefreshTokenMapper;
import cn.iocoder.mall.systemservice.service.oauth.bo.OAuth2AccessTokenBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static cn.iocoder.mall.systemservice.enums.SystemErrorCodeConstants.*;

/**
 * OAuth2.0 Service
 */
@Service
public class OAuth2Service {

    /**
     * 访问令牌过期时间，单位：毫秒
     */
    @Value("${modules.oauth2-service.access-token-expire-time-millis}")
    private int accessTokenExpireTimeMillis;
    /**
     * 刷新令牌过期时间，单位：毫秒
     */
    @Value("${modules.oauth2-service.refresh-token-expire-time-millis}")
    private int refreshTokenExpireTimeMillis;

    @Autowired
    private OAuth2AccessTokenMapper oauth2AccessTokenMapper;
    @Autowired
    private OAuth2RefreshTokenMapper oauth2RefreshTokenMapper;

    @Transactional
    public OAuth2AccessTokenBO createAccessToken(Integer userId, Integer userType, String createIp) {
        // 创建刷新令牌 + 访问令牌
        OAuth2RefreshTokenDO refreshTokenDO = createOAuth2RefreshToken(userId, userType, createIp);
        OAuth2AccessTokenDO accessTokenDO = createOAuth2AccessToken(refreshTokenDO, createIp);
        // 返回访问令牌
        return OAuth2Convert.INSTANCE.convert(accessTokenDO);
    }

    @Transactional
    public OAuth2AccessTokenBO checkAccessToken(String accessToken) {
        OAuth2AccessTokenDO accessTokenDO = oauth2AccessTokenMapper.selectById(accessToken);
        if (accessTokenDO == null) { // 不存在
            throw ServiceExceptionUtil.exception(OAUTH2_ACCESS_TOKEN_NOT_FOUND);
        }
        if (accessTokenDO.getExpiresTime().getTime() < System.currentTimeMillis()) { // 已过期
            throw ServiceExceptionUtil.exception(OAUTH2_ACCESS_TOKEN_TOKEN_EXPIRED);
        }
        // 返回访问令牌
        return OAuth2Convert.INSTANCE.convert(accessTokenDO);
    }

    @Transactional
    public OAuth2AccessTokenBO refreshAccessToken(String refreshToken, String createIp) {
        OAuth2RefreshTokenDO refreshTokenDO = oauth2RefreshTokenMapper.selectById(refreshToken);
        // 校验刷新令牌是否合法
        if (refreshTokenDO == null) { // 不存在
            throw ServiceExceptionUtil.exception(OAUTH2_REFRESH_TOKEN_NOT_FOUND);
        }
        if (refreshTokenDO.getExpiresTime().getTime() < System.currentTimeMillis()) { // 已过期
            throw ServiceExceptionUtil.exception(OAUTH_REFRESH_TOKEN_EXPIRED);
        }
        // 标记 refreshToken 对应的 accessToken 都不合法
        // 这块的实现，参考了 Spring Security OAuth2 的代码
        oauth2AccessTokenMapper.deleteByRefreshToken(refreshToken);
        // 创建访问令牌
        OAuth2AccessTokenDO oauth2AccessTokenDO = createOAuth2AccessToken(refreshTokenDO, createIp);
        // 返回访问令牌
        return OAuth2Convert.INSTANCE.convert(oauth2AccessTokenDO);
    }

    @Transactional
    public void removeToken(Integer userId, Integer userType) {
        oauth2AccessTokenMapper.deleteByUserIdAndUserType(userId, userType);
        oauth2RefreshTokenMapper.deleteByUserIdAndUserType(userId, userType);
    }

    private OAuth2AccessTokenDO createOAuth2AccessToken(OAuth2RefreshTokenDO refreshTokenDO, String createIp) {
        OAuth2AccessTokenDO accessToken = new OAuth2AccessTokenDO()
                .setId(generateAccessToken())
                .setUserId(refreshTokenDO.getUserId()).setUserType(refreshTokenDO.getUserType())
                .setRefreshToken(refreshTokenDO.getId())
                .setExpiresTime(new Date(System.currentTimeMillis() + accessTokenExpireTimeMillis))
                .setCreateIp(createIp);
        oauth2AccessTokenMapper.insert(accessToken);
        return accessToken;
    }

    private OAuth2RefreshTokenDO createOAuth2RefreshToken(Integer userId, Integer userType, String createIp) {
        OAuth2RefreshTokenDO refreshToken = new OAuth2RefreshTokenDO()
                .setId(generateRefreshToken())
                .setUserId(userId).setUserType(userType)
                .setExpiresTime(new Date(System.currentTimeMillis() + refreshTokenExpireTimeMillis))
                .setCreateIp(createIp);
        oauth2RefreshTokenMapper.insert(refreshToken);
        return refreshToken;
    }

    private String generateAccessToken() {
        return StringUtils.uuid(true);
    }

    private String generateRefreshToken() {
        return StringUtils.uuid(true);
    }

}
