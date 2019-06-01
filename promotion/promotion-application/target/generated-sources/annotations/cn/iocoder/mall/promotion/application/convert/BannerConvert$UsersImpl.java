package cn.iocoder.mall.promotion.application.convert;

import cn.iocoder.mall.promotion.api.bo.BannerBO;
import cn.iocoder.mall.promotion.application.convert.BannerConvert.Users;
import cn.iocoder.mall.promotion.application.vo.users.UsersBannerVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-24T11:39:15+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class BannerConvert$UsersImpl implements Users {

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
