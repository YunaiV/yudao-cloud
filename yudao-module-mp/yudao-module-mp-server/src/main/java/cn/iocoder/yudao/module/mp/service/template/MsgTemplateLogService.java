package cn.iocoder.yudao.module.mp.service.template;

import java.util.List;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.mp.controller.admin.template.vo.MsgTemplateLogPageReqVO;
import cn.iocoder.yudao.module.mp.controller.admin.template.vo.MsgTemplateLogSaveReqVO;
import cn.iocoder.yudao.module.mp.dal.dataobject.template.MsgTemplateLogDO;
import jakarta.validation.Valid;

/**
 * 微信模版消息发送记录 Service 接口
 *
 * @author dengsl
 */
public interface MsgTemplateLogService {

    /**
     * 创建微信模版消息发送记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMsgTemplateLog(@Valid MsgTemplateLogSaveReqVO createReqVO);

    /**
     * 更新微信模版消息发送记录
     *
     * @param updateReqVO 更新信息
     */
    void updateMsgTemplateLog(@Valid MsgTemplateLogSaveReqVO updateReqVO);

    /**
     * 删除微信模版消息发送记录
     *
     * @param id 编号
     */
    void deleteMsgTemplateLog(Long id);

    /**
    * 批量删除微信模版消息发送记录
    *
    * @param ids 编号
    */
    void deleteMsgTemplateLogListByIds(List<Long> ids);

    /**
     * 获得微信模版消息发送记录
     *
     * @param id 编号
     * @return 微信模版消息发送记录
     */
    MsgTemplateLogDO getMsgTemplateLog(Long id);

    /**
     * 获得微信模版消息发送记录分页
     *
     * @param pageReqVO 分页查询
     * @return 微信模版消息发送记录分页
     */
    PageResult<MsgTemplateLogDO> getMsgTemplateLogPage(MsgTemplateLogPageReqVO pageReqVO);

}