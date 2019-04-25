package cn.iocoder.mall.search.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 商品搜索条件返回 BO
 */
@Data
@Accessors(chain = true)
public class ProductConditionBO {

    /**
     * 商品分类数组
     */
    private List<Category> categories;

    @Data
    @Accessors(chain = true)
    public static class Category {

        /**
         * 分类编号
         */
        private Integer id;
        /**
         * 分类名称
         */
        private String name;

    }

}
