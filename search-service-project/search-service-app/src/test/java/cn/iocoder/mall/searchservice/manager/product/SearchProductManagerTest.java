package cn.iocoder.mall.searchservice.manager.product;

import cn.iocoder.mall.searchservice.dal.es.repository.ESProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * {@link SearchProductManager} 的测试类，目前是集成测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class SearchProductManagerTest {

    static {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }

    @Autowired
    private SearchProductManager searchProductManager;

    @Autowired
    private ESProductRepository esProductRepository;

    @Test
    public void testRebuild() {
        int counts = searchProductManager.rebuild();
        System.out.println("重建数量：" + counts);

        System.out.println(esProductRepository.count());
    }

}
