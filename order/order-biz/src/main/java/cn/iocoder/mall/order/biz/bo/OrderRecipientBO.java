package cn.iocoder.mall.order.biz.bo;

import cn.iocoder.mall.mybatis.core.dataobject.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 订单收件人信息 order_recipient
 *
 * @author Sin
 * @time 2019-03-31 11:37
 */
@Data
@Accessors(chain = true)
public class OrderRecipientBO extends BaseDO { // TODO FROM 芋艿 TO 小范，不要继承 BaseDO

    /**
     * 编号
     */
    private Integer id;
    /**
     * 订单id
     */
    private Integer orderId;
    /**
     * 收件区域编号
     */
    private String areaNo;
    /**
     * 收件人名称
     */
    private String name;
    /**
     * 收件手机号
     */
    private String mobile;
    /**
     * 手机方式
     */
    private Integer type;
    /**
     * 收件详细地址
     */
    private String address;
}
