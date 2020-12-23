package cn.iocoder.mall.order.rest.request.comment;

import cn.iocoder.common.framework.vo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * UsersOrderCommentPageRequest
 *
 * @author xiaofeng
 * @version 1.0
 * @date 2020/05/20 23:21
 */
@ApiModel("用户 - Order 模块 - 订单评论分页")
@Data
@Accessors(chain = true)
public class UsersOrderCommentPageRequest extends PageParam {

    /**
     * 商品 sku id
     */
    @ApiModelProperty(value = "商品 SKU id", required = true)
    @NotNull(message = "商品 SKU id 不能为空")
    private Integer productSkuId;
}
