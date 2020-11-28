package cn.iocoder.mall.pay.biz.service;

import cn.iocoder.common.framework.util.DateUtil;
import cn.iocoder.common.framework.util.MathUtil;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.pay.api.PayTransactionService;
import cn.iocoder.mall.pay.api.bo.transaction.PayTransactionBO;
import cn.iocoder.mall.pay.api.bo.transaction.PayTransactionPageBO;
import cn.iocoder.mall.pay.api.bo.transaction.PayTransactionSubmitBO;
import cn.iocoder.mall.pay.api.constant.PayErrorCodeEnum;
import cn.iocoder.mall.pay.api.constant.PayTransactionStatusEnum;
import cn.iocoder.mall.pay.api.dto.transaction.PayTransactionCreateDTO;
import cn.iocoder.mall.pay.api.dto.transaction.PayTransactionGetDTO;
import cn.iocoder.mall.pay.api.dto.transaction.PayTransactionPageDTO;
import cn.iocoder.mall.pay.api.dto.transaction.PayTransactionSubmitDTO;
import cn.iocoder.mall.pay.biz.client.AbstractPaySDK;
import cn.iocoder.mall.pay.biz.client.PaySDKFactory;
import cn.iocoder.mall.pay.biz.client.TransactionSuccessBO;
import cn.iocoder.mall.pay.biz.convert.PayTransactionConvert;
import cn.iocoder.mall.pay.biz.dao.PayNotifyTaskMapper;
import cn.iocoder.mall.pay.biz.dao.PayTransactionExtensionMapper;
import cn.iocoder.mall.pay.biz.dao.PayTransactionMapper;
import cn.iocoder.mall.pay.biz.dataobject.PayAppDO;
import cn.iocoder.mall.pay.biz.dataobject.PayTransactionDO;
import cn.iocoder.mall.pay.biz.dataobject.PayTransactionExtensionDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.PayTransactionService.version}")
public class PayTransactionServiceImpl implements PayTransactionService {

    public PayTransactionDO getTransaction(Integer id) {
        return payTransactionMapper.selectById(id);
    }

    public int updateTransactionPriceTotalIncr(Integer id, Integer incr) {
        return payTransactionMapper.updateForRefundTotal(id, incr);
    }

    public PayTransactionExtensionDO getPayTransactionExtension(Integer id) {
        return payTransactionExtensionMapper.selectById(id);
    }

    @Override
    @Transactional
    public Boolean updateTransactionPaySuccess(Integer payChannel, String params) {
        // TODO 芋艿，记录回调日志
        // 解析传入的参数，成 TransactionSuccessBO 对象
        AbstractPaySDK paySDK = PaySDKFactory.getSDK(payChannel);
        CommonResult<TransactionSuccessBO> paySuccessResult = paySDK.parseTransactionSuccessParams(params);
        if (paySuccessResult.isError()) {
            throw ServiceExceptionUtil.exception(paySuccessResult.getCode(), paySuccessResult.getMessage());
        }
        // TODO 芋艿，先最严格的校验。即使调用方重复调用，实际哪个订单已经被重复回调的支付，也返回 false 。也没问题，因为实际已经回调成功了。
        // 1.1 查询 PayTransactionExtensionDO
        PayTransactionExtensionDO extension = payTransactionExtensionMapper.selectByTransactionCode(paySuccessResult.getData().getTransactionCode());
        if (extension == null) {
            throw ServiceExceptionUtil.exception(PayErrorCodeEnum.PAY_TRANSACTION_EXTENSION_NOT_FOUND.getCode());
        }
        if (!PayTransactionStatusEnum.WAITING.getValue().equals(extension.getStatus())) { // 校验状态，必须是待支付
            throw ServiceExceptionUtil.exception(PayErrorCodeEnum.PAY_TRANSACTION_EXTENSION_STATUS_IS_NOT_WAITING.getCode());
        }
        // 1.2 更新 PayTransactionExtensionDO
        PayTransactionExtensionDO updatePayTransactionExtension = new PayTransactionExtensionDO()
                .setId(extension.getId())
                .setStatus(PayTransactionStatusEnum.SUCCESS.getValue())
                .setExtensionData(params);
        int updateCounts = payTransactionExtensionMapper.update(updatePayTransactionExtension, PayTransactionStatusEnum.WAITING.getValue());
        if (updateCounts == 0) { // 校验状态，必须是待支付
            throw ServiceExceptionUtil.exception(PayErrorCodeEnum.PAY_TRANSACTION_EXTENSION_STATUS_IS_NOT_WAITING.getCode());
        }
        logger.info("[updateTransactionPaySuccess][PayTransactionExtensionDO({}) 更新为已支付]", extension.getId());
        // 2.1 判断 PayTransactionDO 是否处于待支付
        PayTransactionDO transaction = payTransactionMapper.selectById(extension.getTransactionId());
        if (transaction == null) {
            throw ServiceExceptionUtil.exception(PayErrorCodeEnum.PAY_TRANSACTION_NOT_FOUND.getCode());
        }
        if (!PayTransactionStatusEnum.WAITING.getValue().equals(transaction.getStatus())) { // 校验状态，必须是待支付
            throw ServiceExceptionUtil.exception(PayErrorCodeEnum.PAY_TRANSACTION_STATUS_IS_NOT_WAITING.getCode());
        }
        // 2.2 更新 PayTransactionDO
        PayTransactionDO updatePayTransaction = new PayTransactionDO()
                .setId(transaction.getId())
                .setStatus(PayTransactionStatusEnum.SUCCESS.getValue())
                .setExtensionId(extension.getId())
                .setPayChannel(payChannel)
                .setPaymentTime(paySuccessResult.getData().getPaymentTime())
                .setNotifyTime(new Date())
                .setTradeNo(paySuccessResult.getData().getTradeNo());
        updateCounts = payTransactionMapper.update(updatePayTransaction, PayTransactionStatusEnum.WAITING.getValue());
        if (updateCounts == 0) { // 校验状态，必须是待支付 TODO 这种类型，需要思考下。需要返回错误，但是又要保证事务回滚
            throw ServiceExceptionUtil.exception(PayErrorCodeEnum.PAY_TRANSACTION_STATUS_IS_NOT_WAITING.getCode());
        }
        logger.info("[updateTransactionPaySuccess][PayTransactionDO({}) 更新为已支付]", transaction.getId());
        // 3 新增 PayNotifyTaskDO 注释原因，参见 PayRefundSuccessConsumer 类。
//        payNotifyService.addTransactionNotifyTask(transaction, extension);
        // 返回结果
        return true;
    }

    @Override
    public List<PayTransactionBO> getTransactionList(Collection<Integer> ids) {
        return PayTransactionConvert.INSTANCE.convertList(payTransactionMapper.selectListByIds(ids));
    }

    @Override
    public PayTransactionPageBO getTransactionPage(PayTransactionPageDTO payTransactionPageDTO) {
        PayTransactionPageBO payTransactionPage = new PayTransactionPageBO();
        // 查询分页数据
        int offset = (payTransactionPageDTO.getPageNo() - 1) * payTransactionPageDTO.getPageSize();
        payTransactionPage.setList(PayTransactionConvert.INSTANCE.convertList(payTransactionMapper.selectListByPage(
                payTransactionPageDTO.getCreateBeginTime(), payTransactionPageDTO.getCreateEndTime(),
                payTransactionPageDTO.getPaymentBeginTime(), payTransactionPageDTO.getPaymentEndTime(),
                payTransactionPageDTO.getStatus(), payTransactionPageDTO.getHasRefund(),
                payTransactionPageDTO.getPayChannel(), payTransactionPageDTO.getOrderSubject(),
                offset, payTransactionPageDTO.getPageSize())));
        // 查询分页总数
        payTransactionPage.setTotal(payTransactionMapper.selectCountByPage(
                payTransactionPageDTO.getCreateBeginTime(), payTransactionPageDTO.getCreateEndTime(),
                payTransactionPageDTO.getPaymentBeginTime(), payTransactionPageDTO.getPaymentEndTime(),
                payTransactionPageDTO.getStatus(), payTransactionPageDTO.getHasRefund(),
                payTransactionPageDTO.getPayChannel(), payTransactionPageDTO.getOrderSubject()));
        return payTransactionPage;
    }

    @Override // TODO 芋艿，后面去实现
    public CommonResult cancelTransaction() {
        return null;
    }




}
