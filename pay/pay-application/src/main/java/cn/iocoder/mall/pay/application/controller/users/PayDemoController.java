package cn.iocoder.mall.pay.application.controller.users;

import cn.iocoder.common.framework.util.HttpUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.pay.api.PayTransactionService;
import cn.iocoder.mall.pay.api.bo.PayTransactionBO;
import cn.iocoder.mall.pay.api.dto.PayTransactionCreateDTO;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 示例 Controller
 */
@RestController
@RequestMapping("users/demo")
public class PayDemoController {

    @Reference(validation = "true")
    private PayTransactionService payTransactionService;

    @PostMapping("/create_order")
    public void createOrder(HttpServletRequest request) {
        // 创建业务订单
        // ...

        // 调用【支付服务】，创建交易订单
        PayTransactionCreateDTO payTransactionCreateDTO = new PayTransactionCreateDTO()
                .setAppId("1024")
                .setCreateIp(HttpUtil.getIp(request))
                .setOrderId("1")
                .setOrderSubject("商品名" )
                .setOrderDescription("商品描述")
                .setOrderMemo("商品备注")
                .setPrice(10)
                .setExpireTime(new Date());
        CommonResult<PayTransactionBO> result = payTransactionService.createTransaction(payTransactionCreateDTO);
        Assert.isTrue(result.isSuccess(), "一定会成功的");
    }

}