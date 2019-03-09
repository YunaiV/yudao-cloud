package cn.iocoder.mall.user.service;

import cn.iocoder.common.framework.exception.ServiceException;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.user.convert.OAuth2Convert;
import cn.iocoder.mall.user.dao.OAuth2AccessTokenMapper;
import cn.iocoder.mall.user.dao.OAuth2RefreshTokenMapper;
import cn.iocoder.mall.user.dataobject.MobileCodeDO;
import cn.iocoder.mall.user.dataobject.OAuth2AccessTokenDO;
import cn.iocoder.mall.user.dataobject.OAuth2RefreshTokenDO;
import cn.iocoder.mall.user.dataobject.UserDO;
import cn.iocoder.mall.user.service.api.OAuth2Service;
import cn.iocoder.mall.user.service.api.constant.UserErrorCodeEnum;
import cn.iocoder.mall.user.service.api.bo.OAuth2AccessTokenBO;
import cn.iocoder.mall.user.service.api.bo.OAuth2AuthenticationBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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
    public CommonResult<OAuth2AccessTokenBO> getAccessToken(String mobile, String code) {
        // 校验传入的 mobile 和 code 是否合法
        CommonResult<MobileCodeDO> result = mobileCodeService.validLastMobileCode(mobile, code);
        if (result.isError()) {
            return CommonResult.error(result);
        }
        // 获取用户
        UserDO userDO = userService.getUser(mobile);
        if (userDO == null) { // 用户不存在，则进行创建用户
            userDO = userService.createUser(mobile);
            Assert.notNull(userDO, "创建用户必然成功");
        }
        // 创建刷新令牌
        OAuth2RefreshTokenDO oauth2RefreshTokenDO = createOAuth2RefreshToken(userDO.getId());
        // 创建访问令牌
        OAuth2AccessTokenDO oauth2AccessTokenDO = createOAuth2AccessToken(userDO.getId(), oauth2RefreshTokenDO.getId());
        // 标记已使用
        mobileCodeService.useMobileCode(result.getData().getId(), userDO.getId());
        // 转换返回
        return CommonResult.success(OAuth2Convert.INSTANCE.convertToAccessTokenWithExpiresIn(oauth2AccessTokenDO));
    }

    @Override
    public CommonResult<OAuth2AuthenticationBO> checkToken(String accessToken) throws ServiceException {
        OAuth2AccessTokenDO accessTokenDO = oauth2AccessTokenMapper.selectByTokenId(accessToken);
        if (accessTokenDO == null) { // 不存在
            return ServiceExceptionUtil.error(UserErrorCodeEnum.OAUTH_INVALID_TOKEN_NOT_FOUND.getCode());
        }
        if (accessTokenDO.getExpiresTime().getTime() < System.currentTimeMillis()) { // 已过期
            return ServiceExceptionUtil.error(UserErrorCodeEnum.OAUTH_INVALID_TOKEN_EXPIRED.getCode());
        }
        if (!accessTokenDO.getValid()) { // 无效
            return ServiceExceptionUtil.error(UserErrorCodeEnum.OAUTH_INVALID_TOKEN_INVALID.getCode());
        }
        // 转换返回
        return CommonResult.success(OAuth2Convert.INSTANCE.convertToAuthentication(accessTokenDO));
    }

    private OAuth2AccessTokenDO createOAuth2AccessToken(Long uid, String refreshToken) {
        OAuth2AccessTokenDO accessToken = new OAuth2AccessTokenDO().setId(generateAccessToken())
                .setRefreshToken(refreshToken)
                .setUid(uid)
                .setExpiresTime(new Date(System.currentTimeMillis() + accessTokenExpireTimeMillis))
                .setValid(true);
        oauth2AccessTokenMapper.insert(accessToken);
        return accessToken;
    }

    private OAuth2RefreshTokenDO createOAuth2RefreshToken(Long uid) {
        OAuth2RefreshTokenDO refreshToken = new OAuth2RefreshTokenDO().setId(generateRefreshToken())
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