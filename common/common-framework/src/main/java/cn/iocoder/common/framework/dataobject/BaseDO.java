package cn.iocoder.common.framework.dataobject;

import java.util.Date;

/**
 * 基础实体对象
 */
public class BaseDO {

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后更新时间
     */
    private Date updateTime;
    private Boolean deleted;

    public Date getCreateTime() {
        return createTime;
    }

    public BaseDO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public BaseDO setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public BaseDO setDeleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

}