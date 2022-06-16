package cn.iocoder.mall.order.rest.controller.comment;

import cn.iocoder.common.framework.enums.MallConstants;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.order.biz.dto.comment.OrderCommentPageDTO;
import cn.iocoder.mall.order.biz.service.comment.OrderCommentService;
import cn.iocoder.mall.order.rest.convert.comment.UsersOrderCommentConvert;
import cn.iocoder.mall.order.rest.request.comment.UsersOrderCommentAddRequest;
import cn.iocoder.mall.order.rest.request.comment.UsersOrderCommentPageRequest;
import cn.iocoder.mall.order.rest.response.comment.UsersOrderCommentPageResponse;
import cn.iocoder.mall.security.core.context.UserSecurityContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UsersOrderCommentController
 *
 * @author xiaofeng
 * @version 1.0
 * @date 2020/05/12 22:56
 */
@RestController
@RequestMapping(MallConstants.ROOT_PATH_USER + "/order_comment")
@Api("订单商品评论模块")
public class UsersOrderCommentController {

    private final OrderCommentService orderCommentService;

    public UsersOrderCommentController(
            OrderCommentService orderCommentService) {
        this.orderCommentService = orderCommentService;
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加订单评论")
    public CommonResult<Boolean> add(
            @RequestBody @Validated UsersOrderCommentAddRequest request) {
        Integer userId = UserSecurityContextHolder.getContext().getUserId();
        request.setUserId(userId);

        return CommonResult.success(orderCommentService.addOrderComment(
                UsersOrderCommentConvert.INSTANCE.convert(request)));
    }

    @GetMapping("/page")
    @ApiOperation(value = "获取订单评论")
    public CommonResult<PageResult<UsersOrderCommentPageResponse>> page(
            UsersOrderCommentPageRequest request) {
        OrderCommentPageDTO orderCommentPageDTO = UsersOrderCommentConvert.INSTANCE
                .convert(request);
        return CommonResult.success(UsersOrderCommentConvert.INSTANCE
                .convert(orderCommentService.page(orderCommentPageDTO)));
    }


}
