package cn.iocoder.mall.search.api;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.search.api.dto.ProductSearchPageDTO;

public interface ProductSearchService {

    CommonResult<Integer> rebuild();

    CommonResult searchPage(ProductSearchPageDTO searchPageDTO);

}
