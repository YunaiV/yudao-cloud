package cn.iocoder.mall.promotion.api;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.promotion.api.bo.PromotionActivityBO;

import java.util.Collection;
import java.util.List;

public interface PromotionActivityService {

    CommonResult<List<PromotionActivityBO>> getPromotionActivityListBySpuId(Integer spuId,
                                                                            Collection<Integer> activityStatuses);

    CommonResult<List<PromotionActivityBO>> getPromotionActivityListBySpuIds(Collection<Integer> spuIds,
                                                                             Collection<Integer> activityStatuses);

}
