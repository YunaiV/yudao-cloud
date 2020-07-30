package cn.iocoder.mall.productservice.dal.mysql.dataobject.sku;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.mall.mybatis.core.dataobject.DeletableDO;
import cn.iocoder.mall.productservice.dal.mysql.dataobject.attr.ProductAttrValueDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 商品 SKU
 */
@TableName("product_sku")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ProductSkuDO extends DeletableDO {

    /**
     * sku 编号
     */
    private Integer id;
    /**
     * 商品编号
     */
    private Integer spuId;

    // TODO 店铺编号

    /**
     * 状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;
    /**
     * 图片地址
     */
    private String picUrl;
    /**
     * 规格值({@link ProductAttrValueDO#getId()})数组
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
//    /**
//     * 商品在付款减库存的状态下，该Sku上未付款的订单数量
//     */
//    private Integer withHoldQuantity;
//    /**
//     * 销量
//     */
//    private Integer soldNum;

}
