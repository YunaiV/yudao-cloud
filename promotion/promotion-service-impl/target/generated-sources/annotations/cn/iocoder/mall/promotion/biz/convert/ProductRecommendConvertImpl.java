package cn.iocoder.mall.promotion.biz.convert;

import cn.iocoder.mall.promotion.api.bo.ProductRecommendBO;
import cn.iocoder.mall.promotion.api.dto.ProductRecommendAddDTO;
import cn.iocoder.mall.promotion.api.dto.ProductRecommendUpdateDTO;
import cn.iocoder.mall.promotion.biz.dataobject.ProductRecommendDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-04-06T01:40:11+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class ProductRecommendConvertImpl implements ProductRecommendConvert {

    @Override
    public ProductRecommendBO convertToBO(ProductRecommendDO banner) {
        if ( banner == null ) {
            return null;
        }

        ProductRecommendBO productRecommendBO = new ProductRecommendBO();

        productRecommendBO.setId( banner.getId() );
        productRecommendBO.setType( banner.getType() );
        productRecommendBO.setProductSpuId( banner.getProductSpuId() );
        productRecommendBO.setSort( banner.getSort() );
        productRecommendBO.setStatus( banner.getStatus() );
        productRecommendBO.setMemo( banner.getMemo() );
        productRecommendBO.setCreateTime( banner.getCreateTime() );

        return productRecommendBO;
    }

    @Override
    public List<ProductRecommendBO> convertToBO(List<ProductRecommendDO> bannerList) {
        if ( bannerList == null ) {
            return null;
        }

        List<ProductRecommendBO> list = new ArrayList<ProductRecommendBO>( bannerList.size() );
        for ( ProductRecommendDO productRecommendDO : bannerList ) {
            list.add( convertToBO( productRecommendDO ) );
        }

        return list;
    }

    @Override
    public ProductRecommendDO convert(ProductRecommendAddDTO bannerAddDTO) {
        if ( bannerAddDTO == null ) {
            return null;
        }

        ProductRecommendDO productRecommendDO = new ProductRecommendDO();

        productRecommendDO.setType( bannerAddDTO.getType() );
        productRecommendDO.setProductSpuId( bannerAddDTO.getProductSpuId() );
        productRecommendDO.setSort( bannerAddDTO.getSort() );
        productRecommendDO.setMemo( bannerAddDTO.getMemo() );

        return productRecommendDO;
    }

    @Override
    public ProductRecommendDO convert(ProductRecommendUpdateDTO bannerUpdateDTO) {
        if ( bannerUpdateDTO == null ) {
            return null;
        }

        ProductRecommendDO productRecommendDO = new ProductRecommendDO();

        productRecommendDO.setId( bannerUpdateDTO.getId() );
        productRecommendDO.setType( bannerUpdateDTO.getType() );
        productRecommendDO.setProductSpuId( bannerUpdateDTO.getProductSpuId() );
        productRecommendDO.setSort( bannerUpdateDTO.getSort() );
        productRecommendDO.setMemo( bannerUpdateDTO.getMemo() );

        return productRecommendDO;
    }
}
