package cn.iocoder.mall.promotion.application.convert;

import cn.iocoder.mall.promotion.api.bo.BannerBO;
import cn.iocoder.mall.promotion.api.bo.BannerPageBO;
import cn.iocoder.mall.promotion.application.convert.BannerConvert.Admins;
import cn.iocoder.mall.promotion.application.vo.admins.AdminsBannerPageVO;
import cn.iocoder.mall.promotion.application.vo.admins.AdminsBannerVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-24T11:39:16+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class BannerConvert$AdminsImpl implements Admins {

    @Override
    public AdminsBannerVO convert(BannerBO bannerBO) {
        if ( bannerBO == null ) {
            return null;
        }

        AdminsBannerVO adminsBannerVO = new AdminsBannerVO();

        adminsBannerVO.setId( bannerBO.getId() );
        adminsBannerVO.setTitle( bannerBO.getTitle() );
        adminsBannerVO.setUrl( bannerBO.getUrl() );
        adminsBannerVO.setPicUrl( bannerBO.getPicUrl() );
        adminsBannerVO.setSort( bannerBO.getSort() );
        adminsBannerVO.setStatus( bannerBO.getStatus() );
        adminsBannerVO.setMemo( bannerBO.getMemo() );
        adminsBannerVO.setCreateTime( bannerBO.getCreateTime() );

        return adminsBannerVO;
    }

    @Override
    public AdminsBannerPageVO convert3(BannerPageBO bannerPageBO) {
        if ( bannerPageBO == null ) {
            return null;
        }

        AdminsBannerPageVO adminsBannerPageVO = new AdminsBannerPageVO();

        adminsBannerPageVO.setList( bannerBOListToAdminsBannerVOList( bannerPageBO.getList() ) );
        adminsBannerPageVO.setTotal( bannerPageBO.getTotal() );

        return adminsBannerPageVO;
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
}
