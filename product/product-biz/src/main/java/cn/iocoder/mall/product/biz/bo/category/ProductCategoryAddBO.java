package cn.iocoder.mall.product.biz.bo.category;

import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: jiangweifan
 * @Date: 2020/5/6
 * @Description: 商品分类 - 创建商品分类BO
 */
@Data
@Accessors(chain = true)
// TODO FROM 芋艿 to 伟帆，BO 可以不加 Serializable 接口，因为没序列化的诉求哈。一般 BO 可以创建一个统一的 ProductCategory，可以把 ProductCategoryAllListBO 合并过来
public class ProductCategoryAddBO implements Serializable {

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
