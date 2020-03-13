package cn.iocoder.mall.demo.business.dataobject.order;

import cn.iocoder.common.framework.dataobject.DeletableDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Demo 订单
 */
@Data
@Accessors(chain = true)
@TableName(value = "orders")
public class DemoOrderDO extends DeletableDO {

    /**
     * id
     */
    private Integer id;

    /**
     * 用户编号
     */
    private Integer userId;

    /**
     * 商品编号
     */
    private Integer productId;

    /**
     * 状态
     *
     * - 1、待付款
     * - 2、待发货
     * - 3、待收获
     * - 4、已完成
     * - 5、已关闭
     */
    private Integer status;

}
