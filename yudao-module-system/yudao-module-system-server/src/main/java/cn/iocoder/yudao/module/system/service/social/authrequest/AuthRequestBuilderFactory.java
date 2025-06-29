package cn.iocoder.yudao.module.system.service.social.authrequest;

import cn.iocoder.yudao.module.system.dal.dataobject.social.SocialClientDO;
import me.zhyd.oauth.request.AuthRequest;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 授权请求构建器工厂，根据 socialType 查找对应实现并构建 AuthRequest
 *
 * @author yuchao
 */
@Component
public class AuthRequestBuilderFactory {
    private final List<AuthRequestBuilder> builders;

    public AuthRequestBuilderFactory(List<AuthRequestBuilder> builders) {
        this.builders = builders;
    }

    /**
     * 根据 socialType 查找对应的构建器，并构建 AuthRequest
     *
     * @param socialType 社交平台类型
     * @param client 三方应用配置
     * @return AuthRequest 实例
     */
    public AuthRequest build(Integer socialType, SocialClientDO client) {
        return builders.stream()
                .filter(builder -> builder.support(socialType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("不支持的社交类型: " + socialType))
                .build(client);
    }
}
