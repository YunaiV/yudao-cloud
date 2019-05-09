package cn.iocoder.mall.order.application.controller.admins;

import cn.iocoder.common.framework.util.HttpUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.order.api.OrderReturnService;
import cn.iocoder.mall.order.api.bo.OrderReturnListBO;
import cn.iocoder.mall.order.api.dto.OrderReturnQueryDTO;
import cn.iocoder.mall.order.application.convert.OrderReturnConvert;
import cn.iocoder.mall.order.application.po.admin.OrderReturnQueryPO;
import io.swagger.annotations.Api;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 订单退货
 *
 * @author Sin
 * @time 2019-05-06 21:31
 */
@RestController
@RequestMapping("admins/order_return")
@Api("订单退货(admins api)")
public class AdminOrderReturnController {

    @Autowired
    @Reference(validation = "true")
    private OrderReturnService orderReturnService;

    @GetMapping("list")
    public CommonResult<OrderReturnListBO> list(@Validated OrderReturnQueryPO queryPO) {
        OrderReturnQueryDTO queryDTO = OrderReturnConvert.INSTANCE.convert(queryPO);
        return orderReturnService.orderReturnList(queryDTO);
    }

    @PostMapping("agree")
    public CommonResult agree(@RequestParam("id") Integer id) {
        return orderReturnService.orderReturnAgree(id);
    }

    @PostMapping("refuse")
    public CommonResult refuse(@RequestParam("id") Integer id) {
        return orderReturnService.orderReturnRefuse(id);
    }

    @PostMapping("confirm_receipt")
    public CommonResult confirmReceipt(@RequestParam("id") Integer id) {
        return orderReturnService.confirmReceipt(id);
    }

    @PostMapping("confirm_refund")
    public CommonResult confirmRefund(HttpServletRequest request, @RequestParam("id") Integer id) {
        String ip = HttpUtil.getIp(request);
        return orderReturnService.refund(id, ip);
    }
}
