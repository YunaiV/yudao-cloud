package cn.iocoder.mall.admin.service;

import cn.iocoder.common.framework.constant.DeletedStatusEnum;
import cn.iocoder.common.framework.exception.ServiceException;
import cn.iocoder.mall.admin.api.SmsPlatform;
import cn.iocoder.mall.admin.api.SmsService;
import cn.iocoder.mall.admin.api.bo.sms.SmsSignBO;
import cn.iocoder.mall.admin.api.bo.sms.PageSmsSignBO;
import cn.iocoder.mall.admin.api.bo.sms.SmsTemplateBO;
import cn.iocoder.mall.admin.api.bo.sms.PageSmsTemplateBO;
import cn.iocoder.mall.admin.api.constant.AdminErrorCodeEnum;
import cn.iocoder.mall.admin.api.dto.sms.PageQuerySmsSignDTO;
import cn.iocoder.mall.admin.api.dto.sms.PageQuerySmsTemplateDTO;
import cn.iocoder.mall.admin.convert.SmsSignConvert;
import cn.iocoder.mall.admin.convert.SmsTemplateConvert;
import cn.iocoder.mall.admin.dao.SmsSignMapper;
import cn.iocoder.mall.admin.dao.SmsTemplateMapper;
import cn.iocoder.mall.admin.dataobject.SmsSignDO;
import cn.iocoder.mall.admin.dataobject.SmsTemplateDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 短信
 *
 * @author Sin
 * @time 2019/5/16 10:30 AM
 */
@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.SmsService.version}")
public class SmsServiceImpl implements SmsService {

    private static final String SMS_TEMPLATE = "【%s】%s";

    @Autowired
    private SmsSignMapper smsSignMapper;
    @Autowired
    private SmsTemplateMapper smsTemplateMapper;

    @Autowired
    @Qualifier("smsYunPianPlatform")
    private SmsPlatform smsPlatform;

    @Override
    public PageSmsSignBO pageSmsSign(PageQuerySmsSignDTO queryDTO) {
        QueryWrapper<SmsSignDO> queryWrapper = new QueryWrapper<>();
        if (queryDTO.getApplyStatus() != null) {
            queryWrapper.eq("apply_status", queryDTO.getApplyStatus());
        }
        if (!StringUtils.isEmpty(queryDTO.getSign())) {
            queryWrapper.like("sign", queryDTO.getSign());
        }

        Page<SmsSignDO> page = new Page<SmsSignDO>()
                .setSize(queryDTO.getPageSize())
                .setCurrent(queryDTO.getPageCurrent())
                .setDesc("create_time");

        IPage<SmsSignDO> signPage = smsSignMapper.selectPage(page, queryWrapper);
        List<PageSmsSignBO.Sign> signList = SmsSignConvert.INSTANCE.convert(signPage.getRecords());

        return new PageSmsSignBO()
                .setData(signList)
                .setCurrent(signPage.getCurrent())
                .setSize(signPage.getSize())
                .setTotal(signPage.getTotal());
    }

    @Override
    public PageSmsTemplateBO pageSmsTemplate(PageQuerySmsTemplateDTO queryDTO) {
        QueryWrapper<SmsTemplateDO> queryWrapper = new QueryWrapper<>();
        if (queryDTO.getApplyStatus() != null) {
            queryWrapper.eq("apply_status", queryDTO.getApplyStatus());
        }
        if (queryDTO.getSmsSignId() != null) {
            queryWrapper.eq("sms_sign_id", queryDTO.getSmsSignId());
        }
        if (!StringUtils.isEmpty(queryDTO.getTemplate())) {
            queryWrapper.like("template", queryDTO.getTemplate());
        }

        Page<SmsTemplateDO> page = new Page<SmsTemplateDO>()
                .setSize(queryDTO.getSize())
                .setCurrent(queryDTO.getCurrent())
                .setDesc("create_time");

        IPage<SmsTemplateDO> signPage = smsTemplateMapper.selectPage(page, queryWrapper);
        List<PageSmsTemplateBO.Template> templateList
                = SmsTemplateConvert.INSTANCE.convert(signPage.getRecords());

        return new PageSmsTemplateBO()
                .setData(templateList)
                .setCurrent(signPage.getCurrent())
                .setSize(signPage.getSize())
                .setTotal(signPage.getTotal());
    }

