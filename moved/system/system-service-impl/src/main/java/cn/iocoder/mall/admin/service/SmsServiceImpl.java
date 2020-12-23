package cn.iocoder.mall.admin.service;

import cn.iocoder.common.framework.enums.DeletedStatusEnum;
import cn.iocoder.common.framework.exception.ServiceException;
import cn.iocoder.mall.system.api.SmsService;
import cn.iocoder.mall.system.api.bo.sms.PageSmsSignBO;
import cn.iocoder.mall.system.api.bo.sms.PageSmsTemplateBO;
import cn.iocoder.mall.system.api.bo.sms.SmsSignBO;
import cn.iocoder.mall.system.api.bo.sms.SmsTemplateBO;
import cn.iocoder.mall.system.api.constant.AdminErrorCodeEnum;
import cn.iocoder.mall.system.api.constant.SmsApplyStatusEnum;
import cn.iocoder.mall.system.api.constant.SmsPlatformEnum;
import cn.iocoder.mall.system.api.dto.sms.PageQuerySmsSignDTO;
import cn.iocoder.mall.system.api.dto.sms.PageQuerySmsTemplateDTO;
import cn.iocoder.mall.admin.client.SmsClient;
import cn.iocoder.mall.admin.convert.SmsSignConvert;
import cn.iocoder.mall.admin.convert.SmsTemplateConvert;
import cn.iocoder.mall.admin.dao.SmsSendMapper;
import cn.iocoder.mall.admin.dao.SmsSignMapper;
import cn.iocoder.mall.admin.dao.SmsTemplateMapper;
import cn.iocoder.mall.admin.dataobject.SmsSendLogDO;
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
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

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
    private SmsSendMapper smsSendMapper;

    @Autowired
    @Qualifier("smsYunPianClient")
    private SmsClient smsYunPianClient;
    @Autowired
    @Qualifier("smsAliYunClient")
    private SmsClient smsAliYunClient;

    @Override
    public PageSmsSignBO pageSmsSign(PageQuerySmsSignDTO queryDTO) {
        QueryWrapper<SmsSignDO> queryWrapper = new QueryWrapper<>();
        if (queryDTO.getApplyStatus() != null) {
            queryWrapper.eq("apply_status", queryDTO.getApplyStatus());
        }
        if (!StringUtils.isEmpty(queryDTO.getSign())) {
            queryWrapper.like("sign", queryDTO.getSign());
        }
        if (!StringUtils.isEmpty(queryDTO.getId())) {
            queryWrapper.eq("id", queryDTO.getId());
        }

        Page<SmsSignDO> page = new Page<SmsSignDO>()
                .setSize(queryDTO.getSize())
                .setCurrent(queryDTO.getCurrent())
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
        if (!StringUtils.isEmpty(queryDTO.getId())) {
            queryWrapper.eq("id", queryDTO.getId());
        }

        Page<SmsTemplateDO> page = new Page<SmsTemplateDO>()
                .setSize(queryDTO.getSize())
                .setCurrent(queryDTO.getCurrent())
                .setDesc("create_time");

        IPage<SmsTemplateDO> signPage = smsTemplateMapper.selectPage(page, queryWrapper);

        List<PageSmsTemplateBO.Template> templateList
                = SmsTemplateConvert.INSTANCE.convert(signPage.getRecords());

        if (CollectionUtils.isEmpty(templateList)) {
            return new PageSmsTemplateBO()
                    .setData(Collections.EMPTY_LIST)
                    .setCurrent(signPage.getCurrent())
                    .setSize(signPage.getSize())
                    .setTotal(signPage.getTotal());
        }

        // 获取 sign

        Set<Integer> smsSignIds = templateList.stream().map(
                PageSmsTemplateBO.Template::getSmsSignId).collect(Collectors.toSet());

        List<SmsSignDO> smsSignDOList = smsSignMapper.selectList(
                new QueryWrapper<SmsSignDO>().in("id", smsSignIds));

        List<PageSmsTemplateBO.Sign> signList = SmsTemplateConvert.INSTANCE.convertTemplateSign(smsSignDOList);

        Map<Integer, PageSmsTemplateBO.Sign> smsSignDOMap = signList
                .stream().collect(Collectors.toMap(PageSmsTemplateBO.Sign::getId, o -> o));

        // 设置 sign

        templateList.forEach(template -> {
            if (smsSignDOMap.containsKey(template.getSmsSignId())) {
                template.setSign(smsSignDOMap.get(template.getSmsSignId()));
            }
        });

        return new PageSmsTemplateBO()
                .setData(templateList)
                .setCurrent(signPage.getCurrent())
                .setSize(signPage.getSize())
                .setTotal(signPage.getTotal());
    }

    @Override
    @Transactional
    public void addSign(String sign, Integer platform) {

        // 避免重复
        SmsSignDO smsSignDO = smsSignMapper.selectOne(
                new QueryWrapper<SmsSignDO>()
                        .eq("platform", platform)
                        .eq("sign", sign)
        );

        if (smsSignDO != null) {
            throw new ServiceException(AdminErrorCodeEnum.SMS_SIGN_IS_EXISTENT.getCode(),
                    AdminErrorCodeEnum.SMS_SIGN_IS_EXISTENT.getMessage());
        }

        // 保存数据库
        smsSignMapper.insert(
                (SmsSignDO) new SmsSignDO()
                        .setSign(sign)
                        .setPlatform(platform)
                        .setApplyStatus(SmsApplyStatusEnum.SUCCESS.getValue())
                        .setDeleted(DeletedStatusEnum.DELETED_NO.getValue())
                        .setUpdateTime(new Date())
                        .setCreateTime(new Date())
        );
    }

    @Override
    public SmsSignBO getSign(Integer signId) {
        SmsSignDO smsSignDO = smsSignMapper.selectOne(
                new QueryWrapper<SmsSignDO>()
                        .eq("id", signId)
                        .eq("deleted", DeletedStatusEnum.DELETED_NO.getValue()));

        if (smsSignDO == null) {
            throw new ServiceException(AdminErrorCodeEnum.SMS_SIGN_NOT_EXISTENT.getCode(),
                    AdminErrorCodeEnum.SMS_SIGN_NOT_EXISTENT.getMessage());
        }

        return SmsSignConvert.INSTANCE.convert(smsSignDO);
    }

    @Override
    @Transactional
    public void updateSign(Integer id, String newSign, Integer platform) {
        // 避免重复
        SmsSignDO smsSignDO = smsSignMapper.selectOne(
                new QueryWrapper<SmsSignDO>()
                        .eq("sign", newSign)
                        .eq("platform", platform));

        if (smsSignDO != null) {
            throw new ServiceException(AdminErrorCodeEnum.SMS_SIGN_IS_EXISTENT.getCode(),
                    AdminErrorCodeEnum.SMS_SIGN_IS_EXISTENT.getMessage());
        }

        // 更新
        smsSignMapper.update(
                (SmsSignDO) new SmsSignDO()
                        .setSign(newSign)
                        .setPlatform(platform)
                        .setUpdateTime(new Date()),
                new QueryWrapper<SmsSignDO>().eq("id", id)
        );
    }

    @Override
    public void deleteSign(Integer id) {
        SmsSignDO smsSignDO = smsSignMapper.selectOne(
                new QueryWrapper<SmsSignDO>()
                        .eq("id", id));

        if (smsSignDO == null) {
            throw new ServiceException(AdminErrorCodeEnum.SMS_SIGN_NOT_EXISTENT.getCode(),
                    AdminErrorCodeEnum.SMS_SIGN_NOT_EXISTENT.getMessage());
        }

        // 更新 deleted 为 YES
        smsSignMapper.delete(new UpdateWrapper<SmsSignDO>()
                .set("deleted", DeletedStatusEnum.DELETED_YES.getName())
                .eq("id", id)
        );
    }

    @Override
    @Transactional
    public void addTemplate(Integer smsSignId, String templateCode,
                            String template, Integer platform, Integer smsType) {

        SmsSignDO smsSignDO = smsSignMapper.selectOne(
                new QueryWrapper<SmsSignDO>().eq("id", smsSignId));

        if (smsSignDO == null) {
            throw new ServiceException(AdminErrorCodeEnum.SMS_SIGN_NOT_EXISTENT.getCode(),
                    AdminErrorCodeEnum.SMS_SIGN_NOT_EXISTENT.getMessage());
        }

        // 保存数据库
        smsTemplateMapper.insert(
                (SmsTemplateDO) new SmsTemplateDO()
                        .setId(null)
                        .setSmsSignId(smsSignId)
                        .setTemplateCode(templateCode)
                        .setTemplate(template)
                        .setPlatform(platform)
                        .setSmsType(smsType)
                        .setApplyStatus(SmsApplyStatusEnum.SUCCESS.getValue())
                        .setApplyMessage("")
                        .setDeleted(DeletedStatusEnum.DELETED_NO.getValue())
                        .setCreateTime(new Date())
        );
    }

    @Override
    public SmsTemplateBO getTemplate(Integer id, Integer platform) {
        SmsTemplateDO smsTemplateDO = smsTemplateMapper.selectOne(
                new QueryWrapper<SmsTemplateDO>()
                        .eq("platform", platform)
                        .eq("id", id));

        if (smsTemplateDO == null) {
            throw new ServiceException(AdminErrorCodeEnum.SMS_TEMPLATE_NOT_EXISTENT.getCode(),
                    AdminErrorCodeEnum.SMS_TEMPLATE_NOT_EXISTENT.getMessage());
        }

        return SmsTemplateConvert.INSTANCE.convert(smsTemplateDO);
    }

    @Override
    @Transactional
    public void updateTemplate(Integer id, Integer smsSignId, String templateCode,
                               String template, Integer platform, Integer smsType) {
        SmsTemplateDO smsTemplateDO = smsTemplateMapper.selectOne(
                new QueryWrapper<SmsTemplateDO>().eq("id", id));

        if (smsTemplateDO == null) {
            throw new ServiceException(AdminErrorCodeEnum.SMS_TEMPLATE_NOT_EXISTENT.getCode(),
                    AdminErrorCodeEnum.SMS_TEMPLATE_NOT_EXISTENT.getMessage());
        }

        SmsSignDO smsSignDO = smsSignMapper.selectOne(
                new QueryWrapper<SmsSignDO>().eq("id", smsTemplateDO.getSmsSignId()));

        if (smsSignDO == null) {
            throw new ServiceException(AdminErrorCodeEnum.SMS_SIGN_NOT_EXISTENT.getCode(),
                    AdminErrorCodeEnum.SMS_SIGN_NOT_EXISTENT.getMessage());
        }

        smsTemplateMapper.update(
                (SmsTemplateDO) new SmsTemplateDO()
                        .setSmsSignId(smsSignId)
                        .setTemplateCode(templateCode)
                        .setTemplate(template)
                        .setPlatform(platform)
                        .setSmsType(smsType)
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

        // 删除 数据库模板
        SmsTemplateDO updateTemplate =new SmsTemplateDO();
        updateTemplate.setDeleted(DeletedStatusEnum.DELETED_YES.getValue());
        smsTemplateMapper.delete(
                new UpdateWrapper<SmsTemplateDO>()
                        .set("deleted", DeletedStatusEnum.DELETED_YES)
                        .eq("id", id));
    }

    @Override
    public void singleSend(String mobile, Integer smsTemplateId, Map<String, String> params) {
        SmsTemplateDO smsTemplateDO = smsTemplateMapper.selectOne(
                new QueryWrapper<SmsTemplateDO>().eq("id", smsTemplateId));

        if (smsTemplateDO == null
                || smsTemplateDO.getDeleted().equals(DeletedStatusEnum.DELETED_YES.getValue())) {
            throw new ServiceException(AdminErrorCodeEnum.SMS_TEMPLATE_NOT_EXISTENT.getCode(),
                    AdminErrorCodeEnum.SMS_TEMPLATE_NOT_EXISTENT.getMessage());
        }

        SmsSignDO smsSignDO = smsSignMapper.selectOne(
                new QueryWrapper<SmsSignDO>().eq("id", smsTemplateDO.getSmsSignId()));

        if (smsSignDO == null) {
            throw new ServiceException(AdminErrorCodeEnum.SMS_SIGN_NOT_EXISTENT.getCode(),
                    AdminErrorCodeEnum.SMS_SIGN_NOT_EXISTENT.getMessage());
        }

        // 获取 client
        SmsClient smsClient = getSmsClient(smsTemplateDO.getPlatform());
        // 发送短信
        SmsClient.SendResult sendResult = smsClient.singleSend(mobile, smsSignDO.getSign(),
                smsTemplateDO.getTemplateCode(), smsTemplateDO.getTemplate(), params);

        // 添加日志
        smsSendMapper.insert(
                (SmsSendLogDO) new SmsSendLogDO()
                        .setTemplateId(smsTemplateDO.getId())
                        .setTemplate(smsTemplateDO.getTemplate())
                        .setMessage(sendResult.getMessage())
                        .setCreateTime(new Date())
        );
    }

    @Override
    public void batchSend(List<String> mobileList, Integer smsTemplateId, Map<String, String> params) {
        SmsTemplateDO smsTemplateDO = smsTemplateMapper.selectOne(
                new QueryWrapper<SmsTemplateDO>().eq("id", smsTemplateId));

        if (smsTemplateDO == null
                || smsTemplateDO.getDeleted().equals(DeletedStatusEnum.DELETED_YES.getValue())) {
            throw new ServiceException(AdminErrorCodeEnum.SMS_TEMPLATE_NOT_EXISTENT.getCode(),
                    AdminErrorCodeEnum.SMS_TEMPLATE_NOT_EXISTENT.getMessage());
        }

        SmsSignDO smsSignDO = smsSignMapper.selectOne(
                new QueryWrapper<SmsSignDO>().eq("id", smsTemplateDO.getSmsSignId()));

        if (smsSignDO == null) {
            // 添加日志
            smsSendMapper.insert(
                    (SmsSendLogDO) new SmsSendLogDO()
                            .setTemplateId(smsTemplateDO.getId())
                            .setTemplate(smsTemplateDO.getTemplate())
                            .setMessage("发送成功!")
                            .setCreateTime(new Date())
            );

            throw new ServiceException(AdminErrorCodeEnum.SMS_SIGN_NOT_EXISTENT.getCode(),
                    AdminErrorCodeEnum.SMS_SIGN_NOT_EXISTENT.getMessage());
        }

        // 获取 client
        SmsClient smsClient = getSmsClient(smsTemplateDO.getPlatform());

        // 发送短信
        SmsClient.SendResult sendResult = smsClient.batchSend(mobileList, smsSignDO.getSign(),
                smsTemplateDO.getTemplateCode(), smsTemplateDO.getTemplate(), params);

        // 添加日志
        smsSendMapper.insert(
                (SmsSendLogDO) new SmsSendLogDO()
                        .setTemplateId(smsTemplateDO.getId())
                        .setTemplate(smsTemplateDO.getTemplate())
                        .setMessage(sendResult.getMessage())
                        .setCreateTime(new Date())
        );
    }

    /**
     * 获取 sms 对于的 client
     *
     * @param platform
     * @return
     */
    private SmsClient getSmsClient(Integer platform) {
        SmsClient smsClient = null;
        if (SmsPlatformEnum.YunPian.getValue().equals(platform)) {
            smsClient = smsYunPianClient;
        } else if (SmsPlatformEnum.AliYun.getValue().equals(platform)) {
            smsClient = smsAliYunClient;
        }

        if (smsClient == null) {
            throw new ServiceException(
                    AdminErrorCodeEnum.SMS_NOT_SEND_CLIENT.getCode(),
                    AdminErrorCodeEnum.SMS_NOT_SEND_CLIENT.getMessage());
        }

        return smsClient;
    }
}
