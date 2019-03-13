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
                .setAppId("POd4RC6a")
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

    @PostMapping("/pingxx")
    public String pingxx() {
        return "{\n" +
                "  \"id\": \"ch_n5COmHGG8iX5TWf5qDynvTaP\",\n" +
                "  \"object\": \"charge\",\n" +
                "  \"created\": 1552445643,\n" +
                "  \"livemode\": false,\n" +
                "  \"paid\": false,\n" +
                "  \"refunded\": false,\n" +
                "  \"reversed\": false,\n" +
                "  \"app\": \"app_aTyfXDjrvzDSbLuz\",\n" +
                "  \"channel\": \"wx_pub\",\n" +
                "  \"order_no\": \"1552445643093\",\n" +
                "  \"client_ip\": \"127.0.0.1\",\n" +
                "  \"amount\": 1,\n" +
                "  \"amount_settle\": 1,\n" +
                "  \"currency\": \"cny\",\n" +
                "  \"subject\": \"测试商品\",\n" +
                "  \"body\": \"测试描述\",\n" +
                "  \"time_paid\": null,\n" +
                "  \"time_expire\": 1552452843,\n" +
                "  \"time_settle\": null,\n" +
                "  \"transaction_no\": null,\n" +
                "  \"refunds\": {\n" +
                "    \"object\": \"list\",\n" +
                "    \"url\": \"/v1/charges/ch_n5COmHGG8iX5TWf5qDynvTaP/refunds\",\n" +
                "    \"has_more\": false,\n" +
                "    \"data\": []\n" +
                "  },\n" +
                "  \"amount_refunded\": 0,\n" +
                "  \"failure_code\": null,\n" +
                "  \"failure_msg\": null,\n" +
                "  \"metadata\": {},\n" +
                "  \"credential\": {\n" +
                "    \"object\": \"credential\",\n" +
                "    \"wx_pub\": {\n" +
                "      \"appId\": \"wxytom5krtuf54qjff\",\n" +
                "      \"timeStamp\": \"1552445643\",\n" +
                "      \"nonceStr\": \"5cc0206f78d8bf931980f5132d5ce394\",\n" +
                "      \"package\": \"prepay_id=1101000000190313rx9y5oahkkcsm5gg\",\n" +
                "      \"signType\": \"MD5\",\n" +
                "      \"paySign\": \"9F6E80E89439575B8414FA56ADB07228\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"extra\": {\n" +
                "    \"open_id\": \"just_for_test\"\n" +
                "  },\n" +
                "  \"description\": null\n" +
                "}";
    }

}