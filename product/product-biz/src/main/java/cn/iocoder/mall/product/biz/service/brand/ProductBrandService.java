package cn.iocoder.mall.product.biz.service.brand;


import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.product.biz.bo.brand.ProductBrandBO;
import cn.iocoder.mall.product.biz.dto.brand.ProductBrandAddDTO;
import cn.iocoder.mall.product.biz.dto.brand.ProductBrandPageDTO;
import cn.iocoder.mall.product.biz.dto.brand.ProductBrandUpdateDTO;

public interface ProductBrandService {

    /**
     * 获取品牌分页数据
     *
     * @param productBrandPageDTO 翻页参数
     * @return
     */
    PageResult<ProductBrandBO> getProductBrandPage(ProductBrandPageDTO productBrandPageDTO);

    /**
     * 获取品牌明细
     *
     * @param id 主键
     * @return
     */
    ProductBrandBO getProductBrand(Integer id);

    /**
     * 添加品牌
     *
     * @param productBrandAddDTO 添加参数
     * @return
     */
    ProductBrandBO addProductBrand(Integer adminId, ProductBrandAddDTO productBrandAddDTO);

    /**
     * 更新品牌
     *
     * @param productBrandUpdateDTO 更新参数
     * @return
     */
    Boolean updateProductBrand(Integer adminId, ProductBrandUpdateDTO productBrandUpdateDTO);


}
