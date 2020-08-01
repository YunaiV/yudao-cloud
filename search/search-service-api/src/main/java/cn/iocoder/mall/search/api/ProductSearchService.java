package cn.iocoder.mall.search.biz;

import cn.iocoder.mall.search.biz.bo.ProductConditionBO;
import cn.iocoder.mall.search.biz.bo.ProductPageBO;
import cn.iocoder.mall.search.biz.dto.ProductConditionDTO;
import cn.iocoder.mall.search.biz.dto.ProductSearchPageDTO;

public interface ProductSearchService {

    Integer rebuild();

    ProductPageBO getSearchPage(ProductSearchPageDTO searchPageDTO);

    ProductConditionBO getSearchCondition(ProductConditionDTO conditionDTO);

}
