package cn.iocoder.mall.searchservice.mq.consumer;

import cn.iocoder.mall.searchservice.manager.product.SearchProductManager;
import cn.iocoder.mall.searchservice.mq.consumer.message.ProductUpdateMessage;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 商品更新 Topic 的消费者，重建对应的商品的 ES 索引
 */
@Service
@RocketMQMessageListener(
        topic = ProductUpdateMessage.TOPIC,
        consumerGroup = "${spring.application.name}-consumer-group-" + ProductUpdateMessage.TOPIC
)
public class ProductUpdateConsumer implements RocketMQListener<ProductUpdateMessage> {

    @Autowired
    private SearchProductManager productSearchManager;

    @Override
    public void onMessage(ProductUpdateMessage message) {
        Boolean result = productSearchManager.saveProduct(message.getId());
        Assert.isTrue(result, String.format("重构商品(%d)的 ES 索引，必然成功。实际结果是 %s", message.getId(), result));
    }

}
