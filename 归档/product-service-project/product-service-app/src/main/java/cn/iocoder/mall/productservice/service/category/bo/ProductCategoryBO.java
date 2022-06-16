package cn.iocoder.mall.productservice.service.category.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
* 商品分类 BO
*/
@Data
@Accessors(chain = true)
public class ProductCategoryBO {

    /**
     * 分类编号
     */
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
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;

}
