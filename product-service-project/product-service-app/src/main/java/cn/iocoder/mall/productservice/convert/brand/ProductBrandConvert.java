package cn.iocoder.mall.productservice.convert.brand;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.productservice.dal.mysql.dataobject.brand.ProductBrandDO;
import cn.iocoder.mall.productservice.rpc.brand.dto.ProductBrandCreateReqDTO;
import cn.iocoder.mall.productservice.rpc.brand.dto.ProductBrandPageReqDTO;
import cn.iocoder.mall.productservice.rpc.brand.dto.ProductBrandRespDTO;
import cn.iocoder.mall.productservice.rpc.brand.dto.ProductBrandUpdateReqDTO;
import cn.iocoder.mall.productservice.service.brand.bo.ProductBrandBO;
import cn.iocoder.mall.productservice.service.brand.bo.ProductBrandCreateBO;
import cn.iocoder.mall.productservice.service.brand.bo.ProductBrandPageBO;
import cn.iocoder.mall.productservice.service.brand.bo.ProductBrandUpdateBO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductBrandConvert {

    ProductBrandConvert INSTANCE = Mappers.getMapper(ProductBrandConvert.class);

    ProductBrandDO convert(ProductBrandCreateBO bean);

    ProductBrandBO convert(ProductBrandDO bean);

    ProductBrandDO convert(ProductBrandUpdateBO bean);

    List<ProductBrandBO> convertList(List<ProductBrandDO> list);

    @Mapping(source = "records", target = "list")
    PageResult<ProductBrandBO> convertPage(IPage<ProductBrandDO> page);

    ProductBrandCreateBO convert(ProductBrandCreateReqDTO bean);

    ProductBrandUpdateBO convert(ProductBrandUpdateReqDTO bean);

    ProductBrandRespDTO convert(ProductBrandBO bean);

    List<ProductBrandRespDTO> convertList02(List<ProductBrandBO> list);

    ProductBrandPageBO convert(ProductBrandPageReqDTO bean);

    PageResult<ProductBrandRespDTO> convertPage(PageResult<ProductBrandBO> page);

}
