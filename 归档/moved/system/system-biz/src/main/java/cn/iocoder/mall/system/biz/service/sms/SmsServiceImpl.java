package cn.iocoder.mall.system.biz.service.sms;

import cn.iocoder.common.framework.exception.ServiceException;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.mybatis.core.enums.DeletedStatusEnum;
import cn.iocoder.mall.system.biz.bo.smsSign.ListSmsSignBO;
import cn.iocoder.mall.system.biz.bo.smsSign.SmsSignBO;
import cn.iocoder.mall.system.biz.bo.smsTemplate.ListSmsTemplateBO;
import cn.iocoder.mall.system.biz.bo.smsTemplate.SmsTemplateBO;
import cn.iocoder.mall.system.biz.convert.SmsSignConvert;
import cn.iocoder.mall.system.biz.convert.SmsTemplateConvert;
import cn.iocoder.mall.system.biz.dao.sms.SmsSendMapper;
import cn.iocoder.mall.system.biz.dao.sms.SmsSignMapper;
import cn.iocoder.mall.system.biz.dao.sms.SmsTemplateMapper;
import cn.iocoder.mall.system.biz.dataobject.sms.SmsSendLogDO;
import cn.iocoder.mall.system.biz.dataobject.sms.SmsSignDO;
import cn.iocoder.mall.system.biz.dataobject.sms.SmsTemplateDO;
import cn.iocoder.mall.system.biz.dto.smsSign.AddSignDTO;
import cn.iocoder.mall.system.biz.dto.smsSign.ListSmsSignDTO;
import cn.iocoder.mall.system.biz.dto.smsSign.UpdateSignDTO;
import cn.iocoder.mall.system.biz.dto.smsTemplate.ListSmsTemplateDTO;
import cn.iocoder.mall.system.biz.enums.SystemErrorCodeEnum;
import cn.iocoder.mall.system.biz.enums.sms.SmsApplyStatusEnum;
import cn.iocoder.mall.system.biz.enums.sms.SmsPlatformEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 短信
 *
 * @author Sin
 * @time 2019/5/16 10:30 AM
 */
@Service
public class SmsServiceImpl implements SmsService {

    @Autowired
    private SmsSignMapper smsSignMapper;
    @Autowired
    private SmsTemplateMapper smsTemplateMapper;
    @Autowired
    private SmsSendMapper smsSendMapper;

    @Autowired
    @Qualifier("yunPianSmsClient")
    private SmsClient smsYunPianClient;
    @Autowired
    @Qualifier("aliYunSmsClient")
    private SmsClient smsAliYunClient;

    @Override
    public PageResult<ListSmsSignBO> listSmsSign(ListSmsSignDTO queryDTO) {
        // TODO DOME FROM 芋艿 to 小范：mybatis-plus 的 QueryWrapper 不要到 Service 层，可以抽 Dao 方法里哈。其它的类似可以瞅瞅噢
        IPage<SmsSignDO> signPage = smsSignMapper.listSmsSign(queryDTO);
        List<ListSmsSignBO> signList = SmsSignConvert.INSTANCE.convert(signPage.getRecords());
        return new PageResult<ListSmsSignBO>().setList(signList).setTotal(signPage.getTotal());
    }

    @Override
    public PageResult<ListSmsTemplateBO> listSmsTemplate(ListSmsTemplateDTO listSmsTemplateDTO) {
        // 获取 template
        IPage<SmsTemplateDO> signPage = smsTemplateMapper.listSmsTemplate(listSmsTemplateDTO);
        if (CollectionUtils.isEmpty(signPage.getRecords())) {
            // TODO DOME FROM 芋艿 to 小范，Collections.EMPTY_LIST =》Collections.emptyList();另外，可以考虑直接 Convert 哈
            return new PageResult<ListSmsTemplateBO>().setList(Collections.emptyList()).setTotal(signPage.getTotal());
        }
        // 转换bo
        List<ListSmsTemplateBO> templateList = SmsTemplateConvert.INSTANCE.convert(signPage.getRecords());
        // 获取 sign
        Set<Integer> smsSignIds = templateList.stream().map(
                ListSmsTemplateBO::getSmsSignId).collect(Collectors.toSet());

        List<SmsSignDO> smsSignDOList = smsSignMapper.selectList(
                new QueryWrapper<SmsSignDO>().in("id", smsSignIds));

        List<ListSmsTemplateBO.Sign> signList = SmsTemplateConvert.INSTANCE.convertTemplateSign(smsSignDOList);
        // sign 转换为 map
        Map<Integer, ListSmsTemplateBO.Sign> smsSignDOMap = signList
                .stream().collect(Collectors.toMap(ListSmsTemplateBO.Sign::getId, o -> o));
        // 设置 sign
        templateList.forEach(template -> {
            if (smsSignDOMap.containsKey(template.getSmsSignId())) {
                template.setSign(smsSignDOMap.get(template.getSmsSignId()));
            }
        });
        return new PageResult<ListSmsTemplateBO>().setList(templateList).setTotal(signPage.getTotal());
    }

