package cn.iocoder.mall.promotion.application.convert;

import cn.iocoder.mall.promotion.api.bo.CouponTemplateBO;
import cn.iocoder.mall.promotion.api.bo.CouponTemplatePageBO;
import cn.iocoder.mall.promotion.application.vo.admins.AdminsCouponTemplatePageVO;
import cn.iocoder.mall.promotion.application.vo.admins.AdminsCouponTemplateVO;
import cn.iocoder.mall.promotion.application.vo.users.UsersCouponTemplateVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CouponTemplateConvert {

    Users USERS = Mappers.getMapper(Users.class);

    Admins ADMINS = Mappers.getMapper(Admins.class);

    @Mapper
    interface Admins {

        @Mappings({})
        AdminsCouponTemplateVO convert(CouponTemplateBO template);

        @Mappings({})
        AdminsCouponTemplatePageVO convertPage(CouponTemplatePageBO result);

        @Mappings({})
        List<AdminsCouponTemplateVO> convertList(List<CouponTemplateBO> templates);

    }

    @Mapper
    interface Users {

        @Mappings({})
        UsersCouponTemplateVO convert2(CouponTemplateBO template);

    }

}
