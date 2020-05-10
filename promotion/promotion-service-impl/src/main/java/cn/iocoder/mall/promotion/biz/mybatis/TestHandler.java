package cn.iocoder.mall.promotion.biz.mybatis;

import cn.iocoder.mall.mybatis.type.JSONTypeHandler;
import cn.iocoder.mall.promotion.biz.dataobject.PromotionActivityDO;

public class TestHandler extends JSONTypeHandler<PromotionActivityDO.TimeLimitedDiscount> {

    public TestHandler(Class<PromotionActivityDO.TimeLimitedDiscount> clazz) {
        super(clazz);
    }

}
