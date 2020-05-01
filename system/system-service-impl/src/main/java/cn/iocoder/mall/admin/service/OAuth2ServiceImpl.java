package cn.iocoder.mall.admin.service;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.mall.system.api.OAuth2Service;
import cn.iocoder.mall.system.api.bo.oauth2.OAuth2AccessTokenBO;
import cn.iocoder.mall.system.api.bo.oauth2.OAuth2AuthenticationBO;
import cn.iocoder.mall.system.api.constant.AdminErrorCodeEnum;
import cn.iocoder.mall.system.api.dto.oauth2.OAuth2CreateTokenDTO;
import cn.iocoder.mall.system.api.dto.oauth2.OAuth2GetTokenDTO;
import cn.iocoder.mall.system.api.dto.oauth2.OAuth2RefreshTokenDTO;
import cn.iocoder.mall.system.api.dto.oauth2.OAuth2RemoveTokenByUserDTO;
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

}
