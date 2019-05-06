package cn.iocoder.mall.promotion.application.convert;

import cn.iocoder.mall.promotion.api.bo.BannerBO;
import cn.iocoder.mall.promotion.api.bo.BannerPageBO;
import cn.iocoder.mall.promotion.application.vo.admins.AdminsBannerPageVO;
import cn.iocoder.mall.promotion.application.vo.admins.AdminsBannerVO;
import cn.iocoder.mall.promotion.application.vo.users.UsersBannerVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

public interface BannerConvert {

    Users USERS = Mappers.getMapper(Users.class);

    Admins ADMINS = Mappers.getMapper(Admins.class);

    @Mapper
    interface Admins {

        @Mappings({})
        AdminsBannerVO convert(BannerBO bannerBO);

        @Mappings({})
        AdminsBannerPageVO convert3(BannerPageBO bannerPageBO);

    }

    @Mapper
    interface Users {

        @Mappings({})
        List<UsersBannerVO> convertList(List<BannerBO> banners);

    }

}
