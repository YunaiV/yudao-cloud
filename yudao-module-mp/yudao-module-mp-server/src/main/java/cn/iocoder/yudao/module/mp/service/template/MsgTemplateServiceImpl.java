package cn.iocoder.yudao.module.mp.service.template;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.mp.enums.ErrorCodeConstants.MSG_TEMPLATE_NOT_EXISTS;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import cn.hutool.core.text.CharSequenceUtil;
import cn.iocoder.yudao.module.mp.framework.mp.core.MpServiceFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.github.yulichang.toolkit.MPJWrappers;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.mp.controller.admin.template.vo.MsgTemplateBatchReqVO;
import cn.iocoder.yudao.module.mp.controller.admin.template.vo.MsgTemplateLogSaveReqVO;
import cn.iocoder.yudao.module.mp.controller.admin.template.vo.MsgTemplatePageReqVO;
import cn.iocoder.yudao.module.mp.controller.admin.template.vo.MsgTemplateSaveReqVO;
import cn.iocoder.yudao.module.mp.convert.user.MpUserConvert;
import cn.iocoder.yudao.module.mp.dal.dataobject.account.MpAccountDO;
import cn.iocoder.yudao.module.mp.dal.dataobject.template.MsgTemplateDO;
import cn.iocoder.yudao.module.mp.dal.dataobject.user.MpUserDO;
import cn.iocoder.yudao.module.mp.dal.mysql.template.MsgTemplateMapper;
import cn.iocoder.yudao.module.mp.enums.ErrorCodeConstants;
import cn.iocoder.yudao.module.mp.service.account.MpAccountService;
import cn.iocoder.yudao.module.mp.service.user.MpUserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.template.WxMpTemplate;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

/**
 * 消息模板 Service 实现类
 *
 * @author dengsl
 */
@Service
@Validated
@Slf4j
public class MsgTemplateServiceImpl implements MsgTemplateService {
    @Resource
    @Lazy // 延迟加载，为了解决延迟加载
    private MpServiceFactory mpServiceFactory;
    @Resource
    private MsgTemplateMapper msgTemplateMapper;
    @Resource
    private MpUserService mpUserService;
    @Resource
    private MpAccountService mpAccountService;
    @Resource
    private MsgTemplateLogService msgTemplateLogService;

    @Override
    public Long createMsgTemplate(MsgTemplateSaveReqVO createReqVO) {
        // 插入
        MsgTemplateDO msgTemplate = BeanUtils.toBean(createReqVO, MsgTemplateDO.class);
        msgTemplateMapper.insert(msgTemplate);
        // 返回
        return msgTemplate.getId();
    }

    @Override
    public void updateMsgTemplate(MsgTemplateSaveReqVO updateReqVO) {
        // 校验存在
        validateMsgTemplateExists(updateReqVO.getId());
        // 更新
        MsgTemplateDO updateObj = BeanUtils.toBean(updateReqVO, MsgTemplateDO.class);
        msgTemplateMapper.updateById(updateObj);
    }

    @Override
    public void deleteMsgTemplate(Long id) {
        // 校验存在
        validateMsgTemplateExists(id);
        // 删除
        msgTemplateMapper.deleteById(id);
    }

    @Override
    public void deleteMsgTemplateListByIds(List<Long> ids) {
        // 校验存在
        validateMsgTemplateExists(ids);
        // 删除
        msgTemplateMapper.deleteByIds(ids);
    }

    private void validateMsgTemplateExists(List<Long> ids) {
        List<MsgTemplateDO> list = msgTemplateMapper.selectByIds(ids);
        if (CollUtil.isEmpty(list) || list.size() != ids.size()) {
            throw exception(MSG_TEMPLATE_NOT_EXISTS);
        }
    }

    private void validateMsgTemplateExists(Long id) {
        if (msgTemplateMapper.selectById(id) == null) {
            throw exception(MSG_TEMPLATE_NOT_EXISTS);
        }
    }

    @Override
    public MsgTemplateDO getMsgTemplate(Long id) {
        return msgTemplateMapper.selectById(id);
    }

    @Override
    public PageResult<MsgTemplateDO> getMsgTemplatePage(MsgTemplatePageReqVO pageReqVO) {
        MpAccountDO account = mpAccountService.getAccount(pageReqVO.getAccountId());
        if (account == null) {
            throw exception(ErrorCodeConstants.ACCOUNT_NOT_EXISTS);
        }
        pageReqVO.setAppId(account.getAppId());
        return msgTemplateMapper.selectPage(pageReqVO);
    }

    @Override
    public void syncWxTemplate(Long accountId) throws WxErrorException {
        // 1. 处理新增的模板（在微信中存在但在数据库中不存在）
        // 2. 处理已删除的模板（在数据库中存在但在微信中不存在）
        // 3. 处理已存在的模板（在两边都存在）- 更新
        MpAccountDO mpAccountDO = mpAccountService.getAccount(accountId);
        String appId = mpAccountDO.getAppId();
        List<WxMpTemplate> wxTemplates = mpServiceFactory.getRequiredMpService(appId).getTemplateMsgService().getAllPrivateTemplate();
        if (CollUtil.isNotEmpty(wxTemplates)) {
            List<MsgTemplateDO> dbTemplates = msgTemplateMapper.selectList(new QueryWrapper<MsgTemplateDO>()
                    .lambda().eq(MsgTemplateDO::getAppId, appId));
            // 将微信模板转换为Map，便于查找
            Map<String, WxMpTemplate> wxTemplateMap = wxTemplates.stream()
                    .collect(Collectors.toMap(WxMpTemplate::getTemplateId, Function.identity()));

            // 将数据库模板转换为Map，便于查找
            Map<String, MsgTemplateDO> dbTemplateMap = dbTemplates.stream()
                    .collect(Collectors.toMap(MsgTemplateDO::getTemplateId, Function.identity()));

            // 1. 处理新增的模板（在微信中存在但在数据库中不存在）
            handleNewTemplates(appId, wxTemplateMap, dbTemplateMap);
            // 2. 处理已删除的模板（在数据库中存在但在微信中不存在）
            handleDeletedTemplates(wxTemplateMap, dbTemplateMap);
            // 3. 处理已存在的模板（在两边都存在）- 清空已设置的信息
            handleUpdatedTemplates(wxTemplateMap, dbTemplateMap);
            return;
        }
        log.info("没有模板 appId {} ", appId);
    }

    /**
     * 处理新增的模板
     */
    private void handleNewTemplates(String appId, Map<String, WxMpTemplate> wxTemplateMap,
                                    Map<String, MsgTemplateDO> dbTemplateMap) {
        List<MsgTemplateDO> newTemplates = new ArrayList<>();
        wxTemplateMap.forEach((templateId, wxTemplate) -> {
            if (!dbTemplateMap.containsKey(templateId)) {
                MsgTemplateDO newTemplate = new MsgTemplateDO()
                        .setAppId(appId)
                        .setTemplateId(templateId)
                        .setTitle(wxTemplate.getTitle())
                        .setPrimaryIndustry(wxTemplate.getPrimaryIndustry())
                        .setDeputyIndustry(wxTemplate.getDeputyIndustry())
                        .setContent(wxTemplate.getContent())
                        .setExample(wxTemplate.getExample());
                newTemplates.add(newTemplate);
            }
        });

        if (CollUtil.isNotEmpty(newTemplates)) {
            msgTemplateMapper.insertBatch(newTemplates);
            log.info("批量新增公众号模板: appId={}, count={}", appId, newTemplates.size());
        }
    }

    /**
     * 处理已删除的模板
     */
    private void handleDeletedTemplates(Map<String, WxMpTemplate> wxTemplateMap,
                                        Map<String, MsgTemplateDO> dbTemplateMap) {
        List<MsgTemplateDO> removedTemplates = new ArrayList<>();
        dbTemplateMap.forEach((templateId, dbTemplate) -> {
            if (!wxTemplateMap.containsKey(templateId) && dbTemplate.getIsRemoved() == 0) {
                dbTemplate.setIsRemoved(1);
                removedTemplates.add(dbTemplate);
            }
        });

        if (CollUtil.isNotEmpty(removedTemplates)) {
            msgTemplateMapper.update(new LambdaUpdateWrapper<MsgTemplateDO>()
                    .set(MsgTemplateDO::getIsRemoved, 1)
                    .in(MsgTemplateDO::getId, removedTemplates.stream().map(MsgTemplateDO::getId).collect(Collectors.toList())));
            log.info("批量标记公众号模板为已删除: count={}", removedTemplates.size());
        }
    }

    /**
     * 处理已存在的模板（在两边都存在）- 清空已设置的信息
     */
    private void handleUpdatedTemplates(Map<String, WxMpTemplate> wxTemplateMap,
                                        Map<String, MsgTemplateDO> dbTemplateMap) {
        List<Long> updatedIds = new ArrayList<>();
        dbTemplateMap.forEach((templateId, dbTemplate) -> {
            WxMpTemplate wxTemplate = wxTemplateMap.get(templateId);
            if (wxTemplate != null) {
                // 更新数据库模板信息
                updatedIds.add(dbTemplate.getId());
            }
        });

        if (CollUtil.isNotEmpty(updatedIds)) {
            msgTemplateMapper.update(new LambdaUpdateWrapper<MsgTemplateDO>()
                    .set(MsgTemplateDO::getData, null)
                    .set(MsgTemplateDO::getUrl, null)
                    .set(MsgTemplateDO::getMiniProgramAppId, null)
                    .set(MsgTemplateDO::getMiniProgramPagePath, null)
                    .set(MsgTemplateDO::getExample, null)
                    .in(MsgTemplateDO::getId, updatedIds));
            log.info("批量更新公众号模板: count={}", updatedIds.size());
        }
    }

    @Override
    public MsgTemplateDO getWxTemplate(String appId, String templateId) {
        return msgTemplateMapper.selectOne(new LambdaQueryWrapperX<MsgTemplateDO>()
                        .eq(MsgTemplateDO::getAppId, appId)
                        .eq(CharSequenceUtil.isNotEmpty(templateId), MsgTemplateDO::getTemplateId, templateId));
    }

    @Override
    public void sendMsgBatch(MsgTemplateBatchReqVO batchReqVO) {
        log.info("批量发送模板消息任务开始, 参数：{}", batchReqVO);

        // 获取微信模板信息
        MsgTemplateDO msgTemplateDO = getWxTemplate(batchReqVO.getAppId(), batchReqVO.getTemplateId());
        if (ObjectUtil.isNull(msgTemplateDO)) {
            log.error("未找到对应的模板信息, appId: {}, templateId: {}", batchReqVO.getAppId(), batchReqVO.getTemplateId());
            throw exception(MSG_TEMPLATE_NOT_EXISTS);
        }
        if (msgTemplateDO.getIsRemoved() == 1) {
            throw new ServiceException(GlobalErrorCodeConstants.ERROR_CONFIGURATION.getCode(), "模板未发布");
        }
        if (msgTemplateDO.getStatus() == 1) {
            throw new ServiceException(GlobalErrorCodeConstants.ERROR_CONFIGURATION.getCode(), "模板无效");
        }
        // 构建基础模板消息
        WxMpTemplateMessage.WxMpTemplateMessageBuilder builder = WxMpTemplateMessage.builder()
                .templateId(msgTemplateDO.getTemplateId())
                .url(msgTemplateDO.getUrl())
                .miniProgram(new WxMpTemplateMessage.MiniProgram(
                        msgTemplateDO.getMiniProgramAppId(),
                        msgTemplateDO.getMiniProgramPagePath(),
                        false))
                .data(JSON.parseArray(msgTemplateDO.getData(), WxMpTemplateData.class));

        int currentPage = 1;
        long totalPages = Long.MAX_VALUE;

        while (currentPage <= totalPages) {
            // 按条件查询用户
            batchReqVO.setPageNo(currentPage);
            batchReqVO.setPageSize(500);

            PageResult<MpUserDO> wxUsers = mpUserService.getUserPage(MpUserConvert.INSTANCE.convert(batchReqVO));
            log.info("批量发送模板消息任务, 使用查询条件，处理第{}页，总用户数：{}", currentPage, wxUsers.getTotal());

            // 如果没有用户数据，直接退出循环
            if (CollUtil.isEmpty(wxUsers.getList())) {
                log.warn("当前页无用户数据，结束处理, 当前页：{}", currentPage);
                break;
            }

            // 遍历用户并发送模板消息
            wxUsers.getList().forEach(user -> {
                WxMpTemplateMessage wxMpTemplateMessage = builder.toUser(user.getOpenid()).build();
                try {
                    String result = mpServiceFactory.getRequiredMpService(batchReqVO.getAppId()).getTemplateMsgService().sendTemplateMsg(wxMpTemplateMessage);

                    //保存发送日志
                    MsgTemplateLogSaveReqVO log = new MsgTemplateLogSaveReqVO(wxMpTemplateMessage, batchReqVO.getAppId(), 0, result);
                    msgTemplateLogService.createMsgTemplateLog(log);
                } catch (WxErrorException e) {
                    log.error("发送模板消息失败, 用户OpenId: {}, 错误信息: {}", user.getOpenid(), e.getMessage(), e);
                    // 可以选择继续处理其他用户，而不是直接抛出异常
                    //保存发送日志
                    MsgTemplateLogSaveReqVO log = new MsgTemplateLogSaveReqVO(wxMpTemplateMessage, batchReqVO.getAppId(), 1, e.getMessage());
                    msgTemplateLogService.createMsgTemplateLog(log);
                }
            });

            // 更新分页参数
            currentPage++;
            // 计算总页数
            totalPages = (wxUsers.getTotal() + batchReqVO.getPageSize() - 1) / batchReqVO.getPageSize();
        }

        log.info("批量发送模板消息任务完成");
    }
}