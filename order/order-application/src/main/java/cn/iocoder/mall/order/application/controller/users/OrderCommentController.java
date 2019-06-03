package cn.iocoder.mall.order.application.controller.users;

import cn.iocoder.common.framework.constant.MallConstants;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.order.api.OrderCommentReplyService;
import cn.iocoder.mall.order.api.OrderCommentService;
import cn.iocoder.mall.order.api.bo.OrderCommentCreateBO;
import cn.iocoder.mall.order.api.bo.OrderCommentInfoAndMerchantReplyBO;
import cn.iocoder.mall.order.api.bo.OrderCommentPageBO;
import cn.iocoder.mall.order.api.dto.OrderCommentCreateDTO;
import cn.iocoder.mall.order.api.dto.OrderCommentPageDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


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

    @Reference(validation = "true", version = "${dubbo.provider.OrderCommentReplyService.version}")
    private OrderCommentReplyService orderCommentReplyService;


    @PostMapping("create_order_comment")
    //@RequiresLogin
    @ApiOperation(value = "创建订单")
    public CommonResult<OrderCommentCreateBO> createOrder(@RequestBody @Validated OrderCommentCreateDTO orderCommentCreateDTO) {
        return success(orderCommentService.createOrderComment(orderCommentCreateDTO));
    }

    @GetMapping("order_comment_page")
    //@RequiresLogin
    @ApiOperation(value = "获取评论分页")
    public CommonResult<OrderCommentPageBO> getOrderCommentPage(@Validated OrderCommentPageDTO orderCommentPageDTO){
        return success(orderCommentService.getOrderCommentPage(orderCommentPageDTO));
    }

    @GetMapping("order_comment_info_merchant_reply")
    //@RequiresLogin
    @ApiOperation(value = "获取评论和商家回复")
    public CommonResult<OrderCommentInfoAndMerchantReplyBO> geOrderCommentInfoAndMerchantReply(@RequestParam("commentId") Integer commentId){
        OrderCommentInfoAndMerchantReplyBO orderCommentInfoAndMerchantReplyBO=new OrderCommentInfoAndMerchantReplyBO();
        orderCommentInfoAndMerchantReplyBO.setOrderCommentInfoBO(orderCommentService.getOrderCommentInfo(commentId));
        orderCommentInfoAndMerchantReplyBO.setOrderCommentMerchantReplyBOS(orderCommentReplyService.getOrderCommentMerchantReply(commentId));
        return success(orderCommentInfoAndMerchantReplyBO);
    }

}