    @Override
    @Transactional
    public void addSign(AddSignDTO addSignDTO) {
        // 避免重复
        SmsSignDO smsSignDO = smsSignMapper.selectOne(
                new QueryWrapper<SmsSignDO>()
                        .eq("platform", addSignDTO.getPlatform())
                        .eq("sign", addSignDTO.getSign())
        );
        // 处理 null 情况
        if (smsSignDO != null) {
            // TODO DOME FROM 芋艿 to 小范：可以使用 ServiceExceptionUtil.exception(SystemErrorCodeEnum.SMS_SIGN_IS_EXISTENT);
            throw ServiceExceptionUtil.exception(SystemErrorCodeEnum.SMS_SIGN_IS_EXISTENT);
        }
        // 保存数据库
        smsSignMapper.insert(
                (SmsSignDO) new SmsSignDO()
                        .setSign(addSignDTO.getSign())
                        .setPlatform(addSignDTO.getPlatform())
                        .setApplyStatus(SmsApplyStatusEnum.SUCCESS.getValue())
                        .setDeleted(DeletedStatusEnum.DELETED_NO.getValue())
                        .setUpdateTime(new Date())
                        .setCreateTime(new Date())
        );
    }

    @Override
    public SmsSignBO getSign(Integer signId) {
        // 查询数据库
        SmsSignDO smsSignDO = smsSignMapper.selectOne(
                new QueryWrapper<SmsSignDO>()
                        .eq("id", signId)
                        .eq("deleted", DeletedStatusEnum.DELETED_NO.getValue()));
        // 处理 null
        if (smsSignDO == null) {
            throw new ServiceException(SystemErrorCodeEnum.SMS_SIGN_NOT_EXISTENT.getCode(),
                    SystemErrorCodeEnum.SMS_SIGN_NOT_EXISTENT.getMessage());
        }
        // 转换vo返回
        return SmsSignConvert.INSTANCE.convert(smsSignDO);
    }

    @Override
    @Transactional
    public void updateSign(UpdateSignDTO updateSignDTO) {
        // 避免重复
        SmsSignDO smsSignDO = smsSignMapper.selectOne(
                new QueryWrapper<SmsSignDO>()
                        .eq("sign", updateSignDTO.getSign())
                        .eq("platform", updateSignDTO.getPlatform()));
        // 处理 null
        if (smsSignDO != null) {
            throw new ServiceException(SystemErrorCodeEnum.SMS_SIGN_IS_EXISTENT.getCode(),
                    SystemErrorCodeEnum.SMS_SIGN_IS_EXISTENT.getMessage());
        }
        // 更新
        smsSignMapper.update(
                (SmsSignDO) new SmsSignDO()
                        .setSign(updateSignDTO.getSign())
                        .setPlatform(updateSignDTO.getPlatform())
                        .setUpdateTime(new Date()),
                new QueryWrapper<SmsSignDO>().eq("id", updateSignDTO.getId())
        );
    }

