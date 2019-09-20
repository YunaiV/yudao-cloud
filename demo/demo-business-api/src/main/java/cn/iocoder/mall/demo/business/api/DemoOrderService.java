package cn.iocoder.mall.demo.business.api;

import cn.iocoder.mall.demo.business.bo.order.DemoOrderAddBO;
import cn.iocoder.mall.demo.business.bo.order.DemoOrderCancelBO;

public interface DemoOrderService {

    int add(DemoOrderAddBO addBO);

    int cancel(DemoOrderCancelBO cancelBO);

}
