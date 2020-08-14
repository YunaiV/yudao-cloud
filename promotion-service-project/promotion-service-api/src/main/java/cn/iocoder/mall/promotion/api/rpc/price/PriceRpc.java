package cn.iocoder.mall.promotion.api.rpc.price;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.promotion.api.rpc.price.dto.PriceProductCalcReqDTO;
import cn.iocoder.mall.promotion.api.rpc.price.dto.PriceProductCalcRespDTO;

/**
 * 价格 Rpc 接口，提供价格计算的功能
 */
public interface PriceRpc {

    CommonResult<PriceProductCalcRespDTO> calcProductPrice(PriceProductCalcReqDTO calcReqDTO);

}
