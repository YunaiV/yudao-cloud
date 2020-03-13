package cn.iocoder.mall.demo.business.service;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.demo.business.api.DemoProductService;
import cn.iocoder.mall.demo.business.bo.product.*;
import cn.iocoder.mall.demo.business.convert.DemoProductConvert;
import cn.iocoder.mall.demo.business.dao.mysql.DemoProductMapper;
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

    @Override
    public PageResult<DemoProductBO> page(DemoProductPageBO page) {
        return null;
    }

    @Override
    public int add(DemoProductAddBO product) {
        return 0;
    }

    @Override
    public int update(DemoProductUpdateBO product) {
        return 0;
    }

    @Override
    public void updateQuantityReduce(DemoProductQuantityReduceBO reduceBO) {
        int updateCount = demoProductMapper.updateQuantityReduce(reduceBO.getId(), reduceBO.getQuantity());
        if (updateCount == 0) {
            throw ServiceExceptionUtil.exception(20000); // TODO 芋艿，错误码
        }
    }

}
