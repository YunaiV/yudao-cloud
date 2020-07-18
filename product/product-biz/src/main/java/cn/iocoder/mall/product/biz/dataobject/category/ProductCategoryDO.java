package cn.iocoder.mall.product.biz.dataobject.category;

import cn.iocoder.mall.mybatis.core.dataobject.DeletableDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 商品分类
 */
@Data
@Accessors(chain = true)
@TableName("product_category")
public class ProductCategoryDO extends DeletableDO {

    /**
     * 分类编号
     */
    private Integer id;
    /**
     * 父分类编号
     *
     * 如果不存在父级，则 pid = 0 。
     */
    private Integer pid;
    /**
     * 名称
     */
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * 分类图片
     */
    private String picUrl;
    /**
     * 排序值
     */
    private Integer sort;
    /**
     * 状态
     *
     * 1-开启
     * 2-关闭
     */
    private Integer status;

}
