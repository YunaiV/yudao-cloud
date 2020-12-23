package cn.iocoder.mall.product.biz.bo.attr;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class ProductAttrBO implements Serializable {

    /**
     * 规格编号
     */
    private Integer id;
    /**
     * 规格名
     */
    private String name;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;

}
