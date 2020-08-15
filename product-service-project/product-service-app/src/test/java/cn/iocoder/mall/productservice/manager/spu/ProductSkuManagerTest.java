package cn.iocoder.mall.productservice.manager.spu;

import cn.iocoder.mall.productservice.enums.sku.ProductSkuDetailFieldEnum;
import cn.iocoder.mall.productservice.manager.sku.ProductSkuManager;
import cn.iocoder.mall.productservice.rpc.sku.dto.ProductSkuListQueryReqDTO;
import cn.iocoder.mall.productservice.rpc.sku.dto.ProductSkuRespDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Slf4j
public class ProductSkuManagerTest {

    @Autowired
    private ProductSkuManager productSkuManager;

    @Test
    public void testListProductSkus() {
        List<ProductSkuRespDTO> skuRespDTOs = productSkuManager.listProductSkus(
                new ProductSkuListQueryReqDTO().setProductSkuIds(Arrays.asList(3, 4))
                    .setFields(Arrays.asList(ProductSkuDetailFieldEnum.SPU.getField(),
                            ProductSkuDetailFieldEnum.ATTR.getField())));
        log.info("结果：{}", skuRespDTOs);
    }

}
