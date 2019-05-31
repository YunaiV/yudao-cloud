package cn.iocoder.mall.admin.convert;

import cn.iocoder.mall.admin.api.bo.sms.PageSmsSignBO.Sign;
import cn.iocoder.mall.admin.api.bo.sms.SmsSignBO;
import cn.iocoder.mall.admin.dataobject.SmsSignDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-31T17:43:56+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class SmsSignConvertImpl implements SmsSignConvert {

    @Override
    public SmsSignBO convert(SmsSignDO smsSignDO) {
        if ( smsSignDO == null ) {
            return null;
        }

        SmsSignBO smsSignBO = new SmsSignBO();

        smsSignBO.setId( smsSignDO.getId() );
        smsSignBO.setSign( smsSignDO.getSign() );
        smsSignBO.setApplyStatus( smsSignDO.getApplyStatus() );
        smsSignBO.setApplyMessage( smsSignDO.getApplyMessage() );

        return smsSignBO;
    }

    @Override
    public List<Sign> convert(List<SmsSignDO> smsSignDOList) {
        if ( smsSignDOList == null ) {
            return null;
        }

        List<Sign> list = new ArrayList<Sign>( smsSignDOList.size() );
        for ( SmsSignDO smsSignDO : smsSignDOList ) {
            list.add( smsSignDOToSign( smsSignDO ) );
        }

        return list;
    }

    protected Sign smsSignDOToSign(SmsSignDO smsSignDO) {
        if ( smsSignDO == null ) {
            return null;
        }

        Sign sign = new Sign();

        sign.setId( smsSignDO.getId() );
        sign.setPlatform( smsSignDO.getPlatform() );
        sign.setSign( smsSignDO.getSign() );
        sign.setApplyStatus( smsSignDO.getApplyStatus() );
        sign.setApplyMessage( smsSignDO.getApplyMessage() );
        sign.setUpdateTime( smsSignDO.getUpdateTime() );
        sign.setCreateTime( smsSignDO.getCreateTime() );

        return sign;
    }
}
