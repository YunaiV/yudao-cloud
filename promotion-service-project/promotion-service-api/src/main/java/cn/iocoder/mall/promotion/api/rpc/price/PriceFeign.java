package cn.iocoder.mall.promotion.api.rpc.price;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.promotion.api.rpc.price.dto.PriceProductCalcReqDTO;
import cn.iocoder.mall.promotion.api.rpc.price.dto.PriceProductCalcRespDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Title:
 * Description:
 *
 * @author zhuyang
 * @version 1.0 2021/10/9
 */
@FeignClient("promotion-service")
public interface PriceFeign {
    @PostMapping("/promotion/price/calcProductPrice")
    public CommonResult<PriceProductCalcRespDTO> calcProductPrice(@RequestBody PriceProductCalcReqDTO calcReqDTO) ;
}
