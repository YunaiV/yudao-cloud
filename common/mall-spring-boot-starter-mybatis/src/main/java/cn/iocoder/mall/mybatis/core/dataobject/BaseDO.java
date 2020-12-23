package cn.iocoder.mall.mybatis.core.dataobject;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体对象
 */
public class BaseDO implements Serializable {

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后更新时间
     */
    private Date updateTime;

    @Override
    public String toString() {
        return "BaseDO{" +
                "createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

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
}
