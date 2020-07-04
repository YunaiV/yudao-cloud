package cn.iocoder.mall.promotion.biz.dataobject;


import cn.iocoder.mall.mybatis.dataobject.DeletableDO;
import cn.iocoder.mall.promotion.api.constant.ProductRecommendTypeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 商品推荐 DO
 */
@Data
@Accessors(chain = true)
public class ProductRecommendDO extends DeletableDO {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 类型
     *
     * {@link ProductRecommendTypeEnum}
     */
    private Integer type;
    /**
     * 商品 Spu 编号
     */
    private Integer productSpuId;
    // TODO 芋艿，商品 spu 名
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 状态
     *
     * {@link cn.iocoder.common.framework.enums.CommonStatusEnum}
     */
    private Integer status;
    /**
     * 备注
     */
    private String memo;

}
