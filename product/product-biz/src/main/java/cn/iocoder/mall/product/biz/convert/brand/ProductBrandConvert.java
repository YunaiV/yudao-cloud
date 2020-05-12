package cn.iocoder.mall.product.biz.convert.brand;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.product.biz.bo.brand.ProductBrandBO;
import cn.iocoder.mall.product.biz.dataobject.brand.ProductBrandDO;
import cn.iocoder.mall.product.biz.dto.brand.ProductBrandAddDTO;
import cn.iocoder.mall.product.biz.dto.brand.ProductBrandUpdateDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductBrandConvert {

    ProductBrandConvert INSTANCE = Mappers.getMapper(ProductBrandConvert.class);

    @Mapping(source = "records", target = "list")
    PageResult<ProductBrandBO> convertPage(IPage<ProductBrandDO> bean);

    @Mappings({})
    List<ProductBrandBO> convert(List<ProductBrandDO> brands);

    @Mappings({})
    ProductBrandBO convert(ProductBrandDO brand);

    @Mappings({})
    ProductBrandDO convert(ProductBrandUpdateDTO brand);

    @Mappings({})
    ProductBrandDO convert(ProductBrandAddDTO brand);
}