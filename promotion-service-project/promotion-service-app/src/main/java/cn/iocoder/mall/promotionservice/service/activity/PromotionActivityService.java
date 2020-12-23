package cn.iocoder.mall.promotionservice.service.activity;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.promotion.api.enums.RangeTypeEnum;
import cn.iocoder.mall.promotion.api.enums.activity.PromotionActivityTypeEnum;
import cn.iocoder.mall.promotion.api.rpc.activity.dto.PromotionActivityListReqDTO;
import cn.iocoder.mall.promotion.api.rpc.activity.dto.PromotionActivityPageReqDTO;
import cn.iocoder.mall.promotion.api.rpc.activity.dto.PromotionActivityRespDTO;
import cn.iocoder.mall.promotionservice.convert.activity.PromotionActivityConvert;
import cn.iocoder.mall.promotionservice.dal.mysql.dataobject.activity.PromotionActivityDO;
import cn.iocoder.mall.promotionservice.dal.mysql.mapper.activity.PromotionActivityMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


@Service
@Validated
public class PromotionActivityService {

    @Autowired
    private PromotionActivityMapper promotionActivityMapper;

    public List<PromotionActivityRespDTO> listPromotionActivities(PromotionActivityListReqDTO listReqDTO) {
        List<PromotionActivityDO> activityList = promotionActivityMapper.selectList(listReqDTO);
        return PromotionActivityConvert.INSTANCE.convertList(activityList);
    }

    public List<PromotionActivityRespDTO> listPromotionActivitiesBySpuIds(Collection<Integer> spuIds, Collection<Integer> activityStatuses) {
        if (spuIds.isEmpty() || activityStatuses.isEmpty()) {
            return Collections.emptyList();
        }
        // 查询指定状态的促销活动
        List<PromotionActivityDO> activityList = promotionActivityMapper.selectListByStatus(activityStatuses);
        if (activityList.isEmpty()) {
            return Collections.emptyList();
        }
        // 匹配商品
        for (Iterator<PromotionActivityDO> iterator = activityList.iterator(); iterator.hasNext();) {
            PromotionActivityDO activity = iterator.next();
            boolean matched = false;
            for (Integer spuId : spuIds) {
                if (PromotionActivityTypeEnum.TIME_LIMITED_DISCOUNT.getValue().equals(activity.getActivityType())) {
                    matched = isSpuMatchTimeLimitDiscount(spuId, activity);
                } else if (PromotionActivityTypeEnum.FULL_PRIVILEGE.getValue().equals(activity.getActivityType())) {
                    matched = isSpuMatchFullPrivilege(spuId, activity);
                }
                if (matched) {
                    break;
                }
            }
            // 不匹配，则进行移除
            if (!matched) {
                iterator.remove();
            } else { // 匹配，则做一些后续的处理
                // 如果是限时折扣，移除不在 spuId 数组中的折扣规则
                if (PromotionActivityTypeEnum.TIME_LIMITED_DISCOUNT.getValue().equals(activity.getActivityType())) {
                    activity.getTimeLimitedDiscount().getItems().removeIf(item -> !spuIds.contains(item.getSpuId()));
                }
            }
        }
        // 返回最终结果
        return PromotionActivityConvert.INSTANCE.convertList(activityList);
    }

    public PageResult<PromotionActivityRespDTO> pagePromotionActivity(PromotionActivityPageReqDTO pageReqDTO) {
        IPage<PromotionActivityDO> promotionActivityPage = promotionActivityMapper.selectPage(pageReqDTO);
        return PromotionActivityConvert.INSTANCE.convertPage(promotionActivityPage);
    }

    private boolean isSpuMatchTimeLimitDiscount(Integer spuId, PromotionActivityDO activity) {
        Assert.isTrue(PromotionActivityTypeEnum.TIME_LIMITED_DISCOUNT.getValue().equals(activity.getActivityType()),
                "传入的必须的促销活动必须是限时折扣");
        return activity.getTimeLimitedDiscount().getItems().stream()
                .anyMatch(item -> spuId.equals(item.getSpuId()));
    }

    private boolean isSpuMatchFullPrivilege(Integer spuId, PromotionActivityDO activity) {
        Assert.isTrue(PromotionActivityTypeEnum.FULL_PRIVILEGE.getValue().equals(activity.getActivityType()),
                "传入的必须的促销活动必须是满减送");
        PromotionActivityDO.FullPrivilege fullPrivilege = activity.getFullPrivilege();
        if (RangeTypeEnum.ALL.getValue().equals(fullPrivilege.getRangeType())) {
            return true;
        } else if (RangeTypeEnum.PRODUCT_INCLUDE_PART.getValue().equals(fullPrivilege.getRangeType())) {
            return fullPrivilege.getRangeValues().contains(spuId);
        } else {
            throw new IllegalArgumentException(String.format("促销活动(%s) 可用范围的类型是不正确", activity.toString()));
        }
    }

}
