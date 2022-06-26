package cn.iocoder.mall.searchservice.rpc.product;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.searchservice.rpc.product.dto.SearchProductConditionReqDTO;
import cn.iocoder.mall.searchservice.rpc.product.dto.SearchProductConditionRespDTO;
import cn.iocoder.mall.searchservice.rpc.product.dto.SearchProductPageReqDTO;
import cn.iocoder.mall.searchservice.rpc.product.dto.SearchProductRespDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Title:
 * Description:
 *
 * @author zhuyang
 * @version 1.0 2021/10/9
 */
@FeignClient(value = "search-service")
public interface SearchProductFeign {
    /**
     * 获得商品搜索分页
     *
     * @param pageReqDTO 分页请求 DTO
     * @return 商品搜索分页结果
     */
    @PostMapping("/search/product//pageSearchProduct")
    CommonResult<PageResult<SearchProductRespDTO>> pageSearchProduct(@RequestBody SearchProductPageReqDTO pageReqDTO);


    /**
     * 获得商品搜索条件
     *
     * @param conditionReqDTO 搜索条件 DTO
     * @return 搜索条件
     */
    @PostMapping("/search/product/getSearchProductCondition")
    CommonResult<SearchProductConditionRespDTO> getSearchProductCondition(@RequestBody SearchProductConditionReqDTO conditionReqDTO);
}
