package cn.iocoder.mall.search.rest.controller.user;

import cn.iocoder.common.framework.constant.MallConstants;
import cn.iocoder.common.framework.util.StringUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.SortingField;
import cn.iocoder.mall.search.biz.service.ProductSearchService;
import cn.iocoder.mall.search.rest.response.user.ProductPageResponse;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.iocoder.mall.search.rest.request.user.ProductConditionRequest;
import cn.iocoder.mall.search.rest.request.user.ProductSearchPageRequest;
import cn.iocoder.mall.search.rest.response.user.ProductConditionResponse;

import java.util.Collections;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
 * Created with IDEA
 *
 * @author : lhl
 * @version : 1.0
 * @Time : 19:26
 * @date : 2020/5/14
 */
@RestController
@RequestMapping(MallConstants.ROOT_PATH_ADMIN + "users/product")
@Api(tags = "商品查询 API")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UsersProductSearchController {


    private final ProductSearchService productSearchService;

    @GetMapping("/page") // TODO 芋艿，后面把 BO 改成 VO
    public CommonResult<ProductPageResponse> page(@RequestParam(value = "cid", required = false) Integer cid,
                                                  @RequestParam(value = "keyword", required = false) String keyword,
                                                  @RequestParam(value = "pageNo", required = false) Integer pageNo,
                                                  @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                  @RequestParam(value = "sortField", required = false) String sortField,
                                                  @RequestParam(value = "sortOrder", required = false) String sortOrder) {
        // 创建 ProductSearchPageDTO 对象
        ProductSearchPageRequest productSearchPageDTO = new ProductSearchPageRequest().setCid(cid).setKeyword(keyword)
                .setPageNo(pageNo).setPageSize(pageSize);
        if (StringUtil.hasText(sortField) && StringUtil.hasText(sortOrder)) {
            productSearchPageDTO.setSorts(Collections.singletonList(new SortingField(sortField, sortOrder)));
        }
        // 执行搜索
//        return success(productSearchService.getSearchPage(productSearchPageDTO));
        return success(null);
    }

    @GetMapping("/condition") // TODO 芋艿，后面把 BO 改成 VO
    public CommonResult<ProductConditionResponse> condition(@RequestParam(value = "keyword", required = false) String keyword) {
        // 创建 ProductConditionDTO 对象
        ProductConditionRequest productConditionDTO = new ProductConditionRequest().setKeyword(keyword)
                .setFields(Collections.singleton(ProductConditionRequest.FIELD_CATEGORY));
        // 执行搜索
//        return success(productSearchService.getSearchCondition(productConditionDTO));
        return success(null);
    }


}
