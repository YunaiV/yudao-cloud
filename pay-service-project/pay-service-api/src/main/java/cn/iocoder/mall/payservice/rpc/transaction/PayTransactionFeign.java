package cn.iocoder.mall.payservice.rpc.transaction;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.payservice.rpc.transaction.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Title:
 * Description:
 *
 * @author zhuyang
 * @version 1.0 2021/10/9
 */
@FeignClient(value = "pay-service")
public interface PayTransactionFeign {
    /**
     * 创建支付交易单
     *
     * @param createReqDTO 创建信息
     * @return 支付交易单号
     */
    @PostMapping("/pay/transaction/createPayTransaction")
    CommonResult<Integer> createPayTransaction(@RequestBody PayTransactionCreateReqDTO createReqDTO);

    /**
     * 提交支付交易单
     *
     * @param submitReqDTO 提交信息
     * @return 提交响应，包含三方支付的响应
     */
    @PostMapping("/pay/transaction/submitPayTransaction")
    CommonResult<PayTransactionSubmitRespDTO> submitPayTransaction(@RequestBody PayTransactionSubmitReqDTO submitReqDTO);

    /**
     * 获得当支付交易单
     *
     * @param getReqDTO 获得条件
     * @return 支付交易单
     */
    @PostMapping("/pay/transaction/getPayTransaction")
    CommonResult<PayTransactionRespDTO> getPayTransaction(@RequestBody PayTransactionGetReqDTO getReqDTO);

    /**
     * 更新交易支付成功
     *
     * @param successReqDTO 支付成功信息
     * @return 是否成功
     */
    @PostMapping("/pay/transaction/updatePayTransactionSuccess")
    CommonResult<Boolean> updatePayTransactionSuccess(@RequestBody PayTransactionSuccessReqDTO successReqDTO);

    /**
     * 获得交易支付单分页
     *
     * @param pageReqDTO 分页条件
     * @return 交易支付单分页
     */
    @PostMapping("/pay/transaction/pagePayTransaction")
    CommonResult<PageResult<PayTransactionRespDTO>> pagePayTransaction(@RequestBody  PayTransactionPageReqDTO pageReqDTO);
}
