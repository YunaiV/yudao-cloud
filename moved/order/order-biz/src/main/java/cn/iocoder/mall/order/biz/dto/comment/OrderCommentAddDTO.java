package cn.iocoder.mall.order.biz.dto.comment;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 订单评论创建
 *
 * @author wtz
 * @update xiaofeng
 * @time 2019-05-15 20:42
 * @update time 2020-05-13 0:07
 */
@Data
@Accessors(chain = true)
public class OrderCommentAddDTO implements Serializable {

    @NotNull(message = "订单 id 不能为空")
    private Integer orderId;

    @NotEmpty(message = "订单编号不能为空")
    private String orderNo;

    @NotNull(message = "商品的 spu id 不能为空")
    private Integer productSpuId;

    @NotEmpty(message = "商品的 spu name 不能为空")
    private String productSpuName;

    @NotNull(message = "商品的 sku id 不能为空")
    private Integer productSkuId;

    @NotEmpty(message = "商品的 sku attrs 不能为空")
    private String productSkuAttrs;

    @NotNull(message = "商品的 sku price 不能为空")
    private Integer productSkuPrice;

    @NotEmpty(message = "商品的 sku url 不能为空")
    private String productSkuPicUrl;

    private Integer userId;

    private String userAvatar;

    @NotEmpty(message = "用户昵称不能为空")
    private String userNickName;

    private Integer star;

    private Integer productDescriptionStar;

    private Integer logisticsStar;

    private Integer merchantStar;

    private String commentContent;

    private String commentPics;
}
