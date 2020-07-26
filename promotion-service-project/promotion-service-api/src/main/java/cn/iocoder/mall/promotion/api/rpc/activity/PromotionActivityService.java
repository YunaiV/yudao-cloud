package cn.iocoder.mall.promotion.api.rpc.activity.dto;

import java.util.Collection;
import java.util.List;

public interface PromotionActivityService {

    List<PromotionActivityRespDTO> getPromotionActivityListBySpuId(Integer spuId,
                                                                   Collection<Integer> activityStatuses);

    List<PromotionActivityRespDTO> getPromotionActivityListBySpuIds(Collection<Integer> spuIds,
                                                                    Collection<Integer> activityStatuses);

    PromotionActivityPageReqDTO getPromotionActivityPage(PromotionActivityPageRespDTO promotionActivityPageDTO);

}
