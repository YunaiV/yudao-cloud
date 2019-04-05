package cn.iocoder.mall.promotion.application.convert;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.promotion.api.bo.BannerBO;
import cn.iocoder.mall.promotion.api.bo.BannerPageBO;
import cn.iocoder.mall.promotion.application.vo.admins.AdminsBannerPageVO;
import cn.iocoder.mall.promotion.application.vo.admins.AdminsBannerVO;
import cn.iocoder.mall.promotion.application.vo.users.UsersBannerVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-04-05T22:26:04+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.1 (Oracle Corporation)"
)
public class BannerConvertImpl implements BannerConvert {

    @Override
    public AdminsBannerVO convert(BannerBO bannerBO) {
        if ( bannerBO == null ) {
            return null;
        }

        AdminsBannerVO adminsBannerVO = new AdminsBannerVO();

        adminsBannerVO.setId( bannerBO.getId() );
        adminsBannerVO.setTitle( bannerBO.getTitle() );
        adminsBannerVO.setUrl( bannerBO.getUrl() );
        adminsBannerVO.setSort( bannerBO.getSort() );
        adminsBannerVO.setStatus( bannerBO.getStatus() );
        adminsBannerVO.setMemo( bannerBO.getMemo() );
        adminsBannerVO.setCreateTime( bannerBO.getCreateTime() );
        adminsBannerVO.setPicUrl( bannerBO.getPicUrl() );

        return adminsBannerVO;
    }

    @Override
    public CommonResult<AdminsBannerVO> convert2(CommonResult<BannerBO> result) {
        if ( result == null ) {
            return null;
        }

        CommonResult<AdminsBannerVO> commonResult = new CommonResult<AdminsBannerVO>();

        commonResult.setCode( result.getCode() );
        commonResult.setMessage( result.getMessage() );
        commonResult.setData( convert( result.getData() ) );

        return commonResult;
    }

    @Override
    public CommonResult<AdminsBannerPageVO> convert(CommonResult<BannerPageBO> result) {
        if ( result == null ) {
            return null;
        }

        CommonResult<AdminsBannerPageVO> commonResult = new CommonResult<AdminsBannerPageVO>();

        commonResult.setCode( result.getCode() );
        commonResult.setMessage( result.getMessage() );
        commonResult.setData( bannerPageBOToAdminsBannerPageVO( result.getData() ) );

        return commonResult;
    }

    @Override
    public List<UsersBannerVO> convertList(List<BannerBO> banners) {
        if ( banners == null ) {
            return null;
        }

        List<UsersBannerVO> list = new ArrayList<UsersBannerVO>( banners.size() );
        for ( BannerBO bannerBO : banners ) {
            list.add( bannerBOToUsersBannerVO( bannerBO ) );
        }

        return list;
    }

    protected List<AdminsBannerVO> bannerBOListToAdminsBannerVOList(List<BannerBO> list) {
        if ( list == null ) {
            return null;
        }

        List<AdminsBannerVO> list1 = new ArrayList<AdminsBannerVO>( list.size() );
        for ( BannerBO bannerBO : list ) {
            list1.add( convert( bannerBO ) );
        }

        return list1;
    }

    protected AdminsBannerPageVO bannerPageBOToAdminsBannerPageVO(BannerPageBO bannerPageBO) {
        if ( bannerPageBO == null ) {
            return null;
        }

        AdminsBannerPageVO adminsBannerPageVO = new AdminsBannerPageVO();

        adminsBannerPageVO.setList( bannerBOListToAdminsBannerVOList( bannerPageBO.getList() ) );
        adminsBannerPageVO.setTotal( bannerPageBO.getTotal() );

        return adminsBannerPageVO;
    }

    protected UsersBannerVO bannerBOToUsersBannerVO(BannerBO bannerBO) {
        if ( bannerBO == null ) {
            return null;
        }

        UsersBannerVO usersBannerVO = new UsersBannerVO();

        usersBannerVO.setUrl( bannerBO.getUrl() );
        usersBannerVO.setPicUrl( bannerBO.getPicUrl() );

        return usersBannerVO;
    }
}
