package cn.iocoder.mall.demo.business.service;

import cn.iocoder.mall.demo.business.api.DemoProductService;
import cn.iocoder.mall.demo.business.bo.DemoProductBO;
import cn.iocoder.mall.demo.business.convert.DemoProductConvert;
import cn.iocoder.mall.demo.business.dao.DemoProductMapper;
import cn.iocoder.mall.demo.business.dataobject.product.DemoProductDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoProductServiceImpl implements DemoProductService {

    @Autowired
    private DemoProductMapper demoProductMapper;

    @Override
    public DemoProductBO get(Integer id) {
        DemoProductDO product = demoProductMapper.selectById(id);
        return DemoProductConvert.INSTANCE.convert(product);
    }

}
