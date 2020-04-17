package cn.iocoder.mall.system.biz.bo.ouath2;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * TODO 注释
 */
@Data
@Accessors(chain = true)
public class OAuth2AccessTokenBO {

    /**
     * 访问令牌
     */
    private String id;
    /**
     * 刷新令牌
     */
    private String refreshToken;
    /**
     * 账号编号
     */
    private Integer accountId;
    /**
     * 过期时间
     */
    private Date expiresTime;

}
