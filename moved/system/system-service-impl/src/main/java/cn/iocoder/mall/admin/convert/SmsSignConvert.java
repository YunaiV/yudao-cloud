package cn.iocoder.mall.admin.convert;

import cn.iocoder.mall.system.api.bo.sms.PageSmsSignBO;
import cn.iocoder.mall.system.api.bo.sms.SmsSignBO;
import cn.iocoder.mall.admin.dataobject.SmsSignDO;
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
    SmsSignBO convert(SmsSignDO smsSignDO);

    @Mappings({})
    List<PageSmsSignBO.Sign> convert(List<SmsSignDO> smsSignDOList);

}
