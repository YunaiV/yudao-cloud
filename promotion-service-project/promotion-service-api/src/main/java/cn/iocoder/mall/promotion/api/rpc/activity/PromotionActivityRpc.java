package cn.iocoder.mall.promotion.api.rpc.activity;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.promotion.api.rpc.activity.dto.PromotionActivityListReqDTO;
import cn.iocoder.mall.promotion.api.rpc.activity.dto.PromotionActivityRespDTO;

import java.util.List;

public interface PromotionActivityRpc {

//    List<PromotionActivityRespDTO> getPromotionActivityListBySpuId(Integer spuId,
//                                                                   Collection<Integer> activityStatuses);
//
//    List<PromotionActivityRespDTO> getPromotionActivityListBySpuIds(Collection<Integer> spuIds,
//                                                                    Collection<Integer> activityStatuses);
//
//    PromotionActivityPageReqDTO getPromotionActivityPage(PromotionActivityPageRespDTO promotionActivityPageDTO);

    CommonResult<List<PromotionActivityRespDTO>> listPromotionActivities(PromotionActivityListReqDTO listReqDTO);

}
