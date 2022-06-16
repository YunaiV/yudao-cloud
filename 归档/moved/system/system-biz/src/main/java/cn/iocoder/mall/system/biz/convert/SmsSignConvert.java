package cn.iocoder.mall.system.biz.convert;

import cn.iocoder.mall.system.biz.bo.smsSign.ListSmsSignBO;
import cn.iocoder.mall.system.biz.bo.smsSign.SmsSignBO;
import cn.iocoder.mall.system.biz.dataobject.sms.SmsSignDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 短信 签名
 *
 * @author Sin
 * @time 2019/5/16 6:31 PM
 */
@Mapper
public interface SmsSignConvert {

    SmsSignConvert INSTANCE = Mappers.getMapper(SmsSignConvert.class);

    @Mappings({})
    SmsSignBO convert(SmsSignDO bean);

    @Mappings({})
    List<ListSmsSignBO> convert(List<SmsSignDO> beans);

}
