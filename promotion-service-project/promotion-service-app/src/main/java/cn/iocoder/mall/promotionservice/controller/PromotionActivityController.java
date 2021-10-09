package cn.iocoder.mall.promotionservice.controller;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.promotion.api.rpc.activity.dto.PromotionActivityListReqDTO;
import cn.iocoder.mall.promotion.api.rpc.activity.dto.PromotionActivityPageReqDTO;
import cn.iocoder.mall.promotion.api.rpc.activity.dto.PromotionActivityRespDTO;
import cn.iocoder.mall.promotionservice.manager.activity.PromotionActivityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
 * Title:
 * Description:
 *
 * @author zhuyang
 * @version 1.0 2021/10/9
 */
@RestController
@RequestMapping("/promotion/activity")
public class PromotionActivityController {
    @Autowired
    private PromotionActivityManager promotionActivityManager;

    @PostMapping("pagePromotionActivity")
    public CommonResult<PageResult<PromotionActivityRespDTO>> pagePromotionActivity(@RequestBody PromotionActivityPageReqDTO pageReqDTO) {
        return success(promotionActivityManager.pagePromotionActivity(pageReqDTO));
    }
    @PostMapping("listPromotionActivities")
    public CommonResult<List<PromotionActivityRespDTO>> listPromotionActivities(@RequestBody PromotionActivityListReqDTO listReqDTO) {
        return success(promotionActivityManager.listPromotionActivities(listReqDTO));
    }
}
