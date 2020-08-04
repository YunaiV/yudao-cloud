package cn.iocoder.mall.searchservice.rpc.product;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.searchservice.rpc.product.dto.SearchProductPageReqDTO;
import cn.iocoder.mall.searchservice.rpc.product.dto.SearchProductRespDTO;

/**
 * 商品搜索 RPC 接口
 */
public interface SearchProductRpc {

    CommonResult<PageResult<SearchProductRespDTO>> pageSearchProduct(SearchProductPageReqDTO pageQueryReqDTO);

}
