package cn.iocoder.yudao.module.system.dal.dataobject.social;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.enums.UserTypeEnum;
import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import cn.iocoder.yudao.module.system.enums.social.SocialTypeEnum;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.model.AuthCallback;

/**
 * 社交客户端 DO
 *
 * 对应 {@link AuthConfig} 配置，满足不同租户，有自己的客户端配置，实现社交（三方）登录
 *
 * @author 芋道源码
 */
@TableName(value = "system_social_client", autoResultMap = true)
@KeySequence("system_social_client_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SocialClientDO extends TenantBaseDO {

    /**
     * 编号，自增
     */
    @TableId
    private Long id;
    /**
     * 应用名
     */
    private String name;
    /**
     * 社交类型
     *
     * 枚举 {@link SocialTypeEnum}
     */
    private Integer socialType;
    /**
     * 用户类型
     *
     * 目的：不同用户类型，对应不同的小程序，需要自己的配置
     *
     * 枚举 {@link UserTypeEnum}
     */
    private Integer userType;
    /**
     * 状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;

    /**
     * 客户端 id
     */
    private String clientId;
    /**
     * 客户端 Secret
     */
    private String clientSecret;

    /**
     * 代理编号
     *
     * 目前只有部分“社交类型”在使用：
     * 1. 企业微信：对应授权方的网页应用 ID
     */
    private String agentId;

    /**
     * 登录成功后的回调地址
     */
    private String redirectUri;

    /**
     * 忽略校验 {@code redirectUri} 参数，默认不开启。当 {@code ignoreCheckRedirectUri} 为 {@code true} 时，
     * {@link me.zhyd.oauth.utils.AuthChecker#checkConfig(AuthConfig, AuthSource)} 将不会校验 {@code redirectUri} 的合法性。
     *
     * @since 1.16.1
     */
    private Boolean ignoreCheckRedirectUri;

    /**
     * 忽略校验 {@code state} 参数，默认不开启。当 {@code ignoreCheckState} 为 {@code true} 时，
     * {@link me.zhyd.oauth.request.AuthDefaultRequest#login(AuthCallback)} 将不会校验 {@code state} 的合法性。
     * <p>
     * 使用场景：当且仅当使用自实现 {@code state} 校验逻辑时开启
     * <p>
     * 以下场景使用方案仅作参考：
     * 1. 授权、登录为同端，并且全部使用 JustAuth 实现时，该值建议设为 {@code false};
     * 2. 授权和登录为不同端实现时，比如前端页面拼装 {@code authorizeUrl}，并且前端自行对{@code state}进行校验，
     * 后端只负责使用{@code code}获取用户信息时，该值建议设为 {@code true};
     *
     * <strong>如非特殊需要，不建议开启这个配置</strong>
     * <p>
     * 该方案主要为了解决以下类似场景的问题：
     *
     * @see <a href="https://github.com/justauth/JustAuth/issues/83">https://github.com/justauth/JustAuth/issues/83</a>
     * @since 1.15.6
     */
    private Boolean ignoreCheckState;

}
