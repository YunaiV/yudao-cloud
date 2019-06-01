package cn.iocoder.mall.product.convert;

import cn.iocoder.mall.product.api.bo.ProductAttrBO;
import cn.iocoder.mall.product.api.bo.ProductAttrDetailBO;
import cn.iocoder.mall.product.api.bo.ProductAttrSimpleBO;
import cn.iocoder.mall.product.api.bo.ProductAttrValueBO;
import cn.iocoder.mall.product.api.bo.ProductAttrValueDetailBO;
import cn.iocoder.mall.product.api.bo.ProductAttrValueSimpleBO;
import cn.iocoder.mall.product.api.dto.ProductAttrAddDTO;
import cn.iocoder.mall.product.api.dto.ProductAttrUpdateDTO;
import cn.iocoder.mall.product.api.dto.ProductAttrValueAddDTO;
import cn.iocoder.mall.product.api.dto.ProductAttrValueUpdateDTO;
import cn.iocoder.mall.product.dataobject.ProductAttrDO;
import cn.iocoder.mall.product.dataobject.ProductAttrValueDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-24T11:38:56+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class ProductAttrConvertImpl implements ProductAttrConvert {

    @Override
    public List<ProductAttrDetailBO> convert(List<ProductAttrDO> attrs) {
        if ( attrs == null ) {
            return null;
        }

        List<ProductAttrDetailBO> list = new ArrayList<ProductAttrDetailBO>( attrs.size() );
        for ( ProductAttrDO productAttrDO : attrs ) {
            list.add( productAttrDOToProductAttrDetailBO( productAttrDO ) );
        }

        return list;
    }

    @Override
    public ProductAttrValueDetailBO convert(ProductAttrValueDO value) {
        if ( value == null ) {
            return null;
        }

        ProductAttrValueDetailBO productAttrValueDetailBO = new ProductAttrValueDetailBO();

        productAttrValueDetailBO.setId( value.getId() );
        productAttrValueDetailBO.setName( value.getName() );
        productAttrValueDetailBO.setStatus( value.getStatus() );
        productAttrValueDetailBO.setCreateTime( value.getCreateTime() );

        return productAttrValueDetailBO;
    }

    @Override
    public List<ProductAttrValueDetailBO> convert2(List<ProductAttrValueDO> values) {
        if ( values == null ) {
            return null;
        }

        List<ProductAttrValueDetailBO> list = new ArrayList<ProductAttrValueDetailBO>( values.size() );
        for ( ProductAttrValueDO productAttrValueDO : values ) {
            list.add( convert( productAttrValueDO ) );
        }

        return list;
    }

    @Override
    public List<ProductAttrSimpleBO> convert3(List<ProductAttrDO> attrs) {
        if ( attrs == null ) {
            return null;
        }

        List<ProductAttrSimpleBO> list = new ArrayList<ProductAttrSimpleBO>( attrs.size() );
        for ( ProductAttrDO productAttrDO : attrs ) {
            list.add( productAttrDOToProductAttrSimpleBO( productAttrDO ) );
        }

        return list;
    }

    @Override
    public ProductAttrValueSimpleBO convert3(ProductAttrValueDO value) {
        if ( value == null ) {
            return null;
        }

        ProductAttrValueSimpleBO productAttrValueSimpleBO = new ProductAttrValueSimpleBO();

        productAttrValueSimpleBO.setId( value.getId() );
        productAttrValueSimpleBO.setName( value.getName() );

        return productAttrValueSimpleBO;
    }

    @Override
    public List<ProductAttrValueSimpleBO> convert4(List<ProductAttrValueDO> values) {
        if ( values == null ) {
            return null;
        }

        List<ProductAttrValueSimpleBO> list = new ArrayList<ProductAttrValueSimpleBO>( values.size() );
        for ( ProductAttrValueDO productAttrValueDO : values ) {
            list.add( convert3( productAttrValueDO ) );
        }

        return list;
    }

    @Override
    public ProductAttrDO convert(ProductAttrAddDTO productAttrAddDTO) {
        if ( productAttrAddDTO == null ) {
            return null;
        }

        ProductAttrDO productAttrDO = new ProductAttrDO();

        productAttrDO.setName( productAttrAddDTO.getName() );

        return productAttrDO;
    }

    @Override
    public ProductAttrDO convert(ProductAttrUpdateDTO productAttrUpdateDTO) {
        if ( productAttrUpdateDTO == null ) {
            return null;
        }

        ProductAttrDO productAttrDO = new ProductAttrDO();

        productAttrDO.setId( productAttrUpdateDTO.getId() );
        productAttrDO.setName( productAttrUpdateDTO.getName() );

        return productAttrDO;
    }

    @Override
    public ProductAttrValueDO convert(ProductAttrValueAddDTO productAttrValueAddDTO) {
        if ( productAttrValueAddDTO == null ) {
            return null;
        }

        ProductAttrValueDO productAttrValueDO = new ProductAttrValueDO();

        productAttrValueDO.setAttrId( productAttrValueAddDTO.getAttrId() );
        productAttrValueDO.setName( productAttrValueAddDTO.getName() );

        return productAttrValueDO;
    }

    @Override
    public ProductAttrValueDO convert(ProductAttrValueUpdateDTO productAttrValueUpdateDTO) {
        if ( productAttrValueUpdateDTO == null ) {
            return null;
        }

        ProductAttrValueDO productAttrValueDO = new ProductAttrValueDO();

        productAttrValueDO.setId( productAttrValueUpdateDTO.getId() );
        productAttrValueDO.setName( productAttrValueUpdateDTO.getName() );

        return productAttrValueDO;
    }

    @Override
    public ProductAttrBO convert(ProductAttrDO productAttrDO) {
        if ( productAttrDO == null ) {
            return null;
        }

        ProductAttrBO productAttrBO = new ProductAttrBO();

        productAttrBO.setId( productAttrDO.getId() );
        productAttrBO.setName( productAttrDO.getName() );
        productAttrBO.setStatus( productAttrDO.getStatus() );
        productAttrBO.setCreateTime( productAttrDO.getCreateTime() );

        return productAttrBO;
    }

    @Override
    public ProductAttrValueBO convert2(ProductAttrValueDO productAttrValueDO) {
        if ( productAttrValueDO == null ) {
            return null;
        }

        ProductAttrValueBO productAttrValueBO = new ProductAttrValueBO();

        productAttrValueBO.setId( productAttrValueDO.getId() );
        productAttrValueBO.setAttrId( productAttrValueDO.getAttrId() );
        productAttrValueBO.setName( productAttrValueDO.getName() );
        productAttrValueBO.setStatus( productAttrValueDO.getStatus() );
        productAttrValueBO.setCreateTime( productAttrValueDO.getCreateTime() );

        return productAttrValueBO;
    }

    protected ProductAttrDetailBO productAttrDOToProductAttrDetailBO(ProductAttrDO productAttrDO) {
        if ( productAttrDO == null ) {
            return null;
        }

        ProductAttrDetailBO productAttrDetailBO = new ProductAttrDetailBO();

        productAttrDetailBO.setId( productAttrDO.getId() );
        productAttrDetailBO.setName( productAttrDO.getName() );
        productAttrDetailBO.setStatus( productAttrDO.getStatus() );
        productAttrDetailBO.setCreateTime( productAttrDO.getCreateTime() );

        return productAttrDetailBO;
    }

    protected ProductAttrSimpleBO productAttrDOToProductAttrSimpleBO(ProductAttrDO productAttrDO) {
        if ( productAttrDO == null ) {
            return null;
        }

        ProductAttrSimpleBO productAttrSimpleBO = new ProductAttrSimpleBO();

        productAttrSimpleBO.setId( productAttrDO.getId() );
        productAttrSimpleBO.setName( productAttrDO.getName() );

        return productAttrSimpleBO;
    }
}
