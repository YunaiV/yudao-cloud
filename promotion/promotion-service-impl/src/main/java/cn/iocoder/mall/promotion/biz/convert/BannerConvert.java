package cn.iocoder.mall.promotion.biz.convert;

import cn.iocoder.mall.promotion.api.bo.BannerBO;
import cn.iocoder.mall.promotion.api.dto.BannerAddDTO;
import cn.iocoder.mall.promotion.api.dto.BannerUpdateDTO;
import cn.iocoder.mall.promotion.biz.dataobject.BannerDO;
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
    BannerDO convert(BannerAddDTO bannerAddDTO);

    @Mappings({})
    BannerDO convert(BannerUpdateDTO bannerUpdateDTO);

}