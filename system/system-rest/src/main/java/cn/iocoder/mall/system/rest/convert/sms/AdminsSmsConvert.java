package cn.iocoder.mall.system.rest.convert.sms;

import cn.iocoder.mall.system.biz.dto.smsSign.AddSignDTO;
import cn.iocoder.mall.system.biz.dto.smsSign.UpdateSignDTO;
import cn.iocoder.mall.system.biz.dto.smsTemplate.ListSmsTemplateDTO;
import cn.iocoder.mall.system.rest.request.sms.AddSignRequest;
import cn.iocoder.mall.system.rest.request.sms.UpdateSignRequest;
import cn.iocoder.mall.system.rest.request.sms.ListSmsTemplateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * sms admins convert
 *
 * author: sin
 * time: 2020/4/20 11:07 上午
 */
@Mapper
public interface AdminsSmsConvert {

    AdminsSmsConvert INSTANCE = Mappers.getMapper(AdminsSmsConvert.class);

    @Mappings({})
    AddSignDTO convert(AddSignRequest bean);

    @Mappings({})
    UpdateSignDTO convert(UpdateSignRequest bean);

    @Mappings({})
    ListSmsTemplateDTO convert(ListSmsTemplateRequest bean);
}
