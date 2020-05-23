package cn.iocoder.mall.search.biz.api.user;

import cn.iocoder.common.framework.vo.CommonResult;

public interface ProductSearchRPC {

    CommonResult<Integer> rebuild();

    /**
     * 构建商品的搜索索引
     *
     * @param id 商品编号
     * @return 构建结果
     */
    CommonResult<Boolean> save(Integer id);

//    ProductPageBO getSearchPage(ProductSearchPageDTO searchPageDTO);
//
//    ProductConditionBO getSearchCondition(ProductConditionDTO conditionDTO);

}
