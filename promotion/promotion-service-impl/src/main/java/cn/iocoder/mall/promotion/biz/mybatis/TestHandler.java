package cn.iocoder.mall.promotion.biz.mybatis;

import cn.iocoder.common.framework.mybatis.JSONTypeHandler;
import cn.iocoder.mall.promotion.biz.dataobject.PromotionActivityDO;

public class TestHandler extends JSONTypeHandler<PromotionActivityDO.TimeLimitedDiscount> {

    public TestHandler(Class<PromotionActivityDO.TimeLimitedDiscount> clazz) {
        super(clazz);
    }

}
