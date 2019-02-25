package cn.iocoder.mall.user.dataobject;

import java.util.Date;

/**
 * 用户实体，存储用户基本数据。
 *
 * idx_mobile 唯一索引
 */
public class UserDO {

    /**
     * 用户编号
     */
    private Long id;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 创建时间
     */
    private Date createTime;

    public Long getId() {
        return id;
    }

    public UserDO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public UserDO setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public UserDO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
}