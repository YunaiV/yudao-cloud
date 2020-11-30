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
