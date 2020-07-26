package cn.iocoder.mall.promotionservice.convert.banner;

import cn.iocoder.mall.promotionservice.dal.mysql.dataobject.banner.BannerDO;
import cn.iocoder.mall.promotionservice.service.banner.bo.BannerAddBO;
import cn.iocoder.mall.promotionservice.service.banner.bo.BannerBO;
import cn.iocoder.mall.promotionservice.service.banner.bo.BannerUpdateBO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BannerConvert {

    BannerConvert INSTANCE = Mappers.getMapper(BannerConvert.class);

    @Mappings({})
    BannerBO convertToBO(BannerDO banner);

    @Mappings({})
    List<BannerBO> convertToBO(List<BannerDO> bannerList);

    @Mappings({})
    BannerDO convert(BannerAddBO bannerAddDTO);

    @Mappings({})
    BannerDO convert(BannerUpdateBO bannerUpdateDTO);

}