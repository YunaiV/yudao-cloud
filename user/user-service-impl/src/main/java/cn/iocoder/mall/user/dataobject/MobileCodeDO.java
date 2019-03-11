package cn.iocoder.mall.user.dataobject;

import java.util.Date;

// TODO 优化，IP
public class MobileCodeDO {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 验证码
     */
    private String code;
    /**
     * 今日发送的第几条
     */
    private Integer todayIndex;
    /**
     * 是否使用
     */
    private Boolean used;
    /**
     * 注册的用户编号
     */
    private Integer usedUserId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 使用时间
     */
    private Date usedTime;

    public Integer getId() {
        return id;
    }

    public MobileCodeDO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public MobileCodeDO setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getCode() {
        return code;
    }

    public MobileCodeDO setCode(String code) {
        this.code = code;
        return this;
    }

    public Integer getTodayIndex() {
        return todayIndex;
    }

    public MobileCodeDO setTodayIndex(Integer todayIndex) {
        this.todayIndex = todayIndex;
        return this;
    }

    public Boolean getUsed() {
        return used;
    }

    public MobileCodeDO setUsed(Boolean used) {
        this.used = used;
        return this;
    }

    public Integer getUsedUserId() {
        return usedUserId;
    }

    public MobileCodeDO setUsedUserId(Integer usedUserId) {
        this.usedUserId = usedUserId;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public MobileCodeDO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUsedTime() {
        return usedTime;
    }

    public MobileCodeDO setUsedTime(Date usedTime) {
        this.usedTime = usedTime;
        return this;
    }

}