package cn.iocoder.mall.order.application.controller.users;

import cn.iocoder.common.framework.constant.MallConstants;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.order.api.OrderCommentService;
import cn.iocoder.mall.order.api.bo.OrderCommentCreateBO;
import cn.iocoder.mall.order.api.dto.OrderCommentCreateDTO;
import cn.iocoder.mall.user.sdk.annotation.RequiresLogin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
 *
 * 订单评论 Api(user)
 *
 * @author wtz
 * @time 2019-05-27 20:46
 */
@RestController
@RequestMapping(MallConstants.ROOT_PATH_USER + "/order_comment")
@Api("用户评论模块")
public class OrderCommentController {

    @Reference(validation = "true", version = "${dubbo.provider.OrderCommentService.version}")
    private OrderCommentService orderCommentService;


    @PostMapping("create_order_comment")
    //@RequiresLogin
    @ApiOperation(value = "创建订单")
    public CommonResult<OrderCommentCreateBO> createOrder(@RequestBody @Validated OrderCommentCreateDTO orderCommentCreateDTO) {
        return success(orderCommentService.createOrderComment(orderCommentCreateDTO));
    }
}
