package cn.iocoder.mall.pay.biz.service;

import cn.iocoder.common.framework.util.DateUtil;
import cn.iocoder.common.framework.util.MathUtil;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.pay.api.PayRefundService;
import cn.iocoder.mall.pay.api.bo.refund.PayRefundPageBO;
import cn.iocoder.mall.pay.api.bo.refund.PayRefundSubmitBO;
import cn.iocoder.mall.pay.api.constant.PayErrorCodeEnum;
import cn.iocoder.mall.pay.api.constant.PayRefundStatus;
import cn.iocoder.mall.pay.api.constant.PayTransactionStatusEnum;
import cn.iocoder.mall.pay.api.dto.refund.PayRefundPageDTO;
import cn.iocoder.mall.pay.api.dto.refund.PayRefundSubmitDTO;
import cn.iocoder.mall.pay.biz.client.AbstractPaySDK;
import cn.iocoder.mall.pay.biz.client.PaySDKFactory;
import cn.iocoder.mall.pay.biz.client.RefundSuccessBO;
import cn.iocoder.mall.pay.biz.convert.PayRefundConvert;
import cn.iocoder.mall.pay.biz.dao.PayRefundMapper;
import cn.iocoder.mall.pay.biz.dataobject.PayAppDO;
import cn.iocoder.mall.pay.biz.dataobject.PayRefundDO;
import cn.iocoder.mall.pay.biz.dataobject.PayTransactionDO;
import cn.iocoder.mall.pay.biz.dataobject.PayTransactionExtensionDO;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.PayRefundService.version}")
public class PayRefundServiceImpl implements PayRefundService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PayRefundMapper payRefundMapper;

    @Autowired
    private PayAppServiceImpl payAppService;
    @Autowired
    private PayNotifyServiceImpl payNotifyService;
    @Autowired
    private PayTransactionServiceImpl payTransactionService;

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public CommonResult<PayRefundSubmitBO> submitRefund(PayRefundSubmitDTO payRefundSubmitDTO) {
        // 校验 App 是否有效
        PayAppDO payAppDO = payAppService.validPayApp(payRefundSubmitDTO.getAppId());
        // 获得 PayTransactionDO ，并校验其是否存在
        PayTransactionDO payTransaction = payTransactionService.getTransaction(payRefundSubmitDTO.getAppId(), payRefundSubmitDTO.getOrderId());
        if (payTransaction == null) { // 是否存在
            return ServiceExceptionUtil.error(PayErrorCodeEnum.PAY_TRANSACTION_NOT_FOUND.getCode());
        }
        if (!PayTransactionStatusEnum.SUCCESS.getValue().equals(payTransaction.getStatus())) { // 校验状态，必须是待支付
            return ServiceExceptionUtil.error(PayErrorCodeEnum.PAY_TRANSACTION_STATUS_IS_NOT_SUCCESS.getCode());
        }
        if (payRefundSubmitDTO.getPrice() > payTransaction.getPrice() - payTransaction.getRefundTotal()) { // 金额校验
            return ServiceExceptionUtil.error(PayErrorCodeEnum.PAY_REFUND_PRICE_EXCEED.getCode());
        }
        // 获得 PayTransactionExtensionDO ，并校验其是否存在
        PayTransactionExtensionDO payTransactionExtension = payTransactionService.getPayTransactionExtension(payTransaction.getExtensionId());
        if (payTransactionExtension == null) { // 是否存在
            return ServiceExceptionUtil.error(PayErrorCodeEnum.PAY_TRANSACTION_EXTENSION_NOT_FOUND.getCode());
        }
        if (!PayTransactionStatusEnum.SUCCESS.getValue().equals(payTransactionExtension.getStatus())) { // 校验状态，必须是待支付
            return ServiceExceptionUtil.error(PayErrorCodeEnum.PAY_TRANSACTION_EXTENSION_STATUS_IS_NOT_SUCCESS.getCode());
        }
        // 插入 PayTransactionExtensionDO
        PayRefundDO payRefundDO = PayRefundConvert.INSTANCE.convert(payRefundSubmitDTO)
                .setTransactionId(payTransaction.getId())
                .setRefundCode(generateTransactionCode()) // TODO 芋艿，后续调整
                .setStatus(PayRefundStatus.WAITING.getValue())
                .setNotifyUrl(payAppDO.getRefundNotifyUrl())
                .setRefundChannel(payTransaction.getPayChannel());
        payRefundDO.setCreateTime(new Date());
        payRefundMapper.insert(payRefundDO);
        // 调用三方接口
        AbstractPaySDK paySDK = PaySDKFactory.getSDK(payTransaction.getPayChannel());
        CommonResult<String> invokeResult = paySDK.submitRefund(payRefundDO, payTransactionExtension, null); // TODO 暂时传入 extra = null
        if (invokeResult.isError()) {
            return CommonResult.error(invokeResult);
        }
        // 返回成功
        PayRefundSubmitBO payRefundSubmitBO = new PayRefundSubmitBO()
                .setId(payRefundDO.getId());
        return CommonResult.success(payRefundSubmitBO);
    }

    @Override
    @Transactional
    public CommonResult<Boolean> updateRefundSuccess(Integer payChannel, String params) {
        // TODO 芋艿，记录回调日志
        // 解析传入的参数，成 TransactionSuccessBO 对象
        AbstractPaySDK paySDK = PaySDKFactory.getSDK(payChannel);
        CommonResult<RefundSuccessBO> paySuccessResult = paySDK.parseRefundSuccessParams(params);
        if (paySuccessResult.isError()) {
            return CommonResult.error(paySuccessResult);
        }
        // TODO 芋艿，先最严格的校验。即使调用方重复调用，实际哪个订单已经被重复回调的支付，也返回 false 。也没问题，因为实际已经回调成功了。
        // 1.1 查询 PayRefundDO
        PayRefundDO payRefund = payRefundMapper.selectByRefundCode(paySuccessResult.getData().getRefundCode());
        if (payRefund == null) {
            return ServiceExceptionUtil.error(PayErrorCodeEnum.PAY_REFUND_NOT_FOUND.getCode());
        }
        if (!PayRefundStatus.WAITING.getValue().equals(payRefund.getStatus())) { // 校验状态，必须是待支付
            return ServiceExceptionUtil.error(PayErrorCodeEnum.PAY_REFUND_STATUS_NOT_WAITING.getCode());
        }
        // 1.2 更新 PayRefundDO
        Integer status = paySuccessResult.getData().getSuccess() ? PayRefundStatus.SUCCESS.getValue() : PayRefundStatus.FAILURE.getValue();
        PayRefundDO updatePayRefundDO = new PayRefundDO()
                .setId(payRefund.getId())
                .setStatus(status)
                .setTradeNo(paySuccessResult.getData().getTradeNo())
                .setExtensionData(params);
        int updateCounts = payRefundMapper.update(updatePayRefundDO, PayRefundStatus.WAITING.getValue());
        if (updateCounts == 0) { // 校验状态，必须是待支付
            throw ServiceExceptionUtil.exception(PayErrorCodeEnum.PAY_REFUND_STATUS_NOT_WAITING.getCode());
        }
        // 2.1 判断 PayTransactionDO ，增加已退款金额
        PayTransactionDO payTransaction = payTransactionService.getTransaction(payRefund.getTransactionId());
        if (payTransaction == null) {
            return ServiceExceptionUtil.error(PayErrorCodeEnum.PAY_TRANSACTION_NOT_FOUND.getCode());
        }
        if (!PayTransactionStatusEnum.SUCCESS.getValue().equals(payTransaction.getStatus())) { // 校验状态，必须是已支付
            throw ServiceExceptionUtil.exception(PayErrorCodeEnum.PAY_TRANSACTION_STATUS_IS_NOT_SUCCESS.getCode());
        }
        if (payRefund.getPrice() + payTransaction.getRefundTotal() > payTransaction.getPrice()) {
            throw ServiceExceptionUtil.exception(PayErrorCodeEnum.PAY_REFUND_PRICE_EXCEED.getCode());
        }
        // 2.2 更新 PayTransactionDO
        updateCounts = payTransactionService.updateTransactionPriceTotalIncr(payRefund.getTransactionId(), payRefund.getPrice());
        if (updateCounts == 0) { // 保证不超退 TODO 这种类型，需要思考下。需要返回错误，但是又要保证事务回滚
            throw ServiceExceptionUtil.exception(PayErrorCodeEnum.PAY_REFUND_PRICE_EXCEED.getCode());
        }
        // 3 新增 PayNotifyTaskDO
        payNotifyService.addRefundNotifyTask(payRefund);
        // 返回结果
        return CommonResult.success(true);
    }

    @Override
    public PayRefundPageBO getRefundPage(PayRefundPageDTO payRefundPageDTO) {
        PayRefundPageBO payRefundPageBO = new PayRefundPageBO();
        // 查询分页数据
        int offset = (payRefundPageDTO.getPageNo() - 1) * payRefundPageDTO.getPageSize();
        payRefundPageBO.setList(PayRefundConvert.INSTANCE.convertList(payRefundMapper.selectListByPage(
                payRefundPageDTO.getCreateBeginTime(), payRefundPageDTO.getCreateEndTime(),
                payRefundPageDTO.getFinishBeginTime(), payRefundPageDTO.getFinishEndTime(),
                payRefundPageDTO.getStatus(), payRefundPageDTO.getPayChannel(),
                offset, payRefundPageDTO.getPageSize())));
        // 查询分页总数
        payRefundPageBO.setTotal(payRefundMapper.selectCountByPage(
                payRefundPageDTO.getCreateBeginTime(), payRefundPageDTO.getCreateEndTime(),
                payRefundPageDTO.getFinishBeginTime(), payRefundPageDTO.getFinishEndTime(),
                payRefundPageDTO.getStatus(), payRefundPageDTO.getPayChannel()));
        return payRefundPageBO;
    }

    private String generateTransactionCode() {
//    wx
//    2014
//    10
//    27
//    20
//    09
//    39
//    5522657
//    a690389285100
        // 目前的算法
        // 时间序列，年月日时分秒 14 位
        // 纯随机，6 位 TODO 此处估计是会有问题的，后续在调整
        return DateUtil.format(new Date(), "yyyyMMddHHmmss") + // 时间序列
                MathUtil.random(100000, 999999) // 随机。为什么是这个范围，因为偷懒
                ;
    }

}
