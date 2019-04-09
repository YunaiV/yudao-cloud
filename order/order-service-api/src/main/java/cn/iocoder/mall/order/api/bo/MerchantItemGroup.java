package cn.iocoder.mall.order.api.bo;

import java.util.List;

/**
 * 商家商品分组
 */
public class MerchantItemGroup {

    /**
     * 商品分组数组
     */
    private List<CartItemGroupBO> itemGroups;
    /**
     * 运费详情
     */
    private PostageDetailBO postageDetail;

}
