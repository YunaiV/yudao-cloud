package cn.iocoder.mall.search.application.controller.users;

import cn.iocoder.common.framework.util.StringUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.SortingField;
import cn.iocoder.mall.search.api.ProductSearchService;
import cn.iocoder.mall.search.api.bo.ProductConditionBO;
import cn.iocoder.mall.search.api.bo.ProductPageBO;
import cn.iocoder.mall.search.api.dto.ProductConditionDTO;
import cn.iocoder.mall.search.api.dto.ProductSearchPageDTO;
import org.apache.dubbo.config.annotation.Reference;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@RestController
@RequestMapping("users/product")
@Api("商品搜索")
public class UsersProductSearchController {

    @Reference(validation = "true")
    private ProductSearchService productSearchService;

    @GetMapping("/page") // TODO 芋艿，后面把 BO 改成 VO
    public CommonResult<ProductPageBO> page(@RequestParam(value = "cid", required = false) Integer cid,
                                            @RequestParam(value = "keyword", required = false) String keyword,
                                            @RequestParam(value = "pageNo", required = false) Integer pageNo,
                                            @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                            @RequestParam(value = "sortField", required = false) String sortField,
                                            @RequestParam(value = "sortOrder", required = false) String sortOrder) {
        // 创建 ProductSearchPageDTO 对象
        ProductSearchPageDTO productSearchPageDTO = new ProductSearchPageDTO().setCid(cid).setKeyword(keyword)
                .setPageNo(pageNo).setPageSize(pageSize);
        if (StringUtil.hasText(sortField) && StringUtil.hasText(sortOrder)) {
            productSearchPageDTO.setSorts(Collections.singletonList(new SortingField(sortField, sortOrder)));
        }
        // 执行搜索
        return success(productSearchService.getSearchPage(productSearchPageDTO));
    }

    @GetMapping("/condition") // TODO 芋艿，后面把 BO 改成 VO
    public CommonResult<ProductConditionBO> condition(@RequestParam(value = "keyword", required = false) String keyword) {
        // 创建 ProductConditionDTO 对象
        ProductConditionDTO productConditionDTO = new ProductConditionDTO().setKeyword(keyword)
                .setFields(Collections.singleton(ProductConditionDTO.FIELD_CATEGORY));
        // 执行搜索
        return success(productSearchService.getSearchCondition(productConditionDTO));
    }

}
