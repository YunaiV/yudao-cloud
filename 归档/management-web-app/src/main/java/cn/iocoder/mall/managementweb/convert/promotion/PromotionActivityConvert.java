package cn.iocoder.mall.managementweb.convert.promotion;

import cn.iocoder.mall.managementweb.controller.promotion.activity.vo.PromotionActivityPageReqVO;
import cn.iocoder.mall.promotion.api.rpc.activity.dto.PromotionActivityPageReqDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PromotionActivityConvert {

    PromotionActivityConvert INSTANCE = Mappers.getMapper(PromotionActivityConvert.class);

    PromotionActivityPageReqDTO convert(PromotionActivityPageReqVO bean);

}
