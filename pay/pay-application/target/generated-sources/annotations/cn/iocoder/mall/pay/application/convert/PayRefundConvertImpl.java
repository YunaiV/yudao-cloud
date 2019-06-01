package cn.iocoder.mall.pay.application.convert;

import cn.iocoder.mall.pay.api.bo.refund.PayRefundBO;
import cn.iocoder.mall.pay.application.vo.admins.AdminsPayRefundDetailVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-24T11:47:14+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class PayRefundConvertImpl implements PayRefundConvert {

    @Override
    public List<AdminsPayRefundDetailVO> convertList(List<PayRefundBO> refunds) {
        if ( refunds == null ) {
            return null;
        }

        List<AdminsPayRefundDetailVO> list = new ArrayList<AdminsPayRefundDetailVO>( refunds.size() );
        for ( PayRefundBO payRefundBO : refunds ) {
            list.add( payRefundBOToAdminsPayRefundDetailVO( payRefundBO ) );
        }

        return list;
    }

    protected AdminsPayRefundDetailVO payRefundBOToAdminsPayRefundDetailVO(PayRefundBO payRefundBO) {
        if ( payRefundBO == null ) {
            return null;
        }

        AdminsPayRefundDetailVO adminsPayRefundDetailVO = new AdminsPayRefundDetailVO();

        adminsPayRefundDetailVO.setId( payRefundBO.getId() );
        adminsPayRefundDetailVO.setTransactionId( payRefundBO.getTransactionId() );
        adminsPayRefundDetailVO.setRefundCode( payRefundBO.getRefundCode() );
        adminsPayRefundDetailVO.setAppId( payRefundBO.getAppId() );
        adminsPayRefundDetailVO.setOrderId( payRefundBO.getOrderId() );
        adminsPayRefundDetailVO.setCreateIp( payRefundBO.getCreateIp() );
        adminsPayRefundDetailVO.setOrderDescription( payRefundBO.getOrderDescription() );
        adminsPayRefundDetailVO.setPrice( payRefundBO.getPrice() );
        adminsPayRefundDetailVO.setStatus( payRefundBO.getStatus() );
        adminsPayRefundDetailVO.setFinishTime( payRefundBO.getFinishTime() );
        adminsPayRefundDetailVO.setNotifyUrl( payRefundBO.getNotifyUrl() );
        adminsPayRefundDetailVO.setExtensionData( payRefundBO.getExtensionData() );
        adminsPayRefundDetailVO.setRefundChannel( payRefundBO.getRefundChannel() );
        adminsPayRefundDetailVO.setRefundTime( payRefundBO.getRefundTime() );
        adminsPayRefundDetailVO.setNotifyTime( payRefundBO.getNotifyTime() );
        adminsPayRefundDetailVO.setTradeNo( payRefundBO.getTradeNo() );
        adminsPayRefundDetailVO.setCreateTime( payRefundBO.getCreateTime() );

        return adminsPayRefundDetailVO;
    }
}
