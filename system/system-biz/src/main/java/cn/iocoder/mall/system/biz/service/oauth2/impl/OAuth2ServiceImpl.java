package cn.iocoder.mall.system.biz.service.oauth2.impl;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.mall.system.biz.bo.account.AccountBO;
import cn.iocoder.mall.system.biz.bo.ouath2.OAuth2AccessTokenBO;
import cn.iocoder.mall.system.biz.convert.OAuth2Convert;
import cn.iocoder.mall.system.biz.dao.oauth2.OAuth2AccessTokenMapper;
import cn.iocoder.mall.system.biz.dao.oauth2.OAuth2RefreshTokenMapper;
import cn.iocoder.mall.system.biz.dataobject.oauth2.OAuth2AccessTokenDO;
import cn.iocoder.mall.system.biz.dataobject.oauth2.OAuth2RefreshTokenDO;
import cn.iocoder.mall.system.biz.dto.oatuh2.OAuth2UsernameAuthenticateDTO;
import cn.iocoder.mall.system.biz.service.account.AccountService;
import cn.iocoder.mall.system.biz.service.oauth2.OAuth2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

import static cn.iocoder.mall.system.biz.constant.SystemErrorCodeEnum.OAUTH2_ACCOUNT_NOT_FOUND;
import static cn.iocoder.mall.system.biz.constant.SystemErrorCodeEnum.OAUTH2_ACCOUNT_PASSWORD_ERROR;

@Service
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
    private AccountService accountService;

    @Autowired
    private OAuth2AccessTokenMapper oauth2AccessTokenMapper;
    @Autowired
    private OAuth2RefreshTokenMapper oauth2RefreshTokenMapper;

    @Override
    @Transactional
    public OAuth2AccessTokenBO  authenticate(OAuth2UsernameAuthenticateDTO usernameAuthenticateDTO) {
        // 获得账号
        AccountBO accountBO = accountService.getByUsername(usernameAuthenticateDTO.getUsername());
        if (accountBO == null) {
            throw ServiceExceptionUtil.exception(OAUTH2_ACCOUNT_NOT_FOUND);
        }
        // 校验密码
        if (!accountService.matchPassword(usernameAuthenticateDTO.getPassword(), accountBO.getPassword())) {
            throw ServiceExceptionUtil.exception(OAUTH2_ACCOUNT_PASSWORD_ERROR);
        }
        // 创建刷新令牌 + 访问令牌
        OAuth2RefreshTokenDO oauth2RefreshTokenDO = createOAuth2RefreshToken(accountBO.getId());
        OAuth2AccessTokenDO oauth2AccessTokenDO = createOAuth2AccessToken(accountBO.getId(), oauth2RefreshTokenDO.getId());
        // 返回访问令牌
        return OAuth2Convert.INSTANCE.convert(oauth2AccessTokenDO);
    }

    private OAuth2AccessTokenDO createOAuth2AccessToken(Integer accountId, String refreshToken) {
        OAuth2AccessTokenDO accessToken = new OAuth2AccessTokenDO()
                .setId(generateAccessToken())
                .setAccountId(accountId)
                .setRefreshToken(refreshToken)
                .setExpiresTime(new Date(System.currentTimeMillis() + accessTokenExpireTimeMillis))
                .setValid(true);
        oauth2AccessTokenMapper.insert(accessToken);
        return accessToken;
    }

    private OAuth2RefreshTokenDO createOAuth2RefreshToken(Integer accountId) {
        OAuth2RefreshTokenDO refreshToken = new OAuth2RefreshTokenDO()
                .setId(generateRefreshToken())
                .setAccountId(accountId)
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
