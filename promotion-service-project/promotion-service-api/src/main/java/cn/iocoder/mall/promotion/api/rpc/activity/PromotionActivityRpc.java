package cn.iocoder.mall.promotion.api.rpc.activity;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.promotion.api.rpc.activity.dto.PromotionActivityListReqDTO;
import cn.iocoder.mall.promotion.api.rpc.activity.dto.PromotionActivityPageReqDTO;
import cn.iocoder.mall.promotion.api.rpc.activity.dto.PromotionActivityRespDTO;

import java.util.List;

/**
 * 促销活动 Rpc 接口
 */
public interface PromotionActivityRpc {

    CommonResult<PageResult<PromotionActivityRespDTO>> pagePromotionActivity(PromotionActivityPageReqDTO pageReqDTO);

    CommonResult<List<PromotionActivityRespDTO>> listPromotionActivities(PromotionActivityListReqDTO listReqDTO);

}
