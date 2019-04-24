package cn.iocoder.mall.search.biz.service;

import cn.iocoder.mall.search.biz.dao.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ProductSearchServiceImplTest {

    @Autowired
    private ProductSearchServiceImpl productSearchService;
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testRebuild() {
        int counts = productSearchService.rebuild().getData();
        System.out.println("重建数量：" + counts);

        System.out.println(productRepository.count());
    }

}
