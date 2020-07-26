package cn.iocoder.mall.promotionservice.convert.activity;

import cn.iocoder.mall.promotion.api.rpc.activity.dto.PromotionActivityRespDTO;
import cn.iocoder.mall.promotionservice.dal.mysql.dataobject.activity.PromotionActivityDO;
import cn.iocoder.mall.promotionservice.service.activity.bo.PromotionActivityBO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PromotionActivityConvert {

    PromotionActivityConvert INSTANCE = Mappers.getMapper(PromotionActivityConvert.class);

    @Mappings({})
    PromotionActivityBO convertToBO(PromotionActivityDO activity);

    @Mappings({})
    List<PromotionActivityBO> convertToBO(List<PromotionActivityDO> activityList);

    @Mappings({})
    List<PromotionActivityDO> convertToDO(List<PromotionActivityBO> activityList);

    @Mappings({})
    List<PromotionActivityRespDTO> convertToRespDTO(List<PromotionActivityDO> activityList);

//    @Mappings({})
//    PromotionActivityDO convert(PromotionActivityAddDTO activityAddDTO);
//
//    @Mappings({})
//    PromotionActivityDO convert(PromotionActivityUpdateDTO activityUpdateDTO);

}
