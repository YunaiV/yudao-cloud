package cn.iocoder.mall.systemservice.rpc.oauth.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * OAuth2.0 访问令牌创建 DTO
 */
@Data
@Accessors(chain = true)
public class OAuth2CreateAccessTokenDTO implements Serializable {

    /**
     * 用户编号
     */
    private Integer userId;
    /**
     * 用户类型
     */
    private Integer userType;
    /**
     * 创建 IP
     */
    private String createIp;

}
