package cn.iocoder.mall.productservice.convert.spu;

import cn.iocoder.common.framework.util.StringUtils;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.productservice.dal.mysql.dataobject.spu.ProductSpuDO;
import cn.iocoder.mall.productservice.rpc.attr.dto.ProductAttrKeyValueRespDTO;
import cn.iocoder.mall.productservice.rpc.spu.dto.*;
import cn.iocoder.mall.productservice.service.attr.bo.ProductAttrKeyValueBO;
import cn.iocoder.mall.productservice.service.category.bo.ProductCategoryBO;
import cn.iocoder.mall.productservice.service.sku.bo.ProductSkuBO;
import cn.iocoder.mall.productservice.service.sku.bo.ProductSkuCreateOrUpdateBO;
import cn.iocoder.mall.productservice.service.spu.bo.ProductSpuBO;
import cn.iocoder.mall.productservice.service.spu.bo.ProductSpuCreateBO;
import cn.iocoder.mall.productservice.service.spu.bo.ProductSpuPageBO;
import cn.iocoder.mall.productservice.service.spu.bo.ProductSpuUpdateBO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    ProductSpuUpdateBO convert(ProductSpuAndSkuUpdateReqDTO bean);

    ProductSpuRespDTO convert(ProductSpuBO bean);
    ProductSpuDetailRespDTO convert2(ProductSpuBO bean);

    List<ProductSpuRespDTO> convertList02(List<ProductSpuBO> list);

    ProductSpuPageBO convert(ProductSpuPageReqDTO bean);

    PageResult<ProductSpuRespDTO> convertPage(PageResult<ProductSpuBO> page);

    List<ProductSkuCreateOrUpdateBO> convert(List<ProductSpuAndSkuCreateReqDTO.Sku> list);
    List<ProductSkuCreateOrUpdateBO> convert02(List<ProductSpuAndSkuUpdateReqDTO.Sku> list);

    ProductSpuDetailRespDTO.Sku convert(ProductSkuBO bean);

    ProductAttrKeyValueRespDTO convert(ProductAttrKeyValueBO bean);

    default ProductSpuDetailRespDTO convert(ProductSpuBO spuBO, List<ProductSkuBO> skuBOs,
                                            List<ProductAttrKeyValueBO> attrBOs, ProductCategoryBO categoryBO) {
        // 创建并转换 ProductSpuDetailBO 对象
        ProductSpuDetailRespDTO spuDetailDTO = this.convert2(spuBO);
        // 创建 ProductAttrDetailBO 的映射。其中，KEY 为 ProductAttrDetailBO.attrValueId ，即规格值的编号
        Map<Integer, ProductAttrKeyValueBO> attrDetailBOMap = attrBOs.stream().collect(
                Collectors.toMap(ProductAttrKeyValueBO::getAttrValueId, attrBO -> attrBO));
        // 创建并转换 ProductSpuDetailBO 数组
        spuDetailDTO.setSkus(new ArrayList<>());
        skuBOs.forEach(skuBO -> {
            // 创建 ProductSpuDetailBO 对象
            ProductSpuDetailRespDTO.Sku skuDetail = convert(skuBO).setAttrs(new ArrayList<>());
            spuDetailDTO.getSkus().add(skuDetail);
            // 设置 ProductSpuDetailBO 的 attrs 规格属性
            skuBO.getAttrValueIds().forEach(attrValueId -> skuDetail.getAttrs().add(convert(attrDetailBOMap.get(attrValueId))));
        });
        // 设置分类名
        spuDetailDTO.setCategoryName(categoryBO.getName());
        // 返回
        return spuDetailDTO;
    }

    @Named("translatePicUrlsFromString")
    default List<String> translatePicUrlsFromList(String picUrls) {
        return StringUtils.split(picUrls, ",");
    }

    @Named("translatePicUrlsFromStringList")
    default String translatePicUrlsFromList(List<String> picUrls) {
        return StringUtils.join(picUrls, ",");
    }


}
