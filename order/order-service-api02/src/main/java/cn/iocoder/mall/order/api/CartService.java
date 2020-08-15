package cn.iocoder.mall.order.api;

import cn.iocoder.mall.order.api.bo.CalcSkuPriceBO;

public interface CartService {

    /**
     * 计算指定商品 SKU 的金额，并返回计算结果
     *
     * TODO 芋艿，此处只会计算，限时折扣带来的价格变化。
     *
     * @param skuId 商品 SKU 编号
     * @return 计算订单金额结果
     */
    CalcSkuPriceBO calcSkuPrice(Integer skuId);

}
