package cn.iocoder.mall.systemservice.dal.mysql.dataobject.oauth;

import cn.iocoder.common.framework.enums.UserTypeEnum;
import cn.iocoder.mall.mybatis.core.dataobject.DeletableDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * OAuth2 刷新令牌
 *
 * idx_userId 索引：对应 {@link #userId} 字段
 */
@TableName("oauth2_refresh_token")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class OAuth2RefreshTokenDO extends DeletableDO {

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
     *
     * 枚举 {@link UserTypeEnum}
     */
    private Integer userType;
    /**
     * 过期时间
     */
    private Date expiresTime;
    /**
     * 创建 IP
     */
    private String createIp;

}