    @Override
    @Transactional
    public void createSign(String sign) {

        // 避免重复
        SmsSignDO smsSignDO = smsSignMapper.selectOne(
                new QueryWrapper<SmsSignDO>().eq("sign", sign));

        if (smsSignDO != null) {
            throw new ServiceException(AdminErrorCodeEnum.SMS_SIGN_IS_EXISTENT.getCode(),
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
                        .setCreateTime(new Date())
        );
    }

    @Override
    public SmsSignBO getSign(String sign) {
        SmsSignDO smsSignDO = smsSignMapper.selectOne(
                new QueryWrapper<SmsSignDO>().eq("sign", sign));

        if (smsSignDO == null) {
            throw new ServiceException(AdminErrorCodeEnum.SMS_SIGN_NOT_EXISTENT.getCode(),
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
            throw new ServiceException(AdminErrorCodeEnum.SMS_SIGN_NOT_EXISTENT.getCode(),
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
    @Transactional
    public void createTemplate(Integer smsSignId, String template, Integer tplType) {

        SmsSignDO smsSignDO = smsSignMapper.selectOne(
                new QueryWrapper<SmsSignDO>().eq("id", smsSignId));

        if (smsSignDO == null) {
            throw new ServiceException(AdminErrorCodeEnum.SMS_SIGN_NOT_EXISTENT.getCode(),
                    AdminErrorCodeEnum.SMS_SIGN_NOT_EXISTENT.getMessage());
        }

        // 调用平台
        SmsPlatform.Result result = smsPlatform
                .createTemplate(String.format(SMS_TEMPLATE, smsSignDO.getSign(), template), tplType);

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
    public SmsTemplateBO getTemplate(Integer id) {
        SmsTemplateDO smsTemplateDO = smsTemplateMapper.selectOne(
                new QueryWrapper<SmsTemplateDO>().eq("id", id));

        if (smsTemplateDO == null) {
            throw new ServiceException(AdminErrorCodeEnum.SMS_TEMPLATE_NOT_EXISTENT.getCode(),
                    AdminErrorCodeEnum.SMS_TEMPLATE_NOT_EXISTENT.getMessage());
        }

        return SmsTemplateConvert.INSTANCE.convert(smsTemplateDO);
    }

    @Override
    @Transactional
    public void updateTemplate(Integer id, String template, Integer tplType) {
        SmsTemplateDO smsTemplateDO = smsTemplateMapper.selectOne(
                new QueryWrapper<SmsTemplateDO>().eq("id", id));

        if (smsTemplateDO == null) {
            throw new ServiceException(AdminErrorCodeEnum.SMS_TEMPLATE_NOT_EXISTENT.getCode(),
                    AdminErrorCodeEnum.SMS_TEMPLATE_NOT_EXISTENT.getMessage());
        }

        SmsSignDO smsSignDO = smsSignMapper.selectOne(
                new QueryWrapper<SmsSignDO>().eq("id", smsTemplateDO.getSmsSignId()));


        SmsPlatform.Result result = smsPlatform.updateTemplate(
                smsTemplateDO.getPlatformId(),
                String.format(SMS_TEMPLATE, smsSignDO.getSign(), template), tplType);

        smsTemplateMapper.update(
                (SmsTemplateDO) new SmsTemplateDO()
                        .setTemplate(template)
                        .setApplyStatus(result.getApplyStatus())
                        .setApplyMessage(result.getApplyMessage())
                        .setUpdateTime(new Date()),
                new QueryWrapper<SmsTemplateDO>().eq("id", id)
        );
    }

    @Override
    @Transactional
    public void deleteTemplate(Integer id) {
        SmsTemplateDO smsTemplateDO = smsTemplateMapper.selectOne(
                new QueryWrapper<SmsTemplateDO>().eq("id", id));

        if (smsTemplateDO == null
                || smsTemplateDO.getDeleted().equals(DeletedStatusEnum.DELETED_YES.getValue())) {
            throw new ServiceException(AdminErrorCodeEnum.SMS_TEMPLATE_NOT_EXISTENT.getCode(),
                    AdminErrorCodeEnum.SMS_TEMPLATE_NOT_EXISTENT.getMessage());
        }

        // 删除 平台的模板
        smsPlatform.deleteTemplate(smsTemplateDO.getPlatformId());

        // 删除 数据库模板
        SmsTemplateDO updateTemplate =new SmsTemplateDO();
        updateTemplate.setDeleted(DeletedStatusEnum.DELETED_YES.getValue());
        smsTemplateMapper.delete(new UpdateWrapper<SmsTemplateDO>()
                .set("deleted", DeletedStatusEnum.DELETED_YES).eq("id", id));
    }

    @Override
    public void singleSend(String mobile, Integer smsTemplateId, Map<String, String> params) {

        // TODO: 2019/5/21 Sin params 参数为特换到模板中
        SmsTemplateDO smsTemplateDO = smsTemplateMapper.selectOne(
                new QueryWrapper<SmsTemplateDO>().eq("id", smsTemplateId));

        if (smsTemplateDO == null
                || smsTemplateDO.getDeleted().equals(DeletedStatusEnum.DELETED_YES.getValue())) {
            throw new ServiceException(AdminErrorCodeEnum.SMS_TEMPLATE_NOT_EXISTENT.getCode(),
                    AdminErrorCodeEnum.SMS_TEMPLATE_NOT_EXISTENT.getMessage());
        }

        SmsSignDO smsSignDO = smsSignMapper.selectOne(
                new QueryWrapper<SmsSignDO>().eq("id", smsTemplateDO.getSmsSignId()));

        smsPlatform.singleSend(mobile,
                String.format(SMS_TEMPLATE, smsSignDO.getSign(), smsTemplateDO.getTemplate()));
    }

    @Override
    public void batchSend(List<String> mobileList, Integer smsTemplateId, Map<String, String> params) {
        // TODO: 2019/5/21 Sin params 参数为特换到模板中

        SmsTemplateDO smsTemplateDO = smsTemplateMapper.selectOne(
                new QueryWrapper<SmsTemplateDO>().eq("id", smsTemplateId));

        if (smsTemplateDO == null
                || smsTemplateDO.getDeleted().equals(DeletedStatusEnum.DELETED_YES.getValue())) {
            throw new ServiceException(AdminErrorCodeEnum.SMS_TEMPLATE_NOT_EXISTENT.getCode(),
                    AdminErrorCodeEnum.SMS_TEMPLATE_NOT_EXISTENT.getMessage());
        }

        SmsSignDO smsSignDO = smsSignMapper.selectOne(
                new QueryWrapper<SmsSignDO>().eq("id", smsTemplateDO.getSmsSignId()));

        smsPlatform.batchSend(mobileList,
                String.format(SMS_TEMPLATE, smsSignDO.getSign(), smsTemplateDO.getTemplate()));
    }
}
