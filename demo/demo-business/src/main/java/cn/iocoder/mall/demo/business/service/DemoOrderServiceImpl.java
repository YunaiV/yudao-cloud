package cn.iocoder.mall.demo.business.service;

import cn.iocoder.common.framework.constant.DeletedStatusEnum;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.mall.demo.business.api.DemoOrderService;
import cn.iocoder.mall.demo.business.api.DemoProductService;
import cn.iocoder.mall.demo.business.bo.order.DemoOrderAddBO;
import cn.iocoder.mall.demo.business.bo.order.DemoOrderCancelBO;
import cn.iocoder.mall.demo.business.bo.product.DemoProductBO;
import cn.iocoder.mall.demo.business.bo.product.DemoProductQuantityReduceBO;
import cn.iocoder.mall.demo.business.constant.OrderStatusEnum;
import cn.iocoder.mall.demo.business.convert.DemoOrderConvert;
import cn.iocoder.mall.demo.business.dao.mysql.DemoOrderMapper;
import cn.iocoder.mall.demo.business.dataobject.order.DemoOrderDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoOrderServiceImpl implements DemoOrderService {

    @Autowired
    private DemoProductService demoProductService;

    @Autowired
    private DemoOrderMapper demoOrderMapper;

    @Override
    public int add(DemoOrderAddBO addBO) {
        // 产品信息
        DemoProductBO productBO = demoProductService.get(addBO.getProductId());
        if (productBO == null) { // 商品不存在
            throw ServiceExceptionUtil.exception(100000); // TODO 芋艿，错误码
        }
        int quantity = 1;
        if (productBO.getQuantity() < quantity) { // 库存不够
            throw ServiceExceptionUtil.exception(100001); // TODO 芋艿，错误码
        }

        // 扣除库存
        demoProductService.updateQuantityReduce(new DemoProductQuantityReduceBO()
                .setId(addBO.getProductId()).setQuantity(quantity));

        // 创建订单
        DemoOrderDO orderDO = DemoOrderConvert.INSTANCE.convert(addBO);
        orderDO.setStatus(OrderStatusEnum.WAITING_PAYMENT.getValue())
            .setDeleted(DeletedStatusEnum.DELETED_NO.getValue());
        demoOrderMapper.insert(orderDO);

        // 返回订单编号
        return orderDO.getId();
    }

    @Override
    public int cancel(DemoOrderCancelBO cancelBO) {
        return 0;
    }

}
