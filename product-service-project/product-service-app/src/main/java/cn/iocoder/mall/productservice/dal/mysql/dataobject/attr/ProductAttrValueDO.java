package cn.iocoder.mall.productservice.dal.mysql.dataobject.attr;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.mall.mybatis.core.dataobject.DeletableDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 商品规格值 DO
 */
@TableName("product_attr_value")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ProductAttrValueDO extends DeletableDO {

    /**
     * 规格值编号
     */
    private Integer id;
    /**
     * 规格键编号
     *
     * 外键 {@link ProductAttrKeyDO#getId()}
     */
    private Integer attrKeyId;
    /**
     * 规格值名字
     */
    private String name;
    /**
     * 状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;

}
