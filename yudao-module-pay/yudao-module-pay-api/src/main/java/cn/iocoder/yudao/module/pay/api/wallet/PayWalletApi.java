package cn.iocoder.yudao.module.pay.api.wallet;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.pay.api.wallet.dto.PayWalletAddBalanceReqDTO;
import cn.iocoder.yudao.module.pay.api.wallet.dto.PayWalletRespDTO;
import cn.iocoder.yudao.module.pay.enums.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = ApiConstants.NAME) // TODO 芋艿：fallbackFactory =
@Tag(name = "RPC 服务 - 钱包")
public interface PayWalletApi {

    String PREFIX = ApiConstants.PREFIX + "/wallet";

    @PostMapping(PREFIX + "/add-balance")
    @Operation(summary = "添加钱包余额")
    CommonResult<Boolean> addWalletBalance(@Valid @RequestBody PayWalletAddBalanceReqDTO reqDTO);

    @GetMapping(PREFIX + "/get-or-create")
    @Operation(summary = "获取钱包信息")
    @Parameters({
            @Parameter(name = "userId", description = "用户编号", required = true, example = "1024"),
            @Parameter(name = "userType", description = "用户类型", required = true, example = "1")
    })
    CommonResult<PayWalletRespDTO> getOrCreateWallet(@RequestParam("userId") Long userId,
                                                     @RequestParam("userType") Integer userType);


}
