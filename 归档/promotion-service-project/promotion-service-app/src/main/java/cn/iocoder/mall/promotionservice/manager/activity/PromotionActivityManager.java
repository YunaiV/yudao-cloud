package cn.iocoder.mall.promotionservice.manager.activity;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.promotion.api.rpc.activity.dto.PromotionActivityListReqDTO;
import cn.iocoder.mall.promotion.api.rpc.activity.dto.PromotionActivityPageReqDTO;
import cn.iocoder.mall.promotion.api.rpc.activity.dto.PromotionActivityRespDTO;
import cn.iocoder.mall.promotionservice.service.activity.PromotionActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * 促销活动 Manager
 */
@Service
@Validated
public class PromotionActivityManager {

    @Autowired
    private PromotionActivityService promotionActivityService;

    public List<PromotionActivityRespDTO> listPromotionActivities(PromotionActivityListReqDTO listReqDTO) {
        return promotionActivityService.listPromotionActivities(listReqDTO);
    }

    public PageResult<PromotionActivityRespDTO> pagePromotionActivity(PromotionActivityPageReqDTO pageReqDTO) {
        return promotionActivityService.pagePromotionActivity(pageReqDTO);
    }

}