    @Override
    public void deleteSign(Integer id) {
        // 根据id查询
        SmsSignDO smsSignDO = smsSignMapper.selectById(id);
        // 处理 null
        if (smsSignDO == null) {
            throw new ServiceException(SystemErrorCodeEnum.SMS_SIGN_NOT_EXISTENT.getCode(),
                    SystemErrorCodeEnum.SMS_SIGN_NOT_EXISTENT.getMessage());
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
        // 根据id查询
        SmsSignDO smsSignDO = smsSignMapper.selectById(smsSignId);
        // 处理 null
        if (smsSignDO == null) {
            throw new ServiceException(SystemErrorCodeEnum.SMS_SIGN_NOT_EXISTENT.getCode(),
                    SystemErrorCodeEnum.SMS_SIGN_NOT_EXISTENT.getMessage());
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
        // 获取数据
        SmsTemplateDO smsTemplateDO = smsTemplateMapper.selectOne(
                new QueryWrapper<SmsTemplateDO>()
                        .eq("platform", platform)
                        .eq("id", id));
        // 处理 null
        if (smsTemplateDO == null) {
            throw new ServiceException(SystemErrorCodeEnum.SMS_TEMPLATE_NOT_EXISTENT.getCode(),
                    SystemErrorCodeEnum.SMS_TEMPLATE_NOT_EXISTENT.getMessage());
        }
        // 转换bo返回
        return SmsTemplateConvert.INSTANCE.convert(smsTemplateDO);
    }

    @Override
    @Transactional
    public void updateTemplate(Integer id, Integer smsSignId, String templateCode,
                               String template, Integer platform, Integer smsType) {
        // 获取 template
        SmsTemplateDO smsTemplateDO = smsTemplateMapper.selectById(id);
        if (smsTemplateDO == null) {
            throw new ServiceException(SystemErrorCodeEnum.SMS_TEMPLATE_NOT_EXISTENT.getCode(),
                    SystemErrorCodeEnum.SMS_TEMPLATE_NOT_EXISTENT.getMessage());
        }
        // 获取 sign
        SmsSignDO smsSignDO = smsSignMapper.selectOne(
                new QueryWrapper<SmsSignDO>().eq("id", smsTemplateDO.getSmsSignId()));
        if (smsSignDO == null) {
            throw new ServiceException(SystemErrorCodeEnum.SMS_SIGN_NOT_EXISTENT.getCode(),
                    SystemErrorCodeEnum.SMS_SIGN_NOT_EXISTENT.getMessage());
        }
        // 更新数据库
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
        // 处理不存在情况
        if (smsTemplateDO == null
                || smsTemplateDO.getDeleted().equals(DeletedStatusEnum.DELETED_YES.getValue())) {
            throw new ServiceException(SystemErrorCodeEnum.SMS_TEMPLATE_NOT_EXISTENT.getCode(),
                    SystemErrorCodeEnum.SMS_TEMPLATE_NOT_EXISTENT.getMessage());
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
        // 获取 template
        SmsTemplateDO smsTemplateDO = smsTemplateMapper.selectById(smsTemplateId);
        if (smsTemplateDO == null
                || smsTemplateDO.getDeleted().equals(DeletedStatusEnum.DELETED_YES.getValue())) {
            throw new ServiceException(SystemErrorCodeEnum.SMS_TEMPLATE_NOT_EXISTENT.getCode(),
                    SystemErrorCodeEnum.SMS_TEMPLATE_NOT_EXISTENT.getMessage());
        }
        // 获取 sign
        SmsSignDO smsSignDO = smsSignMapper.selectById(smsTemplateDO.getSmsSignId());
        if (smsSignDO == null) {
            throw new ServiceException(SystemErrorCodeEnum.SMS_SIGN_NOT_EXISTENT.getCode(),
                    SystemErrorCodeEnum.SMS_SIGN_NOT_EXISTENT.getMessage());
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
        // 获取 template
        SmsTemplateDO smsTemplateDO = smsTemplateMapper.selectById(smsTemplateId);
        if (smsTemplateDO == null
                || smsTemplateDO.getDeleted().equals(DeletedStatusEnum.DELETED_YES.getValue())) {
            throw new ServiceException(SystemErrorCodeEnum.SMS_TEMPLATE_NOT_EXISTENT.getCode(),
                    SystemErrorCodeEnum.SMS_TEMPLATE_NOT_EXISTENT.getMessage());
        }
        // 获取 sign
        SmsSignDO smsSignDO = smsSignMapper.selectById(smsTemplateDO.getSmsSignId());
        if (smsSignDO == null) {
            // 添加日志
            smsSendMapper.insert(
                    (SmsSendLogDO) new SmsSendLogDO()
                            .setTemplateId(smsTemplateDO.getId())
                            .setTemplate(smsTemplateDO.getTemplate())
                            .setMessage("发送成功!")
                            .setCreateTime(new Date())
            );

            throw new ServiceException(SystemErrorCodeEnum.SMS_SIGN_NOT_EXISTENT.getCode(),
                    SystemErrorCodeEnum.SMS_SIGN_NOT_EXISTENT.getMessage());
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
        // 阿里云和云片
        if (SmsPlatformEnum.YunPian.getValue().equals(platform)) {
            smsClient = smsYunPianClient;
        } else if (SmsPlatformEnum.AliYun.getValue().equals(platform)) {
            smsClient = smsAliYunClient;
        }
        // 没有支持的平台
        if (smsClient == null) {
            throw new ServiceException(
                    SystemErrorCodeEnum.SMS_NOT_SEND_CLIENT.getCode(),
                    SystemErrorCodeEnum.SMS_NOT_SEND_CLIENT.getMessage());
        }
        return smsClient;
    }
}
