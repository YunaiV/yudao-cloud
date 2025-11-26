package cn.iocoder.yudao.module.mp.service.template;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.mp.enums.ErrorCodeConstants.MSG_TEMPLATE_LOG_NOT_EXISTS;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.mp.controller.admin.template.vo.MsgTemplateLogPageReqVO;
import cn.iocoder.yudao.module.mp.controller.admin.template.vo.MsgTemplateLogSaveReqVO;
import cn.iocoder.yudao.module.mp.dal.dataobject.template.MsgTemplateLogDO;
import cn.iocoder.yudao.module.mp.dal.mysql.template.MsgTemplateLogMapper;
import jakarta.annotation.Resource;

/**
 * 微信模版消息发送记录 Service 实现类
 *
 * @author dengsl
 */
@Service
@Validated
public class MsgTemplateLogServiceImpl implements MsgTemplateLogService {

    @Resource
    private MsgTemplateLogMapper msgTemplateLogMapper;

    @Override
    public Long createMsgTemplateLog(MsgTemplateLogSaveReqVO createReqVO) {
        // 插入
        MsgTemplateLogDO msgTemplateLog = BeanUtils.toBean(createReqVO, MsgTemplateLogDO.class);
        msgTemplateLogMapper.insert(msgTemplateLog);
        // 返回
        return msgTemplateLog.getId();
    }

    @Override
    public void updateMsgTemplateLog(MsgTemplateLogSaveReqVO updateReqVO) {
        // 校验存在
        validateMsgTemplateLogExists(updateReqVO.getId());
        // 更新
        MsgTemplateLogDO updateObj = BeanUtils.toBean(updateReqVO, MsgTemplateLogDO.class);
        msgTemplateLogMapper.updateById(updateObj);
    }

    @Override
    public void deleteMsgTemplateLog(Long id) {
        // 校验存在
        validateMsgTemplateLogExists(id);
        // 删除
        msgTemplateLogMapper.deleteById(id);
    }

    @Override
        public void deleteMsgTemplateLogListByIds(List<Long> ids) {
        // 校验存在
        validateMsgTemplateLogExists(ids);
        // 删除
        msgTemplateLogMapper.deleteByIds(ids);
        }

    private void validateMsgTemplateLogExists(List<Long> ids) {
        List<MsgTemplateLogDO> list = msgTemplateLogMapper.selectByIds(ids);
        if (CollUtil.isEmpty(list) || list.size() != ids.size()) {
            throw exception(MSG_TEMPLATE_LOG_NOT_EXISTS);
        }
    }

    private void validateMsgTemplateLogExists(Long id) {
        if (msgTemplateLogMapper.selectById(id) == null) {
            throw exception(MSG_TEMPLATE_LOG_NOT_EXISTS);
        }
    }

    @Override
    public MsgTemplateLogDO getMsgTemplateLog(Long id) {
        return msgTemplateLogMapper.selectById(id);
    }

    @Override
    public PageResult<MsgTemplateLogDO> getMsgTemplateLogPage(MsgTemplateLogPageReqVO pageReqVO) {
        return msgTemplateLogMapper.selectPage(pageReqVO);
    }

}