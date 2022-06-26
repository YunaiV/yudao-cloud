package cn.iocoder.mall.managementweb.manager.promotion.activity;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.promotion.activity.vo.PromotionActivityPageReqVO;
import cn.iocoder.mall.managementweb.convert.promotion.PromotionActivityConvert;
import cn.iocoder.mall.promotion.api.rpc.activity.PromotionActivityFeign;
import cn.iocoder.mall.promotion.api.rpc.activity.dto.PromotionActivityRespDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 促销活动 Manager
 */
@Service
@Validated
public class PromotionActivityManager {


    @Autowired
    private PromotionActivityFeign promotionActivityFeign;

    public PageResult<PromotionActivityRespDTO> pagePromotionActivity(PromotionActivityPageReqVO pageReqVO) {
        CommonResult<PageResult<PromotionActivityRespDTO>> pagePromotionActivityResult = promotionActivityFeign.pagePromotionActivity(
                PromotionActivityConvert.INSTANCE.convert(pageReqVO));
        pagePromotionActivityResult.checkError();
        return pagePromotionActivityResult.getData();
    }

}
