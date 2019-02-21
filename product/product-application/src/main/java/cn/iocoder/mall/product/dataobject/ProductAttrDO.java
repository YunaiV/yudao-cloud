package cn.iocoder.mall.product.dataobject;

import java.util.Date;

/**
 * Product 规格
 */
public class ProductAttrDO {

    /**
     * 规格编号
     */
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后更新时间
     */
    private Date updateTime;
    /**
     * 状态
     *
     * 1-正常
     * 2-删除
     */
    private Integer status;

}