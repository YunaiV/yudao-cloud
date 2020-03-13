package cn.iocoder.mall.demo.business.dataobject.product;

import cn.iocoder.common.framework.dataobject.DeletableDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Demo 商品
 */
@Data
@Accessors(chain = true)
@TableName(value = "product")
public class DemoProductDO extends DeletableDO {

    /**
     * 编号
     */
    private Integer id;

    /**
     * 名字
     */
    private String name;

    /**
     * 价格
     */
    private Integer price;
    /**
     * 库存数量
     */
    private Integer quantity;

}
