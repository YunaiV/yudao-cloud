package cn.iocoder.mall.product.biz.dataobject.stock;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * Product 库存
 */
@Deprecated // TODO 芋艿，咱暂时不加库存表和库存服务
@Data
@Accessors(chain = true)
public class ProductStockDO {

    /**
     * 编号，自增
     */
    private Integer id;
    /**
     * SKU 编号
     */
    private Integer skuId;
    /**
     * 库存数
     */
    private Integer quantity;
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
