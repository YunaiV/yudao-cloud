package cn.iocoder.mall.tradeservice.client.promotion;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.promotion.api.rpc.price.PriceRpc;
import cn.iocoder.mall.promotion.api.rpc.price.dto.PriceProductCalcReqDTO;
import cn.iocoder.mall.promotion.api.rpc.price.dto.PriceProductCalcRespDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceClient {

    @DubboReference(version = "${dubbo.consumer.PriceRpc.version}")
    private PriceRpc priceRpc;

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
        CommonResult<PriceProductCalcRespDTO> calcProductPriceResult = priceRpc.calcProductPrice(
                new PriceProductCalcReqDTO().setUserId(userId).setItems(items).setCouponCardId(couponCardId));
        calcProductPriceResult.checkError();
        return calcProductPriceResult.getData();
    }

}
