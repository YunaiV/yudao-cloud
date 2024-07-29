package cn.iocoder.yudao.gateway.filter.security;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 登录用户信息
 *
 * copy from yudao-spring-boot-starter-security 的 LoginUser 类
 *
 * @author 芋道源码
 */
@Data
public class LoginUser {

    /**
     * 用户编号
     */
    private Long id;
    /**
     * 用户类型
     */
    private Integer userType;
    /**
     * 额外的用户信息
     */
    private Map<String, String> info;
    /**
     * 租户编号
     */
    private Long tenantId;
    /**
     * 授权范围
     */
    private List<String> scopes;
    /**
     * 过期时间
     */
    private LocalDateTime expiresTime;

}
