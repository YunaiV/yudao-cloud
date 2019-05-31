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
    date = "2019-05-24T11:38:52+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class ProductRecommendConvertImpl implements ProductRecommendConvert {

    @Override
    public ProductRecommendBO convertToBO(ProductRecommendDO recommend) {
        if ( recommend == null ) {
            return null;
        }

        ProductRecommendBO productRecommendBO = new ProductRecommendBO();

        productRecommendBO.setId( recommend.getId() );
        productRecommendBO.setType( recommend.getType() );
        productRecommendBO.setProductSpuId( recommend.getProductSpuId() );
        productRecommendBO.setSort( recommend.getSort() );
        productRecommendBO.setStatus( recommend.getStatus() );
        productRecommendBO.setMemo( recommend.getMemo() );
        productRecommendBO.setCreateTime( recommend.getCreateTime() );

        return productRecommendBO;
    }

    @Override
    public List<ProductRecommendBO> convertToBO(List<ProductRecommendDO> recommendList) {
        if ( recommendList == null ) {
            return null;
        }

        List<ProductRecommendBO> list = new ArrayList<ProductRecommendBO>( recommendList.size() );
        for ( ProductRecommendDO productRecommendDO : recommendList ) {
            list.add( convertToBO( productRecommendDO ) );
        }

        return list;
    }

    @Override
    public ProductRecommendDO convert(ProductRecommendAddDTO recommendAddDTO) {
        if ( recommendAddDTO == null ) {
            return null;
        }

        ProductRecommendDO productRecommendDO = new ProductRecommendDO();

        productRecommendDO.setType( recommendAddDTO.getType() );
        productRecommendDO.setProductSpuId( recommendAddDTO.getProductSpuId() );
        productRecommendDO.setSort( recommendAddDTO.getSort() );
        productRecommendDO.setMemo( recommendAddDTO.getMemo() );

        return productRecommendDO;
    }

    @Override
    public ProductRecommendDO convert(ProductRecommendUpdateDTO recommendUpdateDTO) {
        if ( recommendUpdateDTO == null ) {
            return null;
        }

        ProductRecommendDO productRecommendDO = new ProductRecommendDO();

        productRecommendDO.setId( recommendUpdateDTO.getId() );
        productRecommendDO.setType( recommendUpdateDTO.getType() );
        productRecommendDO.setProductSpuId( recommendUpdateDTO.getProductSpuId() );
        productRecommendDO.setSort( recommendUpdateDTO.getSort() );
        productRecommendDO.setMemo( recommendUpdateDTO.getMemo() );

        return productRecommendDO;
    }
}
