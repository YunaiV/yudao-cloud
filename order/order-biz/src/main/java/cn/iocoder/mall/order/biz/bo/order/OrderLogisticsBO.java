package cn.iocoder.mall.order.biz.bo.order;

import cn.iocoder.mall.mybatis.core.dataobject.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 订单物流信息
 *
 * @author Sin
 * @time 2019-03-19 20:47
 */
@Data
@Accessors(chain = true)
public class OrderLogisticsBO extends BaseDO {

    /**
     * id
     */
    private Integer id;
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
     * 收件详细地址
     */
    private String address;
    /**
     * 物流编号
     */
    private String logisticsNo;
}
