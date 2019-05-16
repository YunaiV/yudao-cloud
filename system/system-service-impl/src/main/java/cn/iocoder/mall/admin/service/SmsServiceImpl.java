package cn.iocoder.mall.admin.service;

import cn.iocoder.common.framework.constant.DeletedStatusEnum;
import cn.iocoder.mall.admin.api.SmsPlatform;
import cn.iocoder.mall.admin.api.SmsService;
import cn.iocoder.mall.admin.api.bo.sms.SmsSignBO;
import cn.iocoder.mall.admin.api.bo.sms.SmsTemplateBO;
import cn.iocoder.mall.admin.api.constant.AdminErrorCodeEnum;
import cn.iocoder.mall.admin.api.exception.SmsFailException;
import cn.iocoder.mall.admin.convert.SmsSignConvert;
import cn.iocoder.mall.admin.convert.SmsTemplateConvert;
import cn.iocoder.mall.admin.dao.SmsSignMapper;
import cn.iocoder.mall.admin.dao.SmsTemplateMapper;
import cn.iocoder.mall.admin.dataobject.SmsSignDO;
import cn.iocoder.mall.admin.dataobject.SmsTemplateDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 短信
 *
 * @author Sin
 * @time 2019/5/16 10:30 AM
 */
@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.SmsService.version}")
public class SmsServiceImpl implements SmsService {

    @Autowired
    private SmsSignMapper smsSignMapper;
    @Autowired
    private SmsTemplateMapper smsTemplateMapper;

    @Autowired
    @Qualifier("smsYunPianPlatform")
    private SmsPlatform smsPlatform;

    @Override
    @Transactional
    public void createSign(String sign) {

        // 避免重复
        SmsSignDO smsSignDO = smsSignMapper.selectOne(
                new QueryWrapper<SmsSignDO>().eq("sign", sign));

        if (smsSignDO != null) {
            throw new SmsFailException(AdminErrorCodeEnum.SMS_SIGN_IS_EXISTENT.getCode(),
                    AdminErrorCodeEnum.SMS_SIGN_IS_EXISTENT.getMessage());
        }

        // 创建平台 sign
        SmsPlatform.Result result = smsPlatform.createSign(sign);

        // 保存数据库
        smsSignMapper.insert(
                (SmsSignDO) new SmsSignDO()
                        .setSign(sign)
                        .setPlatformId(result.getId())
                        .setApplyStatus(result.getApplyStatus())
                        .setDeleted(DeletedStatusEnum.DELETED_NO.getValue())
                        .setUpdateTime(new Date())
        );
    }

    @Override
    public SmsSignBO getSign(String sign) {
        SmsSignDO smsSignDO = smsSignMapper.selectOne(
                new QueryWrapper<SmsSignDO>().eq("sign", sign));

        if (smsSignDO == null) {
            throw new SmsFailException(AdminErrorCodeEnum.SMS_SIGN_NOT_EXISTENT.getCode(),
                    AdminErrorCodeEnum.SMS_SIGN_NOT_EXISTENT.getMessage());
        }

        return SmsSignConvert.INSTANCE.convert(smsSignDO);
    }

    @Override
    @Transactional
    public void updateSign(String oldSign, String sign) {
        // 避免重复
        SmsSignDO smsSignDO = smsSignMapper.selectOne(
                new QueryWrapper<SmsSignDO>().eq("sign", oldSign));

        if (smsSignDO == null) {
            throw new SmsFailException(AdminErrorCodeEnum.SMS_SIGN_NOT_EXISTENT.getCode(),
                    AdminErrorCodeEnum.SMS_SIGN_NOT_EXISTENT.getMessage());
        }

        // 更新平台
        SmsPlatform.Result result = smsPlatform.updateSign(oldSign, sign);

        // 更新
        smsSignMapper.updateById(
                (SmsSignDO) new SmsSignDO()
                        .setId(smsSignDO.getId())
                        .setPlatformId(result.getId())
                        .setSign(sign)
                        .setApplyStatus(result.getApplyStatus())
                        .setUpdateTime(new Date())
        );
    }

    @Override
    public void createTemplate(Integer smsSignId, String template, Integer tplType) {

        SmsSignDO smsSignDO = smsSignMapper.selectOne(
                new QueryWrapper<SmsSignDO>().eq("id", smsSignId));

        if (smsSignDO == null) {
            throw new SmsFailException(AdminErrorCodeEnum.SMS_SIGN_NOT_EXISTENT.getCode(),
                    AdminErrorCodeEnum.SMS_SIGN_NOT_EXISTENT.getMessage());
        }

        // 调用平台
        SmsPlatform.Result result = smsPlatform
                .createTemplate(smsSignDO.getSign(), template, tplType);

        // 保存数据库
        smsTemplateMapper.insert(
                (SmsTemplateDO) new SmsTemplateDO()
                        .setId(null)
                        .setSmsSignId(smsSignId)
                        .setPlatformId(result.getId())
                        .setTemplate(template)
                        .setApplyStatus(result.getApplyStatus())
                        .setApplyMessage(result.getApplyMessage())
                        .setDeleted(DeletedStatusEnum.DELETED_NO.getValue())
                        .setCreateTime(new Date())
        );
    }

    @Override
    public SmsTemplateBO getTemplate(String id) {
        SmsTemplateDO smsTemplateDO = smsTemplateMapper.selectOne(
                new QueryWrapper<SmsTemplateDO>().eq("id", id));

        if (smsTemplateDO == null) {
            throw new SmsFailException(AdminErrorCodeEnum.SMS_TEMPLATE_NOT_EXISTENT.getCode(),
                    AdminErrorCodeEnum.SMS_TEMPLATE_NOT_EXISTENT.getMessage());
        }

        return SmsTemplateConvert.INSTANCE.convert(smsTemplateDO);
    }

    @Override
    public void updateTemplate(String id, String template, Integer tplType) {
        SmsTemplateDO smsTemplateDO = smsTemplateMapper.selectOne(
                new QueryWrapper<SmsTemplateDO>().eq("id", id));

        if (smsTemplateDO == null) {
            throw new SmsFailException(AdminErrorCodeEnum.SMS_TEMPLATE_NOT_EXISTENT.getCode(),
                    AdminErrorCodeEnum.SMS_TEMPLATE_NOT_EXISTENT.getMessage());
        }

        SmsPlatform.Result result = smsPlatform.updateTemplate(
                smsTemplateDO.getPlatformId(), template, tplType);

        smsTemplateMapper.update(
                (SmsTemplateDO) new SmsTemplateDO()
                        .setApplyStatus(result.getApplyStatus())
                        .setApplyMessage(result.getApplyMessage())
                        .setUpdateTime(new Date()),
                new QueryWrapper<SmsTemplateDO>().eq("id", id)
        );
    }
}
