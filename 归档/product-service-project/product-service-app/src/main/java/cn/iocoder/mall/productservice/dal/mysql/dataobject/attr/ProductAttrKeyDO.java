package cn.iocoder.mall.productservice.dal.mysql.dataobject.attr;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.mall.mybatis.core.dataobject.DeletableDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Product 规格键 DO
 */
@TableName("product_attr_key")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ProductAttrKeyDO extends DeletableDO {

    /**
     * 规格编号
     */
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;

}
