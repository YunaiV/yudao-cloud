package cn.iocoder.yudao.module.promotion.api.seckill;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.promotion.api.seckill.dto.SeckillValidateJoinRespDTO;
import cn.iocoder.yudao.module.promotion.service.seckill.SeckillActivityService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 秒杀活动接口 Api 接口实现类
 *
 * @author HUIHUI
 */
@RestController // 提供 RESTful API 接口，给 Feign 调用
@Validated
public class SeckillActivityApiImpl implements SeckillActivityApi {

    @Resource
    private SeckillActivityService activityService;

    @Override
    public CommonResult<Boolean> updateSeckillStockDecr(Long id, Long skuId, Integer count) {
        activityService.updateSeckillStockDecr(id, skuId, count);
        return success(true);
    }

    @Override
    public CommonResult<Boolean> updateSeckillStockIncr(Long id, Long skuId, Integer count) {
        activityService.updateSeckillStockIncr(id, skuId, count);
        return success(true);
    }

    @Override
    public CommonResult<SeckillValidateJoinRespDTO> validateJoinSeckill(Long activityId, Long skuId, Integer count) {
        return success(activityService.validateJoinSeckill(activityId, skuId, count));
    }

}
