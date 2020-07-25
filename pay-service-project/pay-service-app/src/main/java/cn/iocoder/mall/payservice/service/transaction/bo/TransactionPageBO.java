package cn.iocoder.mall.payservice.service.transaction.bo;

import cn.iocoder.common.framework.vo.PageParam;
import lombok.*;
import lombok.experimental.*;
import io.swagger.annotations.*;
import java.util.*;
import javax.validation.constraints.*;

/**
* pay_transaction分页 BO
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class TransactionPageBO extends PageParam {

    /**
    * 编号，自增
    */
    private Integer id;
    /**
    * 应用编号
    */
    private String appId;
    /**
    * 发起交易的 IP
    */
    private String createIp;
    /**
    * 业务线的订单编号
    */
    private String orderId;
    /**
    * 订单商品名
    */
    private String orderSubject;
    /**
    * 订单商品描述
    */
    private String orderDescription;
    /**
    * 订单备注
    */
    private String orderMemo;
    /**
    * 支付金额，单位：分。
    */
    private Integer price;
    /**
    * 订单状态
    */
    private Integer status;
    /**
    * 交易过期时间
    */
    private Date expireTime;
    /**
    * 回调业务线完成时间
    */
    private Date finishTime;
    /**
    * 异步通知地址
    */
    private String notifyUrl;
    /**
    * 成功支付的交易拓展编号
    */
    private Integer extensionId;
    /**
    * 支付成功的支付渠道
    */
    private Integer payChannel;
    /**
    * 第三方支付成功的时间
    */
    private Date paymentTime;
    /**
    * 收到第三方系统通知的时间
    */
    private Date notifyTime;
    /**
    * 第三方的流水号
    */
    private String tradeNo;
    /**
    * 退款总金额
    */
    private Integer refundTotal;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 最后更新时间
    */
    private Date updateTime;

}