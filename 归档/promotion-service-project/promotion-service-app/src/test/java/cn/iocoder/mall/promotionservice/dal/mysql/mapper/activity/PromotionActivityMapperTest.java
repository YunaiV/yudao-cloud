package cn.iocoder.mall.promotionservice.dal.mysql.mapper.activity;

import cn.iocoder.mall.promotion.api.enums.activity.PromotionActivityStatusEnum;
import cn.iocoder.mall.promotionservice.dal.mysql.dataobject.activity.PromotionActivityDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class PromotionActivityMapperTest {

    @Autowired
    private PromotionActivityMapper promotionActivityMapper;

    @Test
    public void testSelectListByStatus() {
        List<PromotionActivityDO> result = promotionActivityMapper.selectListByStatus(
                Collections.singleton(PromotionActivityStatusEnum.RUN.getValue()));
        System.out.println(result.size());
    }

}
