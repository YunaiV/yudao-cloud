package cn.iocoder.yudao.module.system.service.social.authrequest;

import cn.iocoder.yudao.module.system.dal.dataobject.social.SocialClientDO;
import me.zhyd.oauth.request.AuthRequest;
import org.springframework.stereotype.Component;

/**
 * 三方登录请求构建器接口
 */
@Component
public interface AuthRequestBuilder {


    /**
     * 判断是否支持该类型
     *
     * @param socialType 社交平台类型
     * @return 是否支持
     */
    boolean support(Integer socialType);

    /**
     * 构建 AuthRequest 实例
     *
     * @param client 三方应用配置
     * @return AuthRequest 实例
     */
    AuthRequest build(SocialClientDO client);

}
