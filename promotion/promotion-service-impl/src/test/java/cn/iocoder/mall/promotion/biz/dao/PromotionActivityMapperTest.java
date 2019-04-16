package cn.iocoder.mall.promotion.biz.dao;

import cn.iocoder.common.framework.util.DateUtil;
import cn.iocoder.mall.promotion.api.constant.PreferentialTypeEnum;
import cn.iocoder.mall.promotion.api.constant.PromotionActivityStatusEnum;
import cn.iocoder.mall.promotion.api.constant.PromotionActivityTypeEnum;
import cn.iocoder.mall.promotion.api.constant.RangeTypeEnum;
import cn.iocoder.mall.promotion.biz.dataobject.PromotionActivityDO;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class PromotionActivityMapperTest {

    @Autowired
    private PromotionActivityMapper promotionActivityMapper;

    /**
     * 插入限时折扣活动
     */
    @Test
    @Ignore
    public void testInsert01() {
        // 创建 PromotionActivityDO 对象
        PromotionActivityDO activityDO = new PromotionActivityDO();
        activityDO.setTitle("老板跑路了");
        activityDO.setActivityType(PromotionActivityTypeEnum.TIME_LIMITED_DISCOUNT.getValue());
        activityDO.setStatus(PromotionActivityStatusEnum.RUN.getValue());
        activityDO.setStartTime(new Date());
        activityDO.setEndTime(DateUtil.addDate(new Date(), Calendar.DAY_OF_YEAR, 100));
        activityDO.setCreateTime(new Date());
        // 创建 TimeLimitedDiscount 对象
        PromotionActivityDO.TimeLimitedDiscount discount = new PromotionActivityDO.TimeLimitedDiscount();
        discount.setQuota(0);
        discount.setItems(new ArrayList<>());
        PromotionActivityDO.TimeLimitedDiscount.Item item01 = new PromotionActivityDO.TimeLimitedDiscount.Item();
        item01.setSpuId(32);
        item01.setPreferentialType(PreferentialTypeEnum.DISCOUNT.getValue());
        item01.setPreferentialValue(40);
        discount.getItems().add(item01);
        activityDO.setTimeLimitedDiscount(discount);
        promotionActivityMapper.insert(activityDO);
    }

    /**
     * 插入满减送活动
     */
    @Test
    public void testInsert02() {
        // 创建 PromotionActivityDO 对象
        PromotionActivityDO activityDO = new PromotionActivityDO();
        activityDO.setTitle("老四赶海");
        activityDO.setActivityType(PromotionActivityTypeEnum.FULL_PRIVILEGE.getValue());
        activityDO.setStatus(PromotionActivityStatusEnum.RUN.getValue());
        activityDO.setStartTime(new Date());
        activityDO.setEndTime(DateUtil.addDate(new Date(), Calendar.DAY_OF_YEAR, 100));
        activityDO.setCreateTime(new Date());
        // 创建 TimeLimitedDiscount 对象
        PromotionActivityDO.FullPrivilege fullPrivilege = new PromotionActivityDO.FullPrivilege();
        fullPrivilege.setRangeType(RangeTypeEnum.ALL.getValue());
        fullPrivilege.setCycled(Boolean.FALSE);
        fullPrivilege.setPrivileges(new ArrayList<>());
        PromotionActivityDO.FullPrivilege.Privilege privilege01 = new PromotionActivityDO.FullPrivilege.Privilege();
        privilege01.setMeetType(1); // TODO 芋艿，硬编码
        privilege01.setMeetValue(20);
        privilege01.setPreferentialType(PreferentialTypeEnum.DISCOUNT.getValue());
        privilege01.setPreferentialValue(80);
        fullPrivilege.getPrivileges().add(privilege01);
        activityDO.setFullPrivilege(fullPrivilege);
        promotionActivityMapper.insert(activityDO);
    }

    /**
     * 查询促销活动
     */
    @Test
    public void testSelectById() {
        PromotionActivityDO activity01 = promotionActivityMapper.selectById(1);
        System.out.println(activity01);

        PromotionActivityDO activity02 = promotionActivityMapper.selectById(2);
        System.out.println(activity02);
    }

}
