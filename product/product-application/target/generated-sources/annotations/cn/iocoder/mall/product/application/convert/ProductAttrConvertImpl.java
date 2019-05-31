package cn.iocoder.mall.product.application.convert;

import cn.iocoder.mall.product.api.bo.ProductAttrBO;
import cn.iocoder.mall.product.api.bo.ProductAttrDetailBO;
import cn.iocoder.mall.product.api.bo.ProductAttrPageBO;
import cn.iocoder.mall.product.api.bo.ProductAttrSimpleBO;
import cn.iocoder.mall.product.api.bo.ProductAttrValueBO;
import cn.iocoder.mall.product.api.bo.ProductAttrValueDetailBO;
import cn.iocoder.mall.product.api.bo.ProductAttrValueSimpleBO;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductAttrDetailVO;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductAttrPageVO;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductAttrSimpleVO;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductAttrVO;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductAttrValueDetailVO;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductAttrValueSimpleVO;
import cn.iocoder.mall.product.application.vo.admins.AdminsProductAttrValueVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-24T11:47:10+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class ProductAttrConvertImpl implements ProductAttrConvert {

    @Override
    public AdminsProductAttrPageVO convert2(ProductAttrPageBO result) {
        if ( result == null ) {
            return null;
        }

        AdminsProductAttrPageVO adminsProductAttrPageVO = new AdminsProductAttrPageVO();

        adminsProductAttrPageVO.setAttrs( productAttrDetailBOListToAdminsProductAttrDetailVOList( result.getAttrs() ) );
        adminsProductAttrPageVO.setCount( result.getCount() );

        return adminsProductAttrPageVO;
    }

    @Override
    public List<AdminsProductAttrSimpleVO> convert(List<ProductAttrSimpleBO> result) {
        if ( result == null ) {
            return null;
        }

        List<AdminsProductAttrSimpleVO> list = new ArrayList<AdminsProductAttrSimpleVO>( result.size() );
        for ( ProductAttrSimpleBO productAttrSimpleBO : result ) {
            list.add( productAttrSimpleBOToAdminsProductAttrSimpleVO( productAttrSimpleBO ) );
        }

        return list;
    }

    @Override
    public AdminsProductAttrVO convert3(ProductAttrBO productAttrBO) {
        if ( productAttrBO == null ) {
            return null;
        }

        AdminsProductAttrVO adminsProductAttrVO = new AdminsProductAttrVO();

        adminsProductAttrVO.setId( productAttrBO.getId() );
        adminsProductAttrVO.setName( productAttrBO.getName() );
        adminsProductAttrVO.setStatus( productAttrBO.getStatus() );
        adminsProductAttrVO.setCreateTime( productAttrBO.getCreateTime() );

        return adminsProductAttrVO;
    }

    @Override
    public AdminsProductAttrValueVO convert4(ProductAttrValueBO productAttrValueBO) {
        if ( productAttrValueBO == null ) {
            return null;
        }

        AdminsProductAttrValueVO adminsProductAttrValueVO = new AdminsProductAttrValueVO();

        adminsProductAttrValueVO.setId( productAttrValueBO.getId() );
        adminsProductAttrValueVO.setAttrId( productAttrValueBO.getAttrId() );
        adminsProductAttrValueVO.setName( productAttrValueBO.getName() );
        adminsProductAttrValueVO.setStatus( productAttrValueBO.getStatus() );
        adminsProductAttrValueVO.setCreateTime( productAttrValueBO.getCreateTime() );

        return adminsProductAttrValueVO;
    }

    protected AdminsProductAttrValueDetailVO productAttrValueDetailBOToAdminsProductAttrValueDetailVO(ProductAttrValueDetailBO productAttrValueDetailBO) {
        if ( productAttrValueDetailBO == null ) {
            return null;
        }

        AdminsProductAttrValueDetailVO adminsProductAttrValueDetailVO = new AdminsProductAttrValueDetailVO();

        adminsProductAttrValueDetailVO.setId( productAttrValueDetailBO.getId() );
        adminsProductAttrValueDetailVO.setName( productAttrValueDetailBO.getName() );
        adminsProductAttrValueDetailVO.setStatus( productAttrValueDetailBO.getStatus() );
        adminsProductAttrValueDetailVO.setCreateTime( productAttrValueDetailBO.getCreateTime() );

        return adminsProductAttrValueDetailVO;
    }

    protected List<AdminsProductAttrValueDetailVO> productAttrValueDetailBOListToAdminsProductAttrValueDetailVOList(List<ProductAttrValueDetailBO> list) {
        if ( list == null ) {
            return null;
        }

        List<AdminsProductAttrValueDetailVO> list1 = new ArrayList<AdminsProductAttrValueDetailVO>( list.size() );
        for ( ProductAttrValueDetailBO productAttrValueDetailBO : list ) {
            list1.add( productAttrValueDetailBOToAdminsProductAttrValueDetailVO( productAttrValueDetailBO ) );
        }

        return list1;
    }

    protected AdminsProductAttrDetailVO productAttrDetailBOToAdminsProductAttrDetailVO(ProductAttrDetailBO productAttrDetailBO) {
        if ( productAttrDetailBO == null ) {
            return null;
        }

        AdminsProductAttrDetailVO adminsProductAttrDetailVO = new AdminsProductAttrDetailVO();

        adminsProductAttrDetailVO.setId( productAttrDetailBO.getId() );
        adminsProductAttrDetailVO.setName( productAttrDetailBO.getName() );
        adminsProductAttrDetailVO.setStatus( productAttrDetailBO.getStatus() );
        adminsProductAttrDetailVO.setCreateTime( productAttrDetailBO.getCreateTime() );
        adminsProductAttrDetailVO.setValues( productAttrValueDetailBOListToAdminsProductAttrValueDetailVOList( productAttrDetailBO.getValues() ) );

        return adminsProductAttrDetailVO;
    }

    protected List<AdminsProductAttrDetailVO> productAttrDetailBOListToAdminsProductAttrDetailVOList(List<ProductAttrDetailBO> list) {
        if ( list == null ) {
            return null;
        }

        List<AdminsProductAttrDetailVO> list1 = new ArrayList<AdminsProductAttrDetailVO>( list.size() );
        for ( ProductAttrDetailBO productAttrDetailBO : list ) {
            list1.add( productAttrDetailBOToAdminsProductAttrDetailVO( productAttrDetailBO ) );
        }

        return list1;
    }

    protected AdminsProductAttrValueSimpleVO productAttrValueSimpleBOToAdminsProductAttrValueSimpleVO(ProductAttrValueSimpleBO productAttrValueSimpleBO) {
        if ( productAttrValueSimpleBO == null ) {
            return null;
        }

        AdminsProductAttrValueSimpleVO adminsProductAttrValueSimpleVO = new AdminsProductAttrValueSimpleVO();

        adminsProductAttrValueSimpleVO.setId( productAttrValueSimpleBO.getId() );
        adminsProductAttrValueSimpleVO.setName( productAttrValueSimpleBO.getName() );

        return adminsProductAttrValueSimpleVO;
    }

    protected List<AdminsProductAttrValueSimpleVO> productAttrValueSimpleBOListToAdminsProductAttrValueSimpleVOList(List<ProductAttrValueSimpleBO> list) {
        if ( list == null ) {
            return null;
        }

        List<AdminsProductAttrValueSimpleVO> list1 = new ArrayList<AdminsProductAttrValueSimpleVO>( list.size() );
        for ( ProductAttrValueSimpleBO productAttrValueSimpleBO : list ) {
            list1.add( productAttrValueSimpleBOToAdminsProductAttrValueSimpleVO( productAttrValueSimpleBO ) );
        }

        return list1;
    }

    protected AdminsProductAttrSimpleVO productAttrSimpleBOToAdminsProductAttrSimpleVO(ProductAttrSimpleBO productAttrSimpleBO) {
        if ( productAttrSimpleBO == null ) {
            return null;
        }

        AdminsProductAttrSimpleVO adminsProductAttrSimpleVO = new AdminsProductAttrSimpleVO();

        adminsProductAttrSimpleVO.setId( productAttrSimpleBO.getId() );
        adminsProductAttrSimpleVO.setName( productAttrSimpleBO.getName() );
        adminsProductAttrSimpleVO.setValues( productAttrValueSimpleBOListToAdminsProductAttrValueSimpleVOList( productAttrSimpleBO.getValues() ) );

        return adminsProductAttrSimpleVO;
    }
}
