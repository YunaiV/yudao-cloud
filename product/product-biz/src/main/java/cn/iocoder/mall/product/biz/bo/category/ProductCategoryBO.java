package cn.iocoder.mall.product.biz.bo.category;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Author: jiangweifan
 * @Date: 2020/5/6
 * @Description: 商品分类 - 商品分类统一BO
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
    /**
     * 创建时间
     */
    private Date createTime;

}
