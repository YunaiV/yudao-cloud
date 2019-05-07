package cn.iocoder.mall.promotion.application.controller.admins;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.promotion.api.PromotionActivityService;
import cn.iocoder.mall.promotion.api.bo.PromotionActivityPageBO;
import cn.iocoder.mall.promotion.api.constant.PromotionActivityStatusEnum;
import cn.iocoder.mall.promotion.api.dto.PromotionActivityPageDTO;
import io.swagger.annotations.Api;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@RestController
@RequestMapping("admins/promotion_activity")
@Api("促销活动模块")
public class AdminsPromotionActivityController {

    @Reference(validation = "true", version = "${dubbo.provider.PromotionActivityService.version}")
    private PromotionActivityService promotionActivityService;

    @GetMapping("/page") // TODO 芋艿，BO => VO
    public CommonResult<PromotionActivityPageBO> page(@RequestParam(value = "title", required = false) String title,
                                                      @RequestParam(value = "activityType") Integer activityType,
                                                      @RequestParam(value = "status") String status,
                                                      @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                                      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        PromotionActivityPageDTO promotionActivityPageDTO = new PromotionActivityPageDTO()
                .setTitle(title).setActivityType(activityType).setPageNo(pageNo).setPageSize(pageSize);
        switch (status) {
            case "WAIT":
                promotionActivityPageDTO.setStatuses(Collections.singleton(PromotionActivityStatusEnum.WAIT.getValue()));
                break;
            case "RUN":
                promotionActivityPageDTO.setStatuses(Collections.singleton(PromotionActivityStatusEnum.RUN.getValue()));
                break;
            case "END":
                promotionActivityPageDTO.setStatuses(Collections.singleton(PromotionActivityStatusEnum.END.getValue()));
                break;
            case "INVALID":
                promotionActivityPageDTO.setStatuses(Collections.singleton(PromotionActivityStatusEnum.INVALID.getValue()));
                break;
            default:
                promotionActivityPageDTO.setStatuses(Arrays.asList(PromotionActivityStatusEnum.WAIT.getValue(),
                        PromotionActivityStatusEnum.RUN.getValue(), PromotionActivityStatusEnum.END.getValue(),
                        PromotionActivityStatusEnum.INVALID.getValue()));
        }
        // 执行查询
        return success(promotionActivityService.getPromotionActivityPage(promotionActivityPageDTO));
    }

}
