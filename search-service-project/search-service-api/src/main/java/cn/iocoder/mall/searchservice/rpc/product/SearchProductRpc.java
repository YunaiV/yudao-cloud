package cn.iocoder.mall.searchservice.rpc.product;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.searchservice.rpc.product.dto.SearchProductConditionReqDTO;
import cn.iocoder.mall.searchservice.rpc.product.dto.SearchProductConditionRespDTO;
import cn.iocoder.mall.searchservice.rpc.product.dto.SearchProductPageReqDTO;
import cn.iocoder.mall.searchservice.rpc.product.dto.SearchProductRespDTO;

/**
 * 商品搜索 RPC 接口
 */
public interface SearchProductRpc {

    /**
     * 获得商品搜索分页
     *
     * @param pageReqDTO 分页请求 DTO
     * @return 商品搜索分页结果
     */
    CommonResult<PageResult<SearchProductRespDTO>> pageSearchProduct(SearchProductPageReqDTO pageReqDTO);

    /**
     * 获得商品搜索条件
     *
     * @param conditionReqDTO 搜索条件 DTO
     * @return 搜索条件
     */
    CommonResult<SearchProductConditionRespDTO> getSearchProductCondition(SearchProductConditionReqDTO conditionReqDTO);

}
