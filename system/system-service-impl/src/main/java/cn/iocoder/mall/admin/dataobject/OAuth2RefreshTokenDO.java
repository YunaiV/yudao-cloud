package cn.iocoder.mall.admin.dataobject;

import cn.iocoder.common.framework.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 刷新令牌
 *
 * idx_uid
 */
@TableName("oauth2_refresh_token")
@Data
@Accessors(chain = true)
public class OAuth2RefreshTokenDO extends BaseDO {

    /**
     * 刷新令牌
     */
    @TableId(type = IdType.INPUT)
    private String id;
    /**
     * 用户编号
     */
    private Integer userId;
    /**
     * 用户类型
     */
    private Integer userType;
    /**
     * 是否有效
     */
    private Boolean valid;
    /**
     * 过期时间
     */
    private Date expiresTime;

}
