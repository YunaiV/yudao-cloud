package cn.iocoder.mall.order.biz.dataobject;

import cn.iocoder.common.framework.dataobject.DeletableDO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 订单物流 - 详细信息
 *
 * - 同步第三方物流信息
 *
 * @author Sin
 * @time 2019-03-19 20:48
 */
@Data
@Accessors(chain = true)
public class OrderLogisticsDetailDO extends DeletableDO {

    /**
     * id
     */
    private Integer id;
    /**
     * 物流id
     */
    private Integer orderLogisticsId;
    /**
     * 物流时间
     */
    private Date logisticsTime;
    /**
     * 物流信息
     */
    private String logisticsInformation;

}
