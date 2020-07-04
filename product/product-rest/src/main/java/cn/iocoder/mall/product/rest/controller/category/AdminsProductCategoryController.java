package cn.iocoder.mall.product.rest.controller.category;

import cn.iocoder.common.framework.enums.MallConstants;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.product.biz.bo.category.ProductCategoryBO;
import cn.iocoder.mall.product.biz.dto.category.ProductCategoryAddDTO;
import cn.iocoder.mall.product.biz.dto.category.ProductCategoryDeleteDTO;
import cn.iocoder.mall.product.biz.dto.category.ProductCategoryUpdateDTO;
import cn.iocoder.mall.product.biz.dto.category.ProductCategoryUpdateStatusDTO;
import cn.iocoder.mall.product.biz.enums.category.ProductCategoryNodeEnum;
import cn.iocoder.mall.product.biz.service.category.ProductCategoryService;
import cn.iocoder.mall.product.rest.convert.category.AdminsProductCategoryConvert;
import cn.iocoder.mall.product.rest.request.category.AdminsProductCategoryAddRequest;
import cn.iocoder.mall.product.rest.request.category.AdminsProductCategoryUpdateRequest;
import cn.iocoder.mall.product.rest.request.category.AdminsProductCategoryUpdateStatusRequest;
import cn.iocoder.mall.product.rest.response.category.AdminsProductCategoryAddResponse;
import cn.iocoder.mall.product.rest.response.category.AdminsProductCategoryTreeNodeResponse;
import cn.iocoder.mall.security.core.context.AdminSecurityContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
 * @Author: jiangweifan
 * @Date: 2020/5/6
 * @Description: 商品分类 - API
 */
@RestController
@RequestMapping(MallConstants.ROOT_PATH_ADMIN + "/category")
@Api(tags = "管理员 - 商品分类 API")
public class AdminsProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/tree")
    @ApiOperation("获取分类树结构")
    public CommonResult<List<AdminsProductCategoryTreeNodeResponse>> tree() {
        List<ProductCategoryBO> productCategories = productCategoryService.getAllProductCategory();
        // 创建 ProductCategoryTreeNodeVO Map
        Map<Integer, AdminsProductCategoryTreeNodeResponse> treeNodeMap = productCategories.stream().collect(Collectors.toMap(ProductCategoryBO::getId, AdminsProductCategoryConvert.INSTANCE::convertToTreeNodeResponse));
        // 处理父子关系
        treeNodeMap.values().stream()
                .filter(node -> !node.getPid().equals(ProductCategoryNodeEnum.ROOT.getId()))
                .forEach((childNode) -> {
                    // 获得父节点
                    AdminsProductCategoryTreeNodeResponse parentNode = treeNodeMap.get(childNode.getPid());
                    if (parentNode.getChildren() == null) { // 初始化 children 数组
                        parentNode.setChildren(new ArrayList<>());
                    }
                    // 将自己添加到父节点中
                    parentNode.getChildren().add(childNode);
                });
        // 获得到所有的根节点
        List<AdminsProductCategoryTreeNodeResponse> rootNodes = treeNodeMap.values().stream()
                .filter(node -> node.getPid().equals(ProductCategoryNodeEnum.ROOT.getId()))
                .sorted(Comparator.comparing(AdminsProductCategoryTreeNodeResponse::getSort))
                .collect(Collectors.toList());
        return success(rootNodes);
    }

    @PostMapping("/add")
    @ApiOperation(value = "创建商品分类")
    public CommonResult<AdminsProductCategoryAddResponse> add(AdminsProductCategoryAddRequest adminsProductCategoryAddRequest) {
        // 转换 ProductCategoryAddDTO 对象
        ProductCategoryAddDTO productCategoryAddDTO = AdminsProductCategoryConvert.INSTANCE.convertToAddDTO(AdminSecurityContextHolder.getContext().getAdminId(), adminsProductCategoryAddRequest);
        // 创建商品分类
        ProductCategoryBO addProductCategoryBO = productCategoryService.addProductCategory(productCategoryAddDTO);
        // 返回结果
        return success(AdminsProductCategoryConvert.INSTANCE.convertToAddResponse(addProductCategoryBO));
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新商品分类")
    public CommonResult<Boolean> update(AdminsProductCategoryUpdateRequest adminsProductCategoryUpdateRequest) {
        // 创建 ProductCategoryUpdateDTO 对象
        ProductCategoryUpdateDTO productCategoryUpdateDTO = AdminsProductCategoryConvert.INSTANCE.convertToUpdateDTO(AdminSecurityContextHolder.getContext().getAdminId(), adminsProductCategoryUpdateRequest);
        // 更新商品分类
        return success(productCategoryService.updateProductCategory(productCategoryUpdateDTO));
    }

    @PostMapping("/update_status")
    @ApiOperation(value = "更新商品分类状态")
    public CommonResult<Boolean> updateStatus(AdminsProductCategoryUpdateStatusRequest adminsProductCategoryUpdateStatusRequest) {
        // 创建 ProductCategoryUpdateStatusDTO 对象
        ProductCategoryUpdateStatusDTO productCategoryUpdateStatusDTO = AdminsProductCategoryConvert.INSTANCE.convertToUpdateStatusDTO(AdminSecurityContextHolder.getContext().getAdminId(),
                adminsProductCategoryUpdateStatusRequest);
        // 更新商品分类状态
        return success(productCategoryService.updateProductCategoryStatus(productCategoryUpdateStatusDTO));
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除商品分类")
    @ApiImplicitParam(name = "id", value = "商品分类编号", required = true, example = "1")
    public CommonResult<Boolean> delete(@RequestParam("id") Integer id) {
        // 创建 ProductCategoryDeleteDTO 对象
        ProductCategoryDeleteDTO productCategoryDeleteDTO = AdminsProductCategoryConvert.INSTANCE.convertToDeleteDTO(AdminSecurityContextHolder.getContext().getAdminId(), id);
        // 删除商品分类
        return success(productCategoryService.deleteProductCategory(productCategoryDeleteDTO));
    }

}
