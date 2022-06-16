package cn.iocoder.mall.searchservice.service.product;

import cn.iocoder.mall.searchservice.service.product.bo.SearchProductConditionBO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;

/**
 * {@link SearchProductService} 的测试类，目前是集成测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class SearchProductServiceTest {

    static {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }

    @Autowired
    private SearchProductService searchProductService;

    @Test
    public void testGetSearchCondition() {
        SearchProductConditionBO conditionBO = searchProductService.getSearchProductCondition("商品", Collections.singletonList("category"));
        System.out.println(conditionBO);
    }

}
