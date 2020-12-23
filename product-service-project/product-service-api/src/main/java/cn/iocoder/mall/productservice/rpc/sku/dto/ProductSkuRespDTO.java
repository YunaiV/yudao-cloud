package cn.iocoder.mall.productservice.rpc.sku.dto;

import cn.iocoder.mall.productservice.enums.sku.ProductSkuDetailFieldEnum;
import cn.iocoder.mall.productservice.rpc.attr.dto.ProductAttrKeyValueRespDTO;
import cn.iocoder.mall.productservice.rpc.spu.dto.ProductSpuRespDTO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 商品 SKU Response DTO
 */
@Data
@Accessors(chain = true)
public class ProductSkuRespDTO implements Serializable {

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
     */
    private Integer status;
    /**
     * 图片地址
     */
    private String picUrl;
    /**
     * 规格值编号数组
     */
    private List<Integer> attrValueIds;
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
     * 规格值数组
     *
     * 需要设置 {@link ProductSkuDetailFieldEnum#ATTR} 才返回
     */
    private List<ProductAttrKeyValueRespDTO> attrs;
    /**
     * 商品 SPU 信息
     *
     * 需要设置 {@link ProductSkuDetailFieldEnum#SPU} 才返回
     *
     * // TODO 芋艿，后续考虑怎么优化下，目前是内嵌了别的 dto
     */
    private ProductSpuRespDTO spu;

}
