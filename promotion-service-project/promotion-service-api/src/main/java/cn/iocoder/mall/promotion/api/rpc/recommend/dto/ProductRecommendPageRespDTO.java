package cn.iocoder.mall.promotion.api.rpc.recommend.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 商品推荐分页 BO
 */
@Data
@Accessors(chain = true)
public class ProductRecommendPageRespDTO implements Serializable {

    /**
     * ProductRecommend 数组
     */
    private List<ProductRecommendRespDTO> list;
    /**
     * 总量
     */
    private Integer total;

}
