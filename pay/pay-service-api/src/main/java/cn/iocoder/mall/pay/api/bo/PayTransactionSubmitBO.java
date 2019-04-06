package cn.iocoder.mall.pay.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 支付交易提交结果 BO
 */
@Data
@Accessors(chain = true)
public class PayTransactionSubmitBO {

    /**
     * 支付交易拓展单编号
     */
    private Integer id;
    /**
     * 调用三方平台的响应结果
     */
    private String invokeResponse;

}
