package cn.iocoder.mall.demo.rpc.service;

import cn.iocoder.mall.demo.business.api.DemoProductService;
import cn.iocoder.mall.demo.business.bo.product.DemoProductBO;
import cn.iocoder.mall.demo.rpc.api.DemoProductRpcService;
import cn.iocoder.mall.demo.rpc.convert.DemoProductConvert;
import cn.iocoder.mall.demo.rpc.vo.DemoProductVO;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service(validation = "true", version = "${dubbo.provider.DemoProductRpcService.version}")
public class DemoProductRpcServiceImpl implements DemoProductRpcService {

    @Autowired
    private DemoProductService productService;

    @Override
    public DemoProductVO get(Integer id) {
        DemoProductBO product = productService.get(id);
        return DemoProductConvert.INSTANCE.convert(product);
    }

}
