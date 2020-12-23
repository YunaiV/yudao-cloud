package cn.iocoder.mall.order.rest.controller.comment;

import cn.iocoder.common.framework.enums.MallConstants;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * 评论回复模块 Api(user)
 *
 * @author wtz
 * @time 2019-05-31 18:00
 */
@RestController
@RequestMapping(MallConstants.ROOT_PATH_USER + "/order_comment_reply")
@Api("用户评论回复模块 ")
public class OrderCommentReplyController {

//    @Reference(validation = "true", version = "${dubbo.provider.OrderCommentService.version}")
//    private OrderCommentReplyService orderCommentReplyService;
//
//    @PostMapping("create_order_comment_reply")
//    //@RequiresLogin
//    @ApiOperation(value = "创建订单回复")
//    public CommonResult<OrderCommentReplyCreateBO> createOrderCommentReply(@RequestBody @Validated OrderCommentReplyCreateDTO orderCommentReplyCreateDTO){
//        return success(orderCommentReplyService.createOrderCommentReply(orderCommentReplyCreateDTO));
//    }
//
//    @GetMapping("order_comment_reply_page")
//    //@RequiresLogin
//    @ApiOperation(value = "分页获取评论回复")
//    public CommonResult<OrderCommentReplyPageBO> getOrderCommentReplyPage(@Validated OrderCommentReplyPageDTO orderCommentReplyCreateDTO){
//        return success(orderCommentReplyService.getOrderCommentReplyPage(orderCommentReplyCreateDTO));
//    }
}
