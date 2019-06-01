package cn.iocoder.mall.admin.convert;

import cn.iocoder.mall.admin.api.bo.sms.PageSmsTemplateBO.Sign;
import cn.iocoder.mall.admin.api.bo.sms.PageSmsTemplateBO.Template;
import cn.iocoder.mall.admin.api.bo.sms.SmsTemplateBO;
import cn.iocoder.mall.admin.dataobject.SmsSignDO;
import cn.iocoder.mall.admin.dataobject.SmsTemplateDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-31T18:10:13+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class SmsTemplateConvertImpl implements SmsTemplateConvert {

    @Override
    public SmsTemplateBO convert(SmsTemplateDO smsTemplateDO) {
        if ( smsTemplateDO == null ) {
            return null;
        }

        SmsTemplateBO smsTemplateBO = new SmsTemplateBO();

        smsTemplateBO.setId( smsTemplateDO.getId() );
        smsTemplateBO.setSmsSignId( smsTemplateDO.getSmsSignId() );
        smsTemplateBO.setTemplate( smsTemplateDO.getTemplate() );
        smsTemplateBO.setApplyStatus( smsTemplateDO.getApplyStatus() );
        smsTemplateBO.setApplyMessage( smsTemplateDO.getApplyMessage() );

        return smsTemplateBO;
    }

    @Override
    public List<Template> convert(List<SmsTemplateDO> smsTemplateDOList) {
        if ( smsTemplateDOList == null ) {
            return null;
        }

        List<Template> list = new ArrayList<Template>( smsTemplateDOList.size() );
        for ( SmsTemplateDO smsTemplateDO : smsTemplateDOList ) {
            list.add( smsTemplateDOToTemplate( smsTemplateDO ) );
        }

        return list;
    }

    @Override
    public List<Sign> convertTemplateSign(List<SmsSignDO> smsSignDOList) {
        if ( smsSignDOList == null ) {
            return null;
        }

        List<Sign> list = new ArrayList<Sign>( smsSignDOList.size() );
        for ( SmsSignDO smsSignDO : smsSignDOList ) {
            list.add( smsSignDOToSign( smsSignDO ) );
        }

        return list;
    }

    protected Template smsTemplateDOToTemplate(SmsTemplateDO smsTemplateDO) {
        if ( smsTemplateDO == null ) {
            return null;
        }

        Template template = new Template();

        template.setId( smsTemplateDO.getId() );
        template.setSmsSignId( smsTemplateDO.getSmsSignId() );
        if ( smsTemplateDO.getPlatform() != null ) {
            template.setPlatform( String.valueOf( smsTemplateDO.getPlatform() ) );
        }
        template.setTemplateCode( smsTemplateDO.getTemplateCode() );
        template.setTemplate( smsTemplateDO.getTemplate() );
        template.setSmsType( smsTemplateDO.getSmsType() );
        template.setApplyStatus( smsTemplateDO.getApplyStatus() );
        template.setApplyMessage( smsTemplateDO.getApplyMessage() );
        template.setUpdateTime( smsTemplateDO.getUpdateTime() );
        template.setCreateTime( smsTemplateDO.getCreateTime() );

        return template;
    }

    protected Sign smsSignDOToSign(SmsSignDO smsSignDO) {
        if ( smsSignDO == null ) {
            return null;
        }

        Sign sign = new Sign();

        sign.setId( smsSignDO.getId() );
        sign.setSign( smsSignDO.getSign() );
        sign.setApplyStatus( smsSignDO.getApplyStatus() );
        sign.setApplyMessage( smsSignDO.getApplyMessage() );

        return sign;
    }
}
