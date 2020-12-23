package cn.iocoder.mall.managementweb.manager.product;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.managementweb.controller.product.vo.category.ProductCategoryCreateReqVO;
import cn.iocoder.mall.managementweb.controller.product.vo.category.ProductCategoryTreeNodeRespVO;
import cn.iocoder.mall.managementweb.controller.product.vo.category.ProductCategoryUpdateReqVO;
import cn.iocoder.mall.managementweb.convert.product.ProductCategoryConvert;
import cn.iocoder.mall.productservice.enums.category.ProductCategoryIdEnum;
import cn.iocoder.mall.productservice.rpc.category.ProductCategoryRpc;
import cn.iocoder.mall.productservice.rpc.category.dto.ProductCategoryListQueryReqDTO;
import cn.iocoder.mall.productservice.rpc.category.dto.ProductCategoryRespDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
* 商品分类表 Manager
*/
@Service
@Slf4j
public class ProductCategoryManager {

    @Reference(version = "${dubbo.consumer.ProductCategoryRpc.version}")
    private ProductCategoryRpc productCategoryRpc;

    /**
    * 创建商品分类表
    *
    * @param createVO 创建商品分类表 VO
    * @return 商品分类表
    */
    public Integer createProductCategory(ProductCategoryCreateReqVO createVO) {
        CommonResult<Integer> createProductCategoryResult = productCategoryRpc.createProductCategory(ProductCategoryConvert.INSTANCE.convert(createVO));
        createProductCategoryResult.checkError();
        return createProductCategoryResult.getData();
    }

    /**
    * 更新商品分类表
    *
    * @param updateVO 更新商品分类表 VO
    */
    public void updateProductCategory(ProductCategoryUpdateReqVO updateVO) {
        CommonResult<Boolean> updateProductCategoryResult = productCategoryRpc.updateProductCategory(ProductCategoryConvert.INSTANCE.convert(updateVO));
        updateProductCategoryResult.checkError();
    }

    /**
    * 删除商品分类表
    *
    * @param productCategoryId 商品分类表编号
    */
    public void deleteProductCategory(Integer productCategoryId) {
        CommonResult<Boolean> deleteProductCategoryResult = productCategoryRpc.deleteProductCategory(productCategoryId);
        deleteProductCategoryResult.checkError();
    }

    /**
     * 获得商品分类树结构
     *
     * @return 商品分类树结构
     */
    public List<ProductCategoryTreeNodeRespVO> treeProductCategory() {
        // 获得商品分类全列表
        CommonResult<List<ProductCategoryRespDTO>> listProductCategories = productCategoryRpc.listProductCategories(new ProductCategoryListQueryReqDTO());
        listProductCategories.checkError();
        // 构建菜单树
        return buildProductCategoryTree(listProductCategories.getData());
    }

    /**
     * 构建商品分类树
     *
     * @param productCategories 商品分类列表
     * @return 商品分类树
     */
    private static List<ProductCategoryTreeNodeRespVO> buildProductCategoryTree(List<ProductCategoryRespDTO> productCategories) {
        // 排序，保证菜单的有序性
        productCategories.sort(Comparator.comparing(ProductCategoryRespDTO::getSort));
        // 构建菜单树
        // 使用 LinkedHashMap 的原因，是为了排序 。实际也可以用 Stream API ，就是太丑了。
        Map<Integer, ProductCategoryTreeNodeRespVO> treeNodeMap = new LinkedHashMap<>();
        productCategories.forEach(category -> treeNodeMap.put(category.getId(), ProductCategoryConvert.INSTANCE.convertTreeNode(category)));
        // 处理父子关系
        treeNodeMap.values().stream().filter(node -> !node.getPid().equals(ProductCategoryIdEnum.ROOT.getId())).forEach((childNode) -> {
            // 获得父节点
            ProductCategoryTreeNodeRespVO parentNode = treeNodeMap.get(childNode.getPid());
            if (parentNode == null) {
                log.error("[buildProductCategoryTree][category({}) 找不到父商品分类({})]", childNode.getId(), childNode.getPid());
                return;
            }
            // 将自己添加到父节点中
            if (parentNode.getChildren() == null) {
                parentNode.setChildren(new ArrayList<>());
            }
            parentNode.getChildren().add(childNode);
        });
        // 获得到所有的根节点
        return treeNodeMap.values().stream().filter(node -> node.getPid().equals(ProductCategoryIdEnum.ROOT.getId()))
                .collect(Collectors.toList());
    }

}
