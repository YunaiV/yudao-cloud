package cn.iocoder.mall.order.rest.request.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 添加订单评论
 *
 * @author xiaofeng
 * @version 1.0
 * @date 2020/05/12 23:02
 */
@ApiModel("用户 - Order 模块 - 添加订单评论")
@Data
@Accessors(chain = true)
public class UsersOrderCommentAddRequest {

    @ApiModelProperty(value = "订单 id", required = true)
    @NotNull(message = "订单 id 不能为空")
    private Integer orderId;

    @ApiModelProperty(value = "订单编号", required = true)
    @NotEmpty(message = "订单编号不能为空")
    private String orderNo;

    @ApiModelProperty(value = "商品 spu id", required = true)
    @NotNull(message = "商品的 spu id 不能为空")
    private Integer productSpuId;

    @ApiModelProperty(value = "商品 spu name", required = true)
    @NotEmpty(message = "商品的 spu name 不能为空")
    private String productSpuName;

    @ApiModelProperty(value = "商品 sku id", required = true)
    @NotNull(message = "商品的 sku id 不能为空")
    private Integer productSkuId;

    @ApiModelProperty(value = "商品 sku attrs", required = true)
    @NotEmpty(message = "商品的 sku attrs 不能为空")
    private String productSkuAttrs;

    @ApiModelProperty(value = "商品 sku price", required = true)
    @NotNull(message = "商品的 sku price 不能为空")
    private Integer productSkuPrice;

    @ApiModelProperty(value = "商品 sku url", required = true)
    @NotEmpty(message = "商品的 sku url 不能为空")
    private String productSkuPicUrl;

    @ApiModelProperty(value = "用户 id", required = true)
    private Integer userId;

    @ApiModelProperty(value = "用户头像", required = true)
    private String userAvatar;

    @ApiModelProperty(value = "用户昵称", required = true)
    @NotEmpty(message = "用户昵称不能为空")
    private String userNickName;

    @ApiModelProperty(value = "评价星级", required = true, example = "1-5")
    private Integer star;

    @ApiModelProperty(value = "商品描述星级", required = true, example = "1-5")
    private Integer productDescriptionStar;

    @ApiModelProperty(value = "物流评价星级", required = true, example = "1-5")
    private Integer logisticsStar;

    @ApiModelProperty(value = "商家评价星级", required = true, example = "1-5")
    private Integer merchantStar;

    @ApiModelProperty(value = "商家评价内容", required = true, example = "1-5")
    private String commentContent;

    @ApiModelProperty(value = "评价图片", required = true)
    private String commentPics;

}
