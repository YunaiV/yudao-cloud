package cn.iocoder.mall.productservice.service.brand;

import cn.iocoder.common.framework.exception.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.productservice.convert.brand.ProductBrandConvert;
import cn.iocoder.mall.productservice.dal.mysql.dataobject.brand.ProductBrandDO;
import cn.iocoder.mall.productservice.dal.mysql.mapper.brand.ProductBrandMapper;
import cn.iocoder.mall.productservice.service.brand.bo.ProductBrandBO;
import cn.iocoder.mall.productservice.service.brand.bo.ProductBrandCreateBO;
import cn.iocoder.mall.productservice.service.brand.bo.ProductBrandPageBO;
import cn.iocoder.mall.productservice.service.brand.bo.ProductBrandUpdateBO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

import static cn.iocoder.mall.productservice.enums.ProductErrorCodeConstants.PRODUCT_BRAND_NAME_EXIST;
import static cn.iocoder.mall.productservice.enums.ProductErrorCodeConstants.PRODUCT_BRAND_NOT_FOUND;

/**
* 商品品牌 Service
*/
@Service
@Validated
public class ProductBrandService {

    @Autowired
    private ProductBrandMapper productBrandMapper;

    /**
    * 创建商品品牌
    *
    * @param createBO 创建商品品牌 BO
    * @return 商品品牌
    */
    public ProductBrandBO createProductBrand(@Valid ProductBrandCreateBO createBO) {
        // 校验商品品牌的名字是否已经使用
        if (productBrandMapper.selectByName(createBO.getName()) != null) {
            throw ServiceExceptionUtil.exception(PRODUCT_BRAND_NAME_EXIST);
        }
        // 插入到数据库
        ProductBrandDO productBrandDO = ProductBrandConvert.INSTANCE.convert(createBO);
        productBrandMapper.insert(productBrandDO);
        // 返回
        return ProductBrandConvert.INSTANCE.convert(productBrandDO);
    }

    /**
    * 更新商品品牌
    *
    * @param updateBO 更新商品品牌 BO
    */
    public void updateProductBrand(@Valid ProductBrandUpdateBO updateBO) {
        // 校验更新的商品品牌是否存在
        if (productBrandMapper.selectById(updateBO.getId()) == null) {
            throw ServiceExceptionUtil.exception(PRODUCT_BRAND_NOT_FOUND);
        }
        // 校验商品品牌的名字是否已经使用
        ProductBrandDO productBrandDOByName = productBrandMapper.selectByName(updateBO.getName());
        if (productBrandDOByName != null && !updateBO.getId().equals(productBrandDOByName.getId())) {
            throw ServiceExceptionUtil.exception(PRODUCT_BRAND_NAME_EXIST);
        }
        // 更新到数据库
        ProductBrandDO updateObject = ProductBrandConvert.INSTANCE.convert(updateBO);
        productBrandMapper.updateById(updateObject);
    }

    /**
    * 删除商品品牌
    *
    * @param productBrandId 商品品牌编号
    */
    public void deleteProductBrand(Integer productBrandId) {
        // 校验删除的商品品牌是否存在
        if (productBrandMapper.selectById(productBrandId) == null) {
            throw ServiceExceptionUtil.exception(PRODUCT_BRAND_NOT_FOUND);
        }
        // TODO 功能点：需要品牌下没有分类
        // 标记删除
        productBrandMapper.deleteById(productBrandId);
    }

    /**
    * 获得商品品牌
    *
    * @param productBrandId 商品品牌编号
    * @return 商品品牌
    */
    public ProductBrandBO getProductBrand(Integer productBrandId) {
        ProductBrandDO productBrandDO = productBrandMapper.selectById(productBrandId);
        return ProductBrandConvert.INSTANCE.convert(productBrandDO);
    }

    /**
    * 获得商品品牌列表
    *
    * @param productBrandIds 商品品牌编号列表
    * @return 商品品牌列表
    */
    public List<ProductBrandBO> listProductBrands(List<Integer> productBrandIds) {
        List<ProductBrandDO> productBrandDOs = productBrandMapper.selectBatchIds(productBrandIds);
        return ProductBrandConvert.INSTANCE.convertList(productBrandDOs);
    }

    /**
    * 获得商品品牌分页
    *
    * @param pageBO 商品品牌分页查询
    * @return 商品品牌分页结果
    */
    public PageResult<ProductBrandBO> pageProductBrand(ProductBrandPageBO pageBO) {
        IPage<ProductBrandDO> productBrandDOPage = productBrandMapper.selectPage(pageBO);
        return ProductBrandConvert.INSTANCE.convertPage(productBrandDOPage);
    }

}
