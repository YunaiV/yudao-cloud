package cn.iocoder.yudao.module.promotion.api.point;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.promotion.api.point.dto.PointValidateJoinRespDTO;
import cn.iocoder.yudao.module.promotion.service.point.PointActivityService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 积分商城活动 Api 接口实现类
 *
 * @author HUIHUI
 */
@RestController // 提供 RESTful API 接口，给 Feign 调用
@Validated
public class PointActivityApiImpl implements PointActivityApi {

    @Resource
    private PointActivityService pointActivityService;

    @Override
    public CommonResult<PointValidateJoinRespDTO> validateJoinPointActivity(Long activityId, Long skuId, Integer count) {
        return success(pointActivityService.validateJoinPointActivity(activityId, skuId, count));
    }

    @Override
    public CommonResult<Boolean> updatePointStockDecr(Long id, Long skuId, Integer count) {
        pointActivityService.updatePointStockDecr(id, skuId, count);
        return success(true);
    }

    @Override
    public CommonResult<Boolean> updatePointStockIncr(Long id, Long skuId, Integer count) {
        pointActivityService.updatePointStockIncr(id, skuId, count);
        return success(true);
    }

}
