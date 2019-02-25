package cn.iocoder.mall.user.service;

import cn.iocoder.common.framework.exception.ServiceException;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.mall.user.convert.OAuth2Convert;
import cn.iocoder.mall.user.dao.OAuth2AccessTokenMapper;
import cn.iocoder.mall.user.dao.OAuth2RefreshTokenMapper;
import cn.iocoder.mall.user.dataobject.MobileCodeDO;
import cn.iocoder.mall.user.dataobject.OAuth2AccessTokenDO;
import cn.iocoder.mall.user.dataobject.OAuth2RefreshTokenDO;
import cn.iocoder.mall.user.dataobject.UserDO;
import cn.iocoder.mall.user.service.api.OAuth2Service;
import cn.iocoder.mall.user.service.api.constant.UserErrorCodeEnum;
import cn.iocoder.mall.user.service.api.dto.OAuth2AccessTokenBO;
import cn.iocoder.mall.user.service.api.dto.OAuth2AuthenticationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * OAuth2Service ，实现用户授权相关的逻辑
 */
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
    private UserServiceImpl userService;
    @Autowired
    private MobileCodeServiceImpl mobileCodeService;
    @Autowired
    private OAuth2AccessTokenMapper oauth2AccessTokenMapper;
    @Autowired
    private OAuth2RefreshTokenMapper oauth2RefreshTokenMapper;

    @Override
    @Transactional
    public OAuth2AccessTokenBO getAccessToken(String mobile, String code) {
        // 校验手机号的最后一个手机验证码是否有效
        MobileCodeDO mobileCodeDO = mobileCodeService.validLastMobileCode(mobile, code);
        // 获取用户
        UserDO userDO = userService.getUser(mobile);
        if (userDO == null) { // 用户不存在
            throw ServiceExceptionUtil.exception(UserErrorCodeEnum.USER_MOBILE_NOT_REGISTERED.getCode());
        }
        // 创建刷新令牌
        OAuth2RefreshTokenDO oauth2RefreshTokenDO = createOAuth2RefreshToken(userDO.getId());
        // 创建访问令牌
        OAuth2AccessTokenDO oauth2AccessTokenDO = createOAuth2AccessToken(userDO.getId(), oauth2RefreshTokenDO.getTokenId());
        // 标记已使用
        mobileCodeService.useMobileCode(mobileCodeDO.getId(), userDO.getId());
        // 转换返回
        return OAuth2Convert.INSTANCE.convertWithExpiresIn(oauth2AccessTokenDO);
    }

    @Override
    public OAuth2AuthenticationDTO checkToken(String accessToken) throws ServiceException {
        OAuth2AccessTokenDO accessTokenDO = oauth2AccessTokenMapper.selectByTokenId(accessToken);
        if (accessTokenDO == null) { // 不存在
            throw ServiceExceptionUtil.exception(UserErrorCodeEnum.OAUTH_INVALID_TOKEN_NOT_FOUND.getCode());
        }
        if (accessTokenDO.getExpiresTime().getTime() < System.currentTimeMillis()) { // 已过期
            throw ServiceExceptionUtil.exception(UserErrorCodeEnum.OAUTH_INVALID_TOKEN_EXPIRED.getCode());
        }
        if (!accessTokenDO.getValid()) { // 无效
            throw ServiceExceptionUtil.exception(UserErrorCodeEnum.OAUTH_INVALID_TOKEN_INVALID.getCode());
        }
        // 转换返回
        return new OAuth2AuthenticationDTO().setUid(accessTokenDO.getUid());
    }

    private OAuth2AccessTokenDO createOAuth2AccessToken(Long uid, String refreshToken) {
        OAuth2AccessTokenDO accessToken = new OAuth2AccessTokenDO().setTokenId(generateAccessToken())
                .setRefreshToken(refreshToken)
                .setUid(uid)
                .setExpiresTime(new Date(System.currentTimeMillis() + accessTokenExpireTimeMillis))
                .setValid(true);
        oauth2AccessTokenMapper.insert(accessToken);
        return accessToken;
    }

    private OAuth2RefreshTokenDO createOAuth2RefreshToken(Long uid) {
        OAuth2RefreshTokenDO refreshToken = new OAuth2RefreshTokenDO().setTokenId(generateRefreshToken())
                .setUid(uid)
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
