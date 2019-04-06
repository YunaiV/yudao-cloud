package cn.iocoder.mall.promotion.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 商品推荐分页 BO
 */
@Data
@Accessors(chain = true)
public class ProductRecommendPageBO {

    /**
     * ProductRecommend 数组
     */
    private List<ProductRecommendBO> list;
    /**
     * 总量
     */
    private Integer total;

}
