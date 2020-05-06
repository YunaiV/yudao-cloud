package cn.iocoder.mall.product.biz.bo.product;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 商品收藏分页
 * @author xiaofeng
 * @date 2019/07/06 18:37
 * @version 1.0
 */
@Data
@Accessors(chain = true)
public class UserProductSpuCollectionsPageBO implements Serializable {

    /**
     * 返回的数据列表
     */
    private List<UserProductSpuCollectionsBO> list;

    /**
     * 总量
     */
    private Integer total;


}
