package cn.iocoder.mall.productservice.dal.mysql.dataobject.brand;

import cn.iocoder.mall.mybatis.core.dataobject.DeletableDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* 商品品牌
*/
@TableName("product_brand")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ProductBrandDO extends DeletableDO {

    /**
     * 品牌编号（主键）
     */
    @TableId
    private Integer id;
    /**
     * 品牌名称
     */
    private String name;
    /**
     * 品牌描述
     */
    private String description;
    /**
     * 品牌名图片
     */
    private String picUrl;
    /**
     * 状态
     */
    private Integer status;

}
