package cn.iocoder.mall.managementweb.controller.promotion.activity;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.managementweb.controller.promotion.activity.vo.PromotionActivityPageReqVO;
import cn.iocoder.mall.managementweb.manager.promotion.activity.PromotionActivityManager;
import cn.iocoder.mall.promotion.api.rpc.activity.dto.PromotionActivityRespDTO;
import cn.iocoder.security.annotations.RequiresPermissions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@RestController
@RequestMapping("/promotion/activity")
@Api(tags = "促销活动 API")
@Validated
public class PromotionActivityController {

    @Autowired
    private PromotionActivityManager promotionActivityManager;

    // TODO 芋艿：DTO => VO
    @GetMapping("/page")
    @ApiOperation("获得促销活动分页")
    @RequiresPermissions("promotion:activity:page")
    public CommonResult<PageResult<PromotionActivityRespDTO>> pagePromotionActivity(PromotionActivityPageReqVO pageReqVO) {
        return success(promotionActivityManager.pagePromotionActivity(pageReqVO));
    }

}
