package cn.iocoder.mall.system.biz.service.oauth2;

import cn.iocoder.common.framework.constant.SysErrorCodeEnum;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.util.ValidationUtil;
import cn.iocoder.mall.system.biz.bo.account.AccountBO;
import cn.iocoder.mall.system.biz.bo.ouath2.OAuth2AccessTokenBO;
import cn.iocoder.mall.system.biz.convert.oauth2.OAuth2Convert;
import cn.iocoder.mall.system.biz.dao.oauth2.OAuth2AccessTokenMapper;
import cn.iocoder.mall.system.biz.dao.oauth2.OAuth2RefreshTokenMapper;
import cn.iocoder.mall.system.biz.dataobject.oauth2.OAuth2AccessTokenDO;
import cn.iocoder.mall.system.biz.dataobject.oauth2.OAuth2RefreshTokenDO;
import cn.iocoder.mall.system.biz.dto.account.AccountCreateDTO;
import cn.iocoder.mall.system.biz.dto.oatuh2.OAuth2AccessTokenAuthenticateDTO;
import cn.iocoder.mall.system.biz.dto.oatuh2.OAuth2MobileCodeAuthenticateDTO;
import cn.iocoder.mall.system.biz.dto.oatuh2.OAuth2UsernameAuthenticateDTO;
import cn.iocoder.mall.system.biz.enums.SystemErrorCodeEnum;
import cn.iocoder.mall.system.biz.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

import static cn.iocoder.mall.system.biz.enums.SystemErrorCodeEnum.OAUTH2_ACCOUNT_NOT_FOUND;
import static cn.iocoder.mall.system.biz.enums.SystemErrorCodeEnum.OAUTH2_ACCOUNT_PASSWORD_ERROR;

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
    private OAuth2MobileCodeService oauth2MobileCodeService;

    @Autowired
    private OAuth2AccessTokenMapper oauth2AccessTokenMapper;
    @Autowired
    private OAuth2RefreshTokenMapper oauth2RefreshTokenMapper;

    @Override
    @Transactional
    public OAuth2AccessTokenBO  authenticate(OAuth2UsernameAuthenticateDTO authenticateDTO) {
        // 获得账号
        AccountBO accountBO = accountService.getByUsername(authenticateDTO.getUsername());
        if (accountBO == null) {
            throw ServiceExceptionUtil.exception(OAUTH2_ACCOUNT_NOT_FOUND);
        }
        // 校验密码
        if (!accountService.matchPassword(authenticateDTO.getPassword(), accountBO.getPassword())) {
            throw ServiceExceptionUtil.exception(OAUTH2_ACCOUNT_PASSWORD_ERROR);
        }
        // TODO 记录账号最后登陆时间和 ip 等
        // 创建刷新令牌 + 访问令牌
        OAuth2RefreshTokenDO oauth2RefreshTokenDO = createOAuth2RefreshToken(accountBO.getId());
        OAuth2AccessTokenDO oauth2AccessTokenDO = createOAuth2AccessToken(accountBO.getId(), oauth2RefreshTokenDO.getId());
        // 返回访问令牌
        return OAuth2Convert.INSTANCE.convert(oauth2AccessTokenDO);
    }

    @Override
    @Transactional
    public OAuth2AccessTokenBO authenticate(OAuth2MobileCodeAuthenticateDTO authenticateDTO) {
        // 校验手机格式
        if (!ValidationUtil.isMobile(authenticateDTO.getMobile())) {
            throw ServiceExceptionUtil.exception(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(), "手机格式不正确"); // TODO 有点搓
        }
        // 使用手机验证码。如果验证不通过，则会抛出异常
        oauth2MobileCodeService.use(authenticateDTO.getMobile(), authenticateDTO.getCode());
        // 获得账号
        AccountBO accountBO = accountService.getByMobile(authenticateDTO.getMobile());
        if (accountBO == null) { // 账号不存时，自动创建
            // 创建账号
            accountBO = accountService.create(new AccountCreateDTO()
                    .setMobile(authenticateDTO.getMobile())
                    .setCreateIp(authenticateDTO.getIp())
            );
        }
        // TODO 记录账号最后登陆时间和 ip 等
        // 创建刷新令牌 + 访问令牌
        OAuth2RefreshTokenDO oauth2RefreshTokenDO = createOAuth2RefreshToken(accountBO.getId());
        OAuth2AccessTokenDO oauth2AccessTokenDO = createOAuth2AccessToken(accountBO.getId(), oauth2RefreshTokenDO.getId());
        // 返回访问令牌
        return OAuth2Convert.INSTANCE.convert(oauth2AccessTokenDO);
    }

    @Override
    public OAuth2AccessTokenBO authenticate(OAuth2AccessTokenAuthenticateDTO authenticateDTO) {
        OAuth2AccessTokenDO oauth2AccessTokenDO = oauth2AccessTokenMapper.selectById(authenticateDTO.getAccessToken());
        if (oauth2AccessTokenDO == null) { // 不存在
            throw ServiceExceptionUtil.exception(SystemErrorCodeEnum.OAUTH2_INVALID_TOKEN_NOT_FOUND.getCode());
        }
        if (oauth2AccessTokenDO.getExpiresTime().getTime() < System.currentTimeMillis()) { // 已过期
            throw ServiceExceptionUtil.exception(SystemErrorCodeEnum.OAUTH2_INVALID_TOKEN_EXPIRED.getCode());
        }
        if (!oauth2AccessTokenDO.getValid()) { // 无效
            throw ServiceExceptionUtil.exception(SystemErrorCodeEnum.OAUTH2_INVALID_TOKEN_INVALID.getCode());
        }
        // 转换返回
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
