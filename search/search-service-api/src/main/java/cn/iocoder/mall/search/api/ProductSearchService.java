package cn.iocoder.mall.search.api;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.search.api.bo.ProductConditionBO;
import cn.iocoder.mall.search.api.bo.ProductPageBO;
import cn.iocoder.mall.search.api.dto.ProductConditionDTO;
import cn.iocoder.mall.search.api.dto.ProductSearchPageDTO;

public interface ProductSearchService {

    CommonResult<Integer> rebuild();

    /**
     * 构建商品的搜索索引
     *
     * @param id 商品编号
     * @return 构建结果
     */
    CommonResult<Boolean> save(Integer id);

    CommonResult<ProductPageBO> getSearchPage(ProductSearchPageDTO searchPageDTO);

    CommonResult<ProductConditionBO> getSearchCondition(ProductConditionDTO conditionDTO);

}
