package cn.iocoder.mall.tradeservice.dal.mysql.dataobject.order;

import cn.iocoder.mall.mybatis.core.dataobject.DeletableDO;
import cn.iocoder.mall.tradeservice.enums.logistics.LogisticsDeliveryTypeEnum;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 交易订单的物流信息
 */
@TableName(value = "logistics_order")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class TradeOrderLogisticsDO extends DeletableDO {

    /**
     * 物流订单号
     */
    private Integer id;
    /**
     * 配送类型
     *
     * 枚举 {@link LogisticsDeliveryTypeEnum}
     */
    private Integer deliveryType;
    /**
     * 物流公司编号
     *
     * 使用 DataDict 数据字典 EXPRESS
     */
    private Integer expressId;
    /**
     * 物流公司单号
     */
    private String expressNo;
    /**
     * 交易订单号
     *
     * 外键 {@link TradeOrderDO#getId()}
     */
    private Integer tradeOrderId;
    /**
     * 商品列表
     */
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<Item> items;

    /**
     * 商品项
     */
    @Data
    public static class Item {

        /**
         * 商品 SKU 编号
         */
        private Integer skuId;
        /**
         * 数量
         */
        private Integer quantity;

    }

}
