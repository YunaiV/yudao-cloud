package cn.iocoder.mall.promotion.api.rpc.activity;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.promotion.api.rpc.activity.dto.PromotionActivityListReqDTO;
import cn.iocoder.mall.promotion.api.rpc.activity.dto.PromotionActivityPageReqDTO;
import cn.iocoder.mall.promotion.api.rpc.activity.dto.PromotionActivityRespDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Title:
 * Description:
 *
 * @author zhuyang
 * @version 1.0 2021/10/9
 */
@FeignClient("promotion-service")
public interface PromotionActivityFeign {

    @PostMapping("/promotion/activity/pagePromotionActivity")
     CommonResult<PageResult<PromotionActivityRespDTO>> pagePromotionActivity(@RequestBody PromotionActivityPageReqDTO pageReqDTO) ;
    @PostMapping("/promotion/activity/listPromotionActivities")
     CommonResult<List<PromotionActivityRespDTO>> listPromotionActivities(@RequestBody PromotionActivityListReqDTO listReqDTO) ;
}
