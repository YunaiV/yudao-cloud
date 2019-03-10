package cn.iocoder.mall.user.dataobject;

import java.util.Date;

/**
 * 用户注册信息
 */
public class UserRegisterDO {

    /**
     * 用户编号
     */
    private Integer id;
    /**
     * 创建时间
     */
    private Date createTime;

    // TODO 芋艿 ip
    // TODO 芋艿 ua
    // TODO 芋艿 方式，手机注册、qq 等等


    public Integer getId() {
        return id;
    }

    public UserRegisterDO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public UserRegisterDO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

}