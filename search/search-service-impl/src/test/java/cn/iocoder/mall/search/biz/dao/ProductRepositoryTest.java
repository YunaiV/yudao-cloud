package cn.iocoder.mall.search.biz.dao;

import cn.iocoder.mall.search.biz.dataobject.ESProductDO;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Ignore
    public void testSave() {
//        productRepository.deleteById(1);
        ESProductDO product = new ESProductDO()
                .setId(1)
                .setName("你猜");
        productRepository.save(product);
    }

    @Test
    @Ignore
    public void testFindByName() {
        ESProductDO product = productRepository.findByName("锤子");
        System.out.println(product);
    }

    @Test
    public void testSearch() {
//        Page<ESProductDO> page = productRepository.search(639, null, 1, 10);
//        console(page.getContent());

//        Page<ESProductDO> page = productRepository.search(null, "数据库Oracle", 1, 10);
//        console(page.getContent());
    }

    private void console(List<ESProductDO> list) {
        list.forEach(System.out::println);
    }

}
