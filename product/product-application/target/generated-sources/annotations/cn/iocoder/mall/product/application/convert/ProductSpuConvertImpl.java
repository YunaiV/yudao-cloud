package cn.iocoder.mall.product.application.convert;

import cn.iocoder.mall.product.api.bo.ProductAttrAndValuePairBO;
import cn.iocoder.mall.product.api.bo.ProductSpuBO;
import cn.iocoder.mall.product.api.bo.ProductSpuDetailBO;
import cn.iocoder.mall.product.api.bo.ProductSpuDetailBO.Sku;
import cn.iocoder.mall.product.api.bo.ProductSpuPageBO;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductAttrAndValuePairVO;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductSkuDetailVO;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductSpuDetailVO;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductSpuPageVO;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductSpuVO;
import cn.iocoder.mall.product.application.vo.users.UsersProductAttrAndValuePairVO;
import cn.iocoder.mall.product.application.vo.users.UsersProductSkuDetailVO;
import cn.iocoder.mall.product.application.vo.users.UsersProductSpuDetailVO;
import cn.iocoder.mall.product.application.vo.users.UsersProductSpuPageVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-24T11:47:09+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class ProductSpuConvertImpl implements ProductSpuConvert {

    @Override
    public AdminsProductSpuDetailVO convert(ProductSpuDetailBO productSpuDetailBO) {
        if ( productSpuDetailBO == null ) {
            return null;
        }

        AdminsProductSpuDetailVO adminsProductSpuDetailVO = new AdminsProductSpuDetailVO();

        adminsProductSpuDetailVO.setId( productSpuDetailBO.getId() );
        adminsProductSpuDetailVO.setName( productSpuDetailBO.getName() );
        adminsProductSpuDetailVO.setSellPoint( productSpuDetailBO.getSellPoint() );
        adminsProductSpuDetailVO.setDescription( productSpuDetailBO.getDescription() );
        adminsProductSpuDetailVO.setCid( productSpuDetailBO.getCid() );
        List<String> list = productSpuDetailBO.getPicUrls();
        if ( list != null ) {
            adminsProductSpuDetailVO.setPicUrls( new ArrayList<String>( list ) );
        }
        adminsProductSpuDetailVO.setVisible( productSpuDetailBO.getVisible() );
        adminsProductSpuDetailVO.setSort( productSpuDetailBO.getSort() );
        adminsProductSpuDetailVO.setSkus( skuListToAdminsProductSkuDetailVOList( productSpuDetailBO.getSkus() ) );

        return adminsProductSpuDetailVO;
    }

    @Override
    public AdminsProductSpuPageVO convert2(ProductSpuPageBO result) {
        if ( result == null ) {
            return null;
        }

        AdminsProductSpuPageVO adminsProductSpuPageVO = new AdminsProductSpuPageVO();

        adminsProductSpuPageVO.setList( convert3( result.getList() ) );
        adminsProductSpuPageVO.setTotal( result.getTotal() );

        return adminsProductSpuPageVO;
    }

    @Override
    public List<AdminsProductSpuVO> convert3(List<ProductSpuBO> result) {
        if ( result == null ) {
            return null;
        }

        List<AdminsProductSpuVO> list = new ArrayList<AdminsProductSpuVO>( result.size() );
        for ( ProductSpuBO productSpuBO : result ) {
            list.add( productSpuBOToAdminsProductSpuVO( productSpuBO ) );
        }

        return list;
    }

    @Override
    public UsersProductSpuPageVO convert3(ProductSpuPageBO result) {
        if ( result == null ) {
            return null;
        }

        UsersProductSpuPageVO usersProductSpuPageVO = new UsersProductSpuPageVO();

        return usersProductSpuPageVO;
    }

    @Override
    public UsersProductSpuDetailVO convert4(ProductSpuDetailBO result) {
        if ( result == null ) {
            return null;
        }

        UsersProductSpuDetailVO usersProductSpuDetailVO = new UsersProductSpuDetailVO();

        usersProductSpuDetailVO.setId( result.getId() );
        usersProductSpuDetailVO.setName( result.getName() );
        usersProductSpuDetailVO.setSellPoint( result.getSellPoint() );
        usersProductSpuDetailVO.setDescription( result.getDescription() );
        usersProductSpuDetailVO.setCid( result.getCid() );
        List<String> list = result.getPicUrls();
        if ( list != null ) {
            usersProductSpuDetailVO.setPicUrls( new ArrayList<String>( list ) );
        }
        usersProductSpuDetailVO.setSkus( skuListToUsersProductSkuDetailVOList( result.getSkus() ) );

        return usersProductSpuDetailVO;
    }

    protected AdminsProductAttrAndValuePairVO productAttrAndValuePairBOToAdminsProductAttrAndValuePairVO(ProductAttrAndValuePairBO productAttrAndValuePairBO) {
        if ( productAttrAndValuePairBO == null ) {
            return null;
        }

        AdminsProductAttrAndValuePairVO adminsProductAttrAndValuePairVO = new AdminsProductAttrAndValuePairVO();

        adminsProductAttrAndValuePairVO.setAttrId( productAttrAndValuePairBO.getAttrId() );
        adminsProductAttrAndValuePairVO.setAttrName( productAttrAndValuePairBO.getAttrName() );
        adminsProductAttrAndValuePairVO.setAttrValueId( productAttrAndValuePairBO.getAttrValueId() );
        adminsProductAttrAndValuePairVO.setAttrValueName( productAttrAndValuePairBO.getAttrValueName() );

        return adminsProductAttrAndValuePairVO;
    }

    protected List<AdminsProductAttrAndValuePairVO> productAttrAndValuePairBOListToAdminsProductAttrAndValuePairVOList(List<ProductAttrAndValuePairBO> list) {
        if ( list == null ) {
            return null;
        }

        List<AdminsProductAttrAndValuePairVO> list1 = new ArrayList<AdminsProductAttrAndValuePairVO>( list.size() );
        for ( ProductAttrAndValuePairBO productAttrAndValuePairBO : list ) {
            list1.add( productAttrAndValuePairBOToAdminsProductAttrAndValuePairVO( productAttrAndValuePairBO ) );
        }

        return list1;
    }

    protected AdminsProductSkuDetailVO skuToAdminsProductSkuDetailVO(Sku sku) {
        if ( sku == null ) {
            return null;
        }

        AdminsProductSkuDetailVO adminsProductSkuDetailVO = new AdminsProductSkuDetailVO();

        adminsProductSkuDetailVO.setId( sku.getId() );
        adminsProductSkuDetailVO.setSpuId( sku.getSpuId() );
        adminsProductSkuDetailVO.setPicURL( sku.getPicURL() );
        adminsProductSkuDetailVO.setAttrs( productAttrAndValuePairBOListToAdminsProductAttrAndValuePairVOList( sku.getAttrs() ) );
        adminsProductSkuDetailVO.setPrice( sku.getPrice() );
        adminsProductSkuDetailVO.setQuantity( sku.getQuantity() );

        return adminsProductSkuDetailVO;
    }

    protected List<AdminsProductSkuDetailVO> skuListToAdminsProductSkuDetailVOList(List<Sku> list) {
        if ( list == null ) {
            return null;
        }

        List<AdminsProductSkuDetailVO> list1 = new ArrayList<AdminsProductSkuDetailVO>( list.size() );
        for ( Sku sku : list ) {
            list1.add( skuToAdminsProductSkuDetailVO( sku ) );
        }

        return list1;
    }

    protected AdminsProductSpuVO productSpuBOToAdminsProductSpuVO(ProductSpuBO productSpuBO) {
        if ( productSpuBO == null ) {
            return null;
        }

        AdminsProductSpuVO adminsProductSpuVO = new AdminsProductSpuVO();

        adminsProductSpuVO.setId( productSpuBO.getId() );
        adminsProductSpuVO.setName( productSpuBO.getName() );
        adminsProductSpuVO.setSellPoint( productSpuBO.getSellPoint() );
        adminsProductSpuVO.setDescription( productSpuBO.getDescription() );
        adminsProductSpuVO.setCid( productSpuBO.getCid() );
        List<String> list = productSpuBO.getPicUrls();
        if ( list != null ) {
            adminsProductSpuVO.setPicUrls( new ArrayList<String>( list ) );
        }
        adminsProductSpuVO.setQuantity( productSpuBO.getQuantity() );
        adminsProductSpuVO.setVisible( productSpuBO.getVisible() );
        adminsProductSpuVO.setSort( productSpuBO.getSort() );

        return adminsProductSpuVO;
    }

    protected UsersProductAttrAndValuePairVO productAttrAndValuePairBOToUsersProductAttrAndValuePairVO(ProductAttrAndValuePairBO productAttrAndValuePairBO) {
        if ( productAttrAndValuePairBO == null ) {
            return null;
        }

        UsersProductAttrAndValuePairVO usersProductAttrAndValuePairVO = new UsersProductAttrAndValuePairVO();

        usersProductAttrAndValuePairVO.setAttrId( productAttrAndValuePairBO.getAttrId() );
        usersProductAttrAndValuePairVO.setAttrName( productAttrAndValuePairBO.getAttrName() );
        usersProductAttrAndValuePairVO.setAttrValueId( productAttrAndValuePairBO.getAttrValueId() );
        usersProductAttrAndValuePairVO.setAttrValueName( productAttrAndValuePairBO.getAttrValueName() );

        return usersProductAttrAndValuePairVO;
    }

    protected List<UsersProductAttrAndValuePairVO> productAttrAndValuePairBOListToUsersProductAttrAndValuePairVOList(List<ProductAttrAndValuePairBO> list) {
        if ( list == null ) {
            return null;
        }

        List<UsersProductAttrAndValuePairVO> list1 = new ArrayList<UsersProductAttrAndValuePairVO>( list.size() );
        for ( ProductAttrAndValuePairBO productAttrAndValuePairBO : list ) {
            list1.add( productAttrAndValuePairBOToUsersProductAttrAndValuePairVO( productAttrAndValuePairBO ) );
        }

        return list1;
    }

    protected UsersProductSkuDetailVO skuToUsersProductSkuDetailVO(Sku sku) {
        if ( sku == null ) {
            return null;
        }

        UsersProductSkuDetailVO usersProductSkuDetailVO = new UsersProductSkuDetailVO();

        usersProductSkuDetailVO.setId( sku.getId() );
        usersProductSkuDetailVO.setSpuId( sku.getSpuId() );
        usersProductSkuDetailVO.setPicURL( sku.getPicURL() );
        usersProductSkuDetailVO.setAttrs( productAttrAndValuePairBOListToUsersProductAttrAndValuePairVOList( sku.getAttrs() ) );
        usersProductSkuDetailVO.setPrice( sku.getPrice() );
        usersProductSkuDetailVO.setQuantity( sku.getQuantity() );

        return usersProductSkuDetailVO;
    }

    protected List<UsersProductSkuDetailVO> skuListToUsersProductSkuDetailVOList(List<Sku> list) {
        if ( list == null ) {
            return null;
        }

        List<UsersProductSkuDetailVO> list1 = new ArrayList<UsersProductSkuDetailVO>( list.size() );
        for ( Sku sku : list ) {
            list1.add( skuToUsersProductSkuDetailVO( sku ) );
        }

        return list1;
    }
}
