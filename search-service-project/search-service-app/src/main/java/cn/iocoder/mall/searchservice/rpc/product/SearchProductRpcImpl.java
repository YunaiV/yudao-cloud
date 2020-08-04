package cn.iocoder.mall.searchservice.rpc.product;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.searchservice.manager.product.SearchProductManager;
import cn.iocoder.mall.searchservice.rpc.product.dto.SearchProductConditionReqDTO;
import cn.iocoder.mall.searchservice.rpc.product.dto.SearchProductConditionRespDTO;
import cn.iocoder.mall.searchservice.rpc.product.dto.SearchProductPageReqDTO;
import cn.iocoder.mall.searchservice.rpc.product.dto.SearchProductRespDTO;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@DubboService
public class SearchProductRpcImpl implements SearchProductRpc {

    @Autowired
    private SearchProductManager searchProductManager;

    @Override
    public CommonResult<PageResult<SearchProductRespDTO>> pageSearchProduct(SearchProductPageReqDTO pageReqDTO) {
        return success(searchProductManager.pageSearchProduct(pageReqDTO));
    }

    @Override
    public CommonResult<SearchProductConditionRespDTO> getSearchProductCondition(SearchProductConditionReqDTO conditionReqDTO) {
        return success(searchProductManager.getSearchProductCondition(conditionReqDTO));
    }

}
