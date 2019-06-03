package cn.iocoder.mall.order.application.controller.users;

import cn.iocoder.common.framework.constant.MallConstants;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.order.api.OrderCommentReplyService;
import cn.iocoder.mall.order.api.bo.OrderCommentReplyCreateBO;
import cn.iocoder.mall.order.api.bo.OrderCommentReplyPageBO;
import cn.iocoder.mall.order.api.dto.OrderCommentReplyCreateDTO;
import cn.iocoder.mall.order.api.dto.OrderCommentReplyPageDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.common.framework.vo.CommonResult.success;


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

    @Reference(validation = "true", version = "${dubbo.provider.OrderCommentService.version}")
    private OrderCommentReplyService orderCommentReplyService;

    @PostMapping("create_order_comment_reply")
    //@RequiresLogin
    @ApiOperation(value = "创建订单回复")
    public CommonResult<OrderCommentReplyCreateBO> createOrderCommentReply(@RequestBody @Validated OrderCommentReplyCreateDTO orderCommentReplyCreateDTO){
        return success(orderCommentReplyService.createOrderCommentReply(orderCommentReplyCreateDTO));
    }

    @GetMapping("order_comment_reply_page")
    //@RequiresLogin
    @ApiOperation(value = "分页获取评论回复")
    public CommonResult<OrderCommentReplyPageBO> getOrderCommentReplyPage(@Validated OrderCommentReplyPageDTO orderCommentReplyCreateDTO){
        return success(orderCommentReplyService.getOrderCommentReplyPage(orderCommentReplyCreateDTO));
    }
}
