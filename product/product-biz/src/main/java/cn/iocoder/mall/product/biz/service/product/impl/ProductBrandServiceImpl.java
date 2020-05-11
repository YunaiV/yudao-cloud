package cn.iocoder.mall.product.biz.service.product.impl;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.mybatis.enums.DeletedStatusEnum;
import cn.iocoder.mall.product.biz.bo.brand.ProductBrandBO;
import cn.iocoder.mall.product.biz.convert.brand.ProductBrandConvert;
import cn.iocoder.mall.product.biz.dao.product.ProductBrandMapper;
import cn.iocoder.mall.product.biz.dataobject.product.ProductBrandDO;
import cn.iocoder.mall.product.biz.dto.brand.ProductBrandAddDTO;
import cn.iocoder.mall.product.biz.dto.brand.ProductBrandPageDTO;
import cn.iocoder.mall.product.biz.dto.brand.ProductBrandUpdateDTO;
import cn.iocoder.mall.product.biz.enums.ProductErrorCodeEnum;
import cn.iocoder.mall.product.biz.service.product.ProductBrandService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 商品规格 Service 实现类
 *
 * @see ProductBrandDO
 */
@Service
public class ProductBrandServiceImpl implements ProductBrandService {

    @Autowired
    private ProductBrandMapper productBrandMapper;

    @Override
    public PageResult<ProductBrandBO> getProductBrandPage(ProductBrandPageDTO productBrandPageDTO) {
        IPage<ProductBrandDO> brandPage = productBrandMapper.selectListByParams(productBrandPageDTO);
        return ProductBrandConvert.INSTANCE.convertPage(brandPage);
    }

    @Override
    public ProductBrandBO getProductBrand(Integer brandId) {
        return ProductBrandConvert.INSTANCE.convert(productBrandMapper.selectById(brandId));
    }

    @Override
    public ProductBrandBO addProductBrand(Integer adminId, ProductBrandAddDTO productBrandAddDTO) {
        // 校验品牌名不重复
        int count = productBrandMapper.selectCount(Wrappers.<ProductBrandDO>query().lambda()
                .eq(ProductBrandDO::getName, productBrandAddDTO.getName())
                .eq(ProductBrandDO::getDeleted, false));
        if (count > 0) {
            throw ServiceExceptionUtil.exception(ProductErrorCodeEnum.PRODUCT_BRAND_EXIST.getCode());
        }
        ProductBrandDO productBrandDO = ProductBrandConvert.INSTANCE.convert(productBrandAddDTO);
        productBrandDO.setCreateTime(new Date());
        productBrandDO.setDeleted(DeletedStatusEnum.DELETED_NO.getValue());
        productBrandMapper.insert(productBrandDO);
        return ProductBrandConvert.INSTANCE.convert(productBrandDO);
    }

    @Override
    public Boolean updateProductBrand(Integer adminId, ProductBrandUpdateDTO productBrandUpdateDTO) {
        ProductBrandDO productBrandDO = ProductBrandConvert.INSTANCE.convert(productBrandUpdateDTO);
        productBrandDO.setUpdateTime(new Date());
        productBrandMapper.updateById(productBrandDO);
        return true;
    }
}
