package cn.iocoder.mall.searchservice.controller;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.searchservice.manager.product.SearchProductManager;
import cn.iocoder.mall.searchservice.rpc.product.dto.SearchProductConditionReqDTO;
import cn.iocoder.mall.searchservice.rpc.product.dto.SearchProductConditionRespDTO;
import cn.iocoder.mall.searchservice.rpc.product.dto.SearchProductPageReqDTO;
import cn.iocoder.mall.searchservice.rpc.product.dto.SearchProductRespDTO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
 * Title:
 * Description:
 *
 * @author zhuyang
 * @version 1.0 2021/10/9
 */
@RestController
@RequestMapping("/search/product")
@Api("商品搜索")
public class SearchProductController {
    @Autowired
    private SearchProductManager searchProductManager;
    /**
     * 获得商品搜索分页
     *
     * @param pageReqDTO 分页请求 DTO
     * @return 商品搜索分页结果
     */
    @PostMapping("/pageSearchProduct")
    CommonResult<PageResult<SearchProductRespDTO>> pageSearchProduct(@RequestBody SearchProductPageReqDTO pageReqDTO){
        return  success(searchProductManager.pageSearchProduct(pageReqDTO));
    }

    /**
     * 获得商品搜索条件
     *
     * @param conditionReqDTO 搜索条件 DTO
     * @return 搜索条件
     */
    @PostMapping("/getSearchProductCondition")
    CommonResult<SearchProductConditionRespDTO> getSearchProductCondition(@RequestBody SearchProductConditionReqDTO conditionReqDTO){
        return success(searchProductManager.getSearchProductCondition(conditionReqDTO));
    }

}
