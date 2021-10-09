package cn.iocoder.mall.tradeservice.client.promotion;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.promotion.api.rpc.price.PriceFeign;
import cn.iocoder.mall.promotion.api.rpc.price.dto.PriceProductCalcReqDTO;
import cn.iocoder.mall.promotion.api.rpc.price.dto.PriceProductCalcRespDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceClient {

    @Autowired
    private PriceFeign priceFeign;
    /**
     * 计算商品们的价格
     *
     * @param userId 用户编号
     * @param items 商品 SKU 集合
     * @param couponCardId 优惠劵编号，允许为空
     * @return 价格
     */
    public PriceProductCalcRespDTO calcProductPrice(Integer userId, List<PriceProductCalcReqDTO.Item> items,
                                                    Integer couponCardId) {
        CommonResult<PriceProductCalcRespDTO> calcProductPriceResult = priceFeign.calcProductPrice(
                new PriceProductCalcReqDTO().setUserId(userId).setItems(items).setCouponCardId(couponCardId));
        calcProductPriceResult.checkError();
        return calcProductPriceResult.getData();
    }

}
