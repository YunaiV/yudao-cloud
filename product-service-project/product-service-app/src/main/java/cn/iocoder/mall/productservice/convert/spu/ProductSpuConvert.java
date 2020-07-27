package cn.iocoder.mall.productservice.convert.spu;

import cn.iocoder.common.framework.util.StringUtils;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.productservice.dal.mysql.dataobject.spu.ProductSpuDO;
import cn.iocoder.mall.productservice.service.sku.bo.ProductSkuCreateOrUpdateBO;
import cn.iocoder.mall.productservice.rpc.spu.dto.ProductSpuAndSkuCreateReqDTO;
import cn.iocoder.mall.productservice.rpc.spu.dto.ProductSpuPageReqDTO;
import cn.iocoder.mall.productservice.rpc.spu.dto.ProductSpuRespDTO;
import cn.iocoder.mall.productservice.rpc.spu.dto.ProductSpuUpdateReqDTO;
import cn.iocoder.mall.productservice.service.spu.bo.ProductSpuBO;
import cn.iocoder.mall.productservice.service.spu.bo.ProductSpuCreateBO;
import cn.iocoder.mall.productservice.service.spu.bo.ProductSpuPageBO;
import cn.iocoder.mall.productservice.service.spu.bo.ProductSpuUpdateBO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductSpuConvert {

    ProductSpuConvert INSTANCE = Mappers.getMapper(ProductSpuConvert.class);

    @Mapping(source = "picUrls", target = "picUrls", qualifiedByName = "translatePicUrlsFromStringList")
    ProductSpuDO convert(ProductSpuCreateBO bean);

    @Mapping(source = "picUrls", target = "picUrls", qualifiedByName = "translatePicUrlsFromString")
    ProductSpuBO convert(ProductSpuDO bean);

    @Mapping(source = "picUrls", target = "picUrls", qualifiedByName = "translatePicUrlsFromStringList")
    ProductSpuDO convert(ProductSpuUpdateBO bean);

    List<ProductSpuBO> convertList(List<ProductSpuDO> list);

    @Mapping(source = "records", target = "list")
	PageResult<ProductSpuBO> convertPage(IPage<ProductSpuDO> page);

    ProductSpuCreateBO convert(ProductSpuAndSkuCreateReqDTO bean);

    ProductSpuUpdateBO convert(ProductSpuUpdateReqDTO bean);

    ProductSpuRespDTO convert(ProductSpuBO bean);

    List<ProductSpuRespDTO> convertList02(List<ProductSpuBO> list);

    ProductSpuPageBO convert(ProductSpuPageReqDTO bean);

    PageResult<ProductSpuRespDTO> convertPage(PageResult<ProductSpuBO> page);

    @Named("translatePicUrlsFromString")
    default List<String> translatePicUrlsFromList(String picUrls) {
        return StringUtils.split(picUrls, ",");
    }

    @Named("translatePicUrlsFromStringList")
    default String translatePicUrlsFromList(List<String> picUrls) {
        return StringUtils.join(picUrls, ",");
    }

    List<ProductSkuCreateOrUpdateBO> convert(List<ProductSpuAndSkuCreateReqDTO.Sku> list);

}
