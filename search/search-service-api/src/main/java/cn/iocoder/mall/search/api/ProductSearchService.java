package cn.iocoder.mall.search.api;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.search.api.bo.ESProductPageBO;
import cn.iocoder.mall.search.api.dto.ProductSearchPageDTO;

public interface ProductSearchService {

    CommonResult<Integer> rebuild();

    CommonResult<ESProductPageBO> searchPage(ProductSearchPageDTO searchPageDTO);

}
