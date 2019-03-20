package cn.iocoder.common.framework.dataobject;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体对象
 */
public class BaseDO implements Serializable {

    /**
     * 是否删除 - 是
     */
    public static final Integer DELETED_YES = 1;
    /**
     * 是否删除 - 否
     */
    public static final Integer DELETED_NO = 0;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后更新时间
     */
    private Date updateTime;
    /**
     * 是否删除
     */
    private Integer deleted;

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

    public Integer getDeleted() {
        return deleted;
    }

    public BaseDO setDeleted(Integer deleted) {
        this.deleted = deleted;
        return this;
    }

}