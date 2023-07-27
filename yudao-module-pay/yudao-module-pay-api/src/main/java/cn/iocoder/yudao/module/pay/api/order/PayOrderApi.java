package cn.iocoder.yudao.module.pay.api.order;

import cn.iocoder.yudao.module.pay.api.order.dto.PayOrderCreateReqDTO;
import cn.iocoder.yudao.module.pay.api.order.dto.PayOrderRespDTO;
import cn.iocoder.yudao.module.pay.enums.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name = ApiConstants.NAME) // TODO 芋艿：fallbackFactory =
@Tag(name = "RPC 服务 - 支付单")
public interface PayOrderApi {

    String PREFIX = ApiConstants.PREFIX + "/order";

    @PostMapping(PREFIX + "/create")
    @Operation(summary = "创建支付单")
    Long createOrder(@Valid @RequestBody PayOrderCreateReqDTO reqDTO);

    @PostMapping(PREFIX + "/get")
    @Operation(summary = "获得支付单")
    @Parameter(name = "id", description = "支付单编号", example = "1", required = true)
    PayOrderRespDTO getOrder(Long id);

}
