package cn.iocoder.mall.searchservice.mq.consumer.message;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 商品更新（包括创建）消息
 */
@Data
@Accessors(chain = true)
public class ProductUpdateMessage {

    public static final String TOPIC = "ProductUpdate";

    /**
     * 商品编号
     */
    private Integer id;

}
