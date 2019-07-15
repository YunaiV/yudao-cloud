package cn.iocoder.mall.product.api.message;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *  商品收藏或取消收藏时发送消息
 * @author xiaofeng
 * @date 2019/07/01 22:33
 * @version 1.0
 */
@Data
@Accessors(chain = true)
public class ProductSpuCollectionMessage {

    public static final String TOPIC = "ProductSpuCollection";

    /**
     * SPU 编号
     */
    private Integer spuId;

    /**
     * 用户ID
     */
    private Integer userId;

    // ========== 基本信息 =========
    /**
     * SPU 名字
     */
    private String spuName;


    /**
     * 商品图片
     */
    private String spuImage;

    /**
     * 卖点
     */
    private String sellPoint;

    /**
     * 价格，单位：分
     */
    private Integer price;

    /**
     * 1 收藏 2 取消
     */
    private Integer hasCollectionType;


}
