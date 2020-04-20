package cn.iocoder.mall.system.biz.convert;

import cn.iocoder.mall.system.biz.bo.smsTemplate.ListSmsTemplateBO;
import cn.iocoder.mall.system.biz.bo.smsTemplate.SmsTemplateBO;
import cn.iocoder.mall.system.biz.dataobject.sms.SmsSignDO;
import cn.iocoder.mall.system.biz.dataobject.sms.SmsTemplateDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 短信 template
 *
 * @author Sin
 * @time 2019/5/16 7:43 PM
 */
@Mapper
public interface SmsTemplateConvert {

    SmsTemplateConvert INSTANCE = Mappers.getMapper(SmsTemplateConvert.class);

    @Mappings({})
    SmsTemplateBO convert(SmsTemplateDO smsTemplateDO);

    @Mappings({})
    List<ListSmsTemplateBO> convert(List<SmsTemplateDO> smsTemplateDOList);

    @Mappings({})
    List<ListSmsTemplateBO.Sign> convertTemplateSign(List<SmsSignDO> smsSignDOList);
}
