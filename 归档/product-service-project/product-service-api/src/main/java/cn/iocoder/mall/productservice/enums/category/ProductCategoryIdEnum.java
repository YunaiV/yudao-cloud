package cn.iocoder.mall.productservice.enums.category;

/**
 * 商品分类的编号枚举
 */
public enum ProductCategoryIdEnum {

    /**
     * 根节点
     */
    ROOT(0);

    private final Integer id;

    ProductCategoryIdEnum(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

}
