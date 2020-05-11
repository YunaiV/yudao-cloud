package cn.iocoder.mall.product.biz.dao.product;

import cn.iocoder.mall.product.biz.dataobject.product.ProductBrandDO;
import cn.iocoder.mall.product.biz.dto.brand.ProductBrandPageDTO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductBrandMapper extends BaseMapper<ProductBrandDO> {

    // TODO FROM 芋艿 to q2118cs：selectPageByParams
    default IPage<ProductBrandDO> selectListByParams(ProductBrandPageDTO productBrandPageDTO) {
        Page<ProductBrandDO> page = new Page<>(productBrandPageDTO.getPageNo(), productBrandPageDTO.getPageSize());
        LambdaQueryWrapper<ProductBrandDO> queryWrapper = Wrappers.<ProductBrandDO>query().lambda()
                .like(StringUtils.isNotBlank(productBrandPageDTO.getName()), ProductBrandDO::getName, productBrandPageDTO.getName())
                .like(StringUtils.isNotBlank(productBrandPageDTO.getDescription()), ProductBrandDO::getName, productBrandPageDTO.getDescription())
                .eq(null != productBrandPageDTO.getStatus(), ProductBrandDO::getName, productBrandPageDTO.getStatus())
                .eq(ProductBrandDO::getDeleted, false);
        return selectPage(page, queryWrapper);
    }
}
