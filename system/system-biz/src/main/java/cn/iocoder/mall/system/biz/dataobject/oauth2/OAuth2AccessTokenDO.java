package cn.iocoder.mall.system.biz.dataobject.oauth2;

import cn.iocoder.mall.mybatis.dataobject.BaseDO;
import cn.iocoder.mall.system.biz.dataobject.account.AccountDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * OAuth2 访问令牌
 */
@TableName("oauth2_access_token")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class OAuth2AccessTokenDO extends BaseDO {

    /**
     * 访问令牌
     */
    @TableId(type = IdType.INPUT)
    private String id;
    /**
     * 刷新令牌
     *
     * 关联 {@link OAuth2RefreshTokenDO#getId()}
     */
    private String refreshToken;
    /**
     * 账号编号
     *
     * 关联 {@link AccountDO#getId()}
     */
    private Integer accountId;
    /**
     * 过期时间
     */
    private Date expiresTime;
    /**
     * 是否有效
     */
    private Boolean valid;

}
