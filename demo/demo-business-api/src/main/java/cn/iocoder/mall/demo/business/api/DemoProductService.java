package cn.iocoder.mall.demo.business.api;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.demo.business.bo.product.*;

public interface DemoProductService {

    DemoProductBO get(Integer id);

    PageResult<DemoProductBO> page(DemoProductPageBO page);

    int add(DemoProductAddBO product);

    int update(DemoProductUpdateBO product);

//    void updateQuantityIncrease();

    void updateQuantityReduce(DemoProductQuantityReduceBO reduceBO);

}
