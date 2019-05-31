package cn.iocoder.mall.product.service;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.mall.product.api.ProductBrandService;
import cn.iocoder.mall.product.api.bo.ProductBrandBO;
import cn.iocoder.mall.product.api.bo.ProductBrangPageBO;
import cn.iocoder.mall.product.api.constant.ProductErrorCodeEnum;
import cn.iocoder.mall.product.api.dto.ProductBrandAddDTO;
import cn.iocoder.mall.product.api.dto.ProductBrandPageDTO;
import cn.iocoder.mall.product.api.dto.ProductBrandUpdateDTO;
import cn.iocoder.mall.product.convert.ProductBrandConvert;
import cn.iocoder.mall.product.dao.ProductBrandMapper;
import cn.iocoder.mall.product.dataobject.ProductBrandDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品规格 Service 实现类
 *
 * @see ProductBrandDO
 */
@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.ProductBrandService.version}")
public class ProductBrandServiceImpl implements ProductBrandService {

    @Autowired
    private ProductBrandMapper productBrandMapper;

    /**
     * 获取品牌分页数据
     * @param productBrandPageDTO 分页参数
     * @return
     */
    @Override
    public ProductBrangPageBO getProductBrandPage(ProductBrandPageDTO productBrandPageDTO) {
        ProductBrangPageBO productBrangPageBO = new ProductBrangPageBO();
        // 查询分页数据
        int offset = (productBrandPageDTO.getPageNo() - 1) * productBrandPageDTO.getPageSize();
        productBrangPageBO.setBrands(
                ProductBrandConvert.INSTANCE.convert(
                        productBrandMapper.selectListByParams(productBrandPageDTO.getName(),
                                productBrandPageDTO.getDescription(),
                                productBrandPageDTO.getStatus(),
                offset, productBrandPageDTO.getPageSize())));
        // 查询分页总数
        productBrangPageBO.setCount(productBrandMapper.selectListCountByParams(productBrandPageDTO.getName(),
                productBrandPageDTO.getDescription(),
                productBrandPageDTO.getStatus()));
        return productBrangPageBO;
    }

    /**
     * 获取品牌明细
     * @param id 主键
     * @return
     */
    @Override
    public ProductBrandBO getProductBrand(Integer id) {
        ProductBrandBO productBrandBO = new ProductBrandBO();
        productBrandBO = ProductBrandConvert.INSTANCE.convert(productBrandMapper.selectById(id));
        return productBrandBO;
    }

    /**
     * 添加品牌
     * @param adminId
     * @param productBrandAddDTO 添加参数
     * @return
     */
    @Override
    public ProductBrandBO addProductBrand(Integer adminId, ProductBrandAddDTO productBrandAddDTO) {
        // 校验品牌名不重复
        if (productBrandMapper.selectByName(productBrandAddDTO.getName()) != null) {
            throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_BRAND_EXIST.getCode());
        }
        ProductBrandDO productBrandDO = new ProductBrandDO();
        productBrandDO = ProductBrandConvert.INSTANCE.convert(productBrandAddDTO);
        productBrandMapper.insert(productBrandDO);
        return ProductBrandConvert.INSTANCE.convert(productBrandDO);
    }

    /**
     * 更新品牌
     * @param adminId
     * @param productBrandUpdateDTO 更新参数
     * @return
     */
    @Override
    public Boolean updateProductBrand(Integer adminId, ProductBrandUpdateDTO productBrandUpdateDTO) {
        ProductBrandDO productBrandDO = new ProductBrandDO();
        productBrandDO = ProductBrandConvert.INSTANCE.convert(productBrandUpdateDTO);
        productBrandMapper.update(productBrandDO);
        return true;
    }
}
