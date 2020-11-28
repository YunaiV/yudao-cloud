package cn.iocoder.mall.pay.api;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.pay.api.bo.refund.PayRefundPageBO;
import cn.iocoder.mall.pay.api.bo.refund.PayRefundSubmitBO;
import cn.iocoder.mall.pay.api.dto.refund.PayRefundPageDTO;
import cn.iocoder.mall.pay.api.dto.refund.PayRefundSubmitDTO;

public interface PayRefundService {

    CommonResult<PayRefundSubmitBO> submitRefund(PayRefundSubmitDTO payRefundSubmitDTO);

    /**
     * 更新退款支付成功
     *
     * 该接口用于不同支付平台，退款成功后，回调该接口
     *
     * @param payChannel 支付渠道
     * @param params 回调参数。
     *               因为不同平台，能够提供的参数不同，所以使用 String 类型统一接收，然后在使用不同的 AbstractPaySDK 进行处理。
     * @return 是否支付成功
     */
    CommonResult<Boolean> updateRefundSuccess(Integer payChannel, String params);

    PayRefundPageBO getRefundPage(PayRefundPageDTO payRefundPageDTO);

}
