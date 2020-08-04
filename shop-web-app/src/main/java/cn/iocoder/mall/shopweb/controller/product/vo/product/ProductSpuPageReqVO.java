package cn.iocoder.mall.shopweb.controller.product.vo.product;

import cn.iocoder.common.framework.vo.PageParam;
import cn.iocoder.common.framework.vo.SortingField;

import java.util.List;

public class ProductSpuPageReqVO extends PageParam {

    /**
     * 分类编号
     */
    private Integer cid;
    /**
     * 关键字
     */
    private String keyword;

    /**
     * 排序字段数组
     */
    private List<SortingField> sorts;

}
