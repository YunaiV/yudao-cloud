package cn.iocoder.mall.user.dataobject;

import java.util.Date;

/**
 * 刷新令牌
 *
 * idx_uid
 */
public class OAuth2RefreshTokenDO {

    /**
     * 刷新令牌
     */
    private String id;
    /**
     * 用户编号
     */
    private Long uid;
    /**
     * 是否有效
     */
    private Boolean valid;
    /**
     * 过期时间
     */
    private Date expiresTime;
    /**
     * 创建时间
     */
    private Date createTime;

    public String getId() {
        return id;
    }

    public OAuth2RefreshTokenDO setId(String id) {
        this.id = id;
        return this;
    }

    public Long getUid() {
        return uid;
    }

    public OAuth2RefreshTokenDO setUid(Long uid) {
        this.uid = uid;
        return this;
    }

    public Boolean getValid() {
        return valid;
    }

    public OAuth2RefreshTokenDO setValid(Boolean valid) {
        this.valid = valid;
        return this;
    }

    public Date getExpiresTime() {
        return expiresTime;
    }

    public OAuth2RefreshTokenDO setExpiresTime(Date expiresTime) {
        this.expiresTime = expiresTime;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public OAuth2RefreshTokenDO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

}
