package cn.iocoder.mall.managementweb.manager.passport;

import cn.iocoder.common.framework.enums.UserTypeEnum;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.managementweb.controller.passport.dto.AdminPassportLoginDTO;
import cn.iocoder.mall.managementweb.controller.passport.vo.AdminPassportVO;
import cn.iocoder.mall.managementweb.convert.passport.AdminPassportConvert;
import cn.iocoder.mall.systemservice.rpc.admin.AdminRpc;
import cn.iocoder.mall.systemservice.rpc.admin.vo.AdminVO;
import cn.iocoder.mall.systemservice.rpc.oauth.OAuth2Rpc;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2CreateAccessTokenDTO;
import cn.iocoder.mall.systemservice.rpc.oauth.vo.OAuth2AccessTokenVO;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service
public class AdminPassportManager {

    @Reference(version = "${dubbo.consumer.AdminRpc.version}", validation = "false")
    private AdminRpc adminRpc;
    @Reference(version = "${dubbo.consumer.OAuth2Rpc.version}", validation = "false")
    private OAuth2Rpc oauth2Rpc;

    public AdminPassportVO login(AdminPassportLoginDTO loginDTO, String ip) {
        // 校验管理员密码
        CommonResult<AdminVO> verifyPasswordResult = adminRpc.verifyPassword(AdminPassportConvert.INSTANCE.convert(loginDTO).setIp(ip));
        verifyPasswordResult.checkError();
        // 创建访问令牌
        CommonResult<OAuth2AccessTokenVO> createAccessTokenResult = oauth2Rpc.createAccessToken(
                new OAuth2CreateAccessTokenDTO().setUserId(verifyPasswordResult.getData().getId())
                        .setUserType(UserTypeEnum.ADMIN.getValue()).setCreateIp(ip));
        createAccessTokenResult.checkError();
        // 返回
        return AdminPassportConvert.INSTANCE.convert(verifyPasswordResult.getData(), createAccessTokenResult.getData());
    }

}
