package cn.iocoder.mall.admin.dataobject;

import cn.iocoder.common.framework.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * OAUTH2 AccessToken
 */
@TableName("oauth2_access_token")
@Data
@Accessors(chain = true)
public class OAuth2AccessTokenDO extends BaseDO {

    /**
     * 访问令牌
     */
    @TableId(type = IdType.INPUT)
    private String id;
    /**
     * 刷新令牌
     */
    private String refreshToken;
    /**
     * 用户编号
     */
    private Integer userId;
    /**
     * 用户类型
     */
    private Integer userType;
    /**
     * 过期时间
     */
    private Date expiresTime;
    /**
     * 是否有效
     */
    private Boolean valid;

}
