package cn.iocoder.mall.order.application.controller.users;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.DataDictService;
import cn.iocoder.mall.admin.api.bo.DataDictBO;
import cn.iocoder.mall.order.api.OrderReturnService;
import cn.iocoder.mall.order.api.bo.OrderReturnInfoBO;
import cn.iocoder.mall.order.api.constant.DictKeyConstants;
import cn.iocoder.mall.order.api.dto.OrderReturnApplyDTO;
import cn.iocoder.mall.order.application.convert.OrderReturnConvert;
import cn.iocoder.mall.order.application.po.user.OrderReturnApplyPO;
import com.alibaba.dubbo.config.annotation.Reference;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单退款/售后流程
 *
 * @author Sin
 * @time 2019-04-25 22:04
 */
@RestController
@RequestMapping("users/order_return")
public class OrderReturnController {

    @Reference(validation = "true")
    private OrderReturnService orderReturnService;
    @Reference(validation = "true")
    private DataDictService dataDictService;

    @GetMapping("reason")
    @ApiOperation("原因")
    public CommonResult<List<DataDictBO>> orderReturnReason() {
        return dataDictService.getDataDict(DictKeyConstants.ORDER_RETURN_REASON);
    }

    @PostMapping("apply")
    @ApiOperation("订单售后")
    public CommonResult orderReturnApply(@RequestBody OrderReturnApplyPO orderReturnApplyPO) {
        OrderReturnApplyDTO applyDTO = OrderReturnConvert.INSTANCE.convert(orderReturnApplyPO);
        return orderReturnService.orderReturnApply(applyDTO);
    }

    @GetMapping("info")
    @ApiOperation("订单售后详细")
    public CommonResult<OrderReturnInfoBO> orderApplyInfo(@RequestParam("orderId") Integer orderId) {
        CommonResult<OrderReturnInfoBO> commonResult = orderReturnService.orderApplyInfo(orderId);

        // 转换 字典值
        if (commonResult.isSuccess()) {
            CommonResult<DataDictBO> dataDictResult = dataDictService.getDataDict(
                    DictKeyConstants.ORDER_RETURN_SERVICE_TYPE,
                    commonResult.getData().getReturnInfo().getServiceType());

            if (dataDictResult.isSuccess()) {
                commonResult.getData().getReturnInfo().setServiceTypeText(dataDictResult.getData().getDisplayName());
            }
        }

        return commonResult;
    }
}
