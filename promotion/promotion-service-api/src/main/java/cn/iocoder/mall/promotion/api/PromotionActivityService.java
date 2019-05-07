package cn.iocoder.mall.promotion.api;

import cn.iocoder.mall.promotion.api.bo.PromotionActivityBO;
import cn.iocoder.mall.promotion.api.bo.PromotionActivityPageBO;
import cn.iocoder.mall.promotion.api.dto.PromotionActivityPageDTO;

import java.util.Collection;
import java.util.List;

public interface PromotionActivityService {

    List<PromotionActivityBO> getPromotionActivityListBySpuId(Integer spuId,
                                                                            Collection<Integer> activityStatuses);

    List<PromotionActivityBO> getPromotionActivityListBySpuIds(Collection<Integer> spuIds,
                                                               Collection<Integer> activityStatuses);

    PromotionActivityPageBO getPromotionActivityPage(PromotionActivityPageDTO promotionActivityPageDTO);

}
