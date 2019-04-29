package cn.iocoder.mall.order.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 订单物流 - 详细信息
 *
 * @author Sin
 * @time 2019-04-12 22:03
 */
@Data
@Accessors(chain = true)
public class OrderLogisticsInfoWithOrderBO implements Serializable {

    /**
     * 订单id
     */
    private Integer orderId;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 物流信息
     */
    private List<Logistics> logistics;

    @Data
    @Accessors(chain = true)
    public static class Logistics {

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
         * 物流 (字典)
         */
        private Integer logistics;
        /**
         * 物流 (字典) 转换后的值
         */
        private String logisticsText;
        /**
         * 物流编号
         */
        private String logisticsNo;

        ///
        /// 物流信息

        private List<LogisticsDetail> details;
    }

    @Data
    @Accessors(chain = true)
    public static class LogisticsDetail {
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
         * 物流时间 text
         */
        private String logisticsTimeText;
        /**
         * 物流信息
         */
        private String logisticsInformation;
    }
}
