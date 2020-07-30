package cn.iocoder.mall.productservice.service.sku.bo;

import cn.iocoder.mall.productservice.dal.mysql.dataobject.attr.ProductAttrValueDO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 商品 SKU BO
 */
@Data
@Accessors(chain = true)
public class ProductSkuBO {

    /**
     * sku 编号
     */
    private Integer id;
    /**
     * 商品编号
     */
    private Integer spuId;
    /**
     * 状态
     *
     * 1-正常
     * 2-禁用
     */
    private Integer status;
    /**
     * 图片地址
     */
    private String picUrl;
    /**
     * 规格值({@link ProductAttrValueDO})数组
     *
     * 数组，以逗号分隔
     */
    private String attrs;
    /**
     * 价格，单位：分
     */
    private Integer price;
    /**
     * 库存数量
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
     * 是否删除
     */
    private Integer deleted;

}
