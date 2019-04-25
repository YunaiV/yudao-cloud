package cn.iocoder.mall.search.biz.mq;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.product.api.message.ProductUpdateMessage;
import cn.iocoder.mall.search.api.ProductSearchService;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RocketMQMessageListener(
        topic = ProductUpdateMessage.TOPIC,
        consumerGroup = "search-consumer-group-" + ProductUpdateMessage.TOPIC
)
public class PayTransactionPaySuccessConsumer implements RocketMQListener<ProductUpdateMessage> {

    @Autowired
    private ProductSearchService productSearchService;

    @Override
    public void onMessage(ProductUpdateMessage message) {
        CommonResult<Boolean> result = productSearchService.save(message.getId());
        Assert.isTrue(result.isSuccess(), String.format("重构商品 ES 索引，必然成功。实际结果是 %s", result));
    }

}
