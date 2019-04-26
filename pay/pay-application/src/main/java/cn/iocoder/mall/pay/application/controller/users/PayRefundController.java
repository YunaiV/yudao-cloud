package cn.iocoder.mall.pay.application.controller.users;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.pay.api.PayRefundService;
import cn.iocoder.mall.pay.api.constant.PayChannelEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

@RestController
@RequestMapping("users/refund") // TODO 芋艿，理论来说，是用户无关的。这里先酱紫先~
public class PayRefundController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PayRefundService payRefundService;

    @PostMapping(value = "pingxx_refund_success", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String pingxxRefundSuccess(HttpServletRequest request) throws IOException {
        logger.info("[pingxxRefundSuccess][被回调]");
        // 读取 webhook
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }

        CommonResult<Boolean> result = payRefundService.updateRefundSuccess(PayChannelEnum.PINGXX.getId(), sb.toString());
        if (result.isError()) {
            logger.error("[pingxxRefundSuccess][message({}) result({})]", sb, result);
            return "failure";
        }
        return "success";
    }

}
