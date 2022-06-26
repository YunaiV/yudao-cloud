package cn.iocoder.mall.productservice.dal.mysql.dataobject.category;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.mall.mybatis.core.dataobject.DeletableDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* 商品分类 DO
*/
@TableName("product_category")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ProductCategoryDO extends DeletableDO {

    /**
     * 分类编号
     */
    @TableId
    private Integer id;
    /**
     * 父分类编号
     */
    private Integer pid;
    /**
     * 分类名称
     */
    private String name;
    /**
     * 分类描述
     */
    private String description;
    /**
     * 分类图片
     */
    private String picUrl;
    /**
     * 分类排序
     */
    private Integer sort;
    /**
     * 状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;

}
