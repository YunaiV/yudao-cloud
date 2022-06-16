package cn.iocoder.mall.promotionservice.controller;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.promotion.api.rpc.price.dto.PriceProductCalcReqDTO;
import cn.iocoder.mall.promotion.api.rpc.price.dto.PriceProductCalcRespDTO;
import cn.iocoder.mall.promotionservice.manager.price.PriceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
 * Title:
 * Description:
 *
 * @author zhuyang
 * @version 1.0 2021/10/9
 */
@RestController
@RequestMapping("/promotion/price")
public class PriceController {

    @Autowired
    private PriceManager priceManager;

    @PostMapping("calcProductPrice")
    public CommonResult<PriceProductCalcRespDTO> calcProductPrice(@RequestBody PriceProductCalcReqDTO calcReqDTO) {
        return success(priceManager.calcProductPrice(calcReqDTO));
    }
}
