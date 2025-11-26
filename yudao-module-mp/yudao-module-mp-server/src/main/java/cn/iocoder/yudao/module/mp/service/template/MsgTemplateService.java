package cn.iocoder.yudao.module.mp.service.template;

import java.util.List;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.mp.controller.admin.template.vo.MsgTemplateBatchReqVO;
import cn.iocoder.yudao.module.mp.controller.admin.template.vo.MsgTemplatePageReqVO;
import cn.iocoder.yudao.module.mp.controller.admin.template.vo.MsgTemplateSaveReqVO;
import cn.iocoder.yudao.module.mp.dal.dataobject.template.MsgTemplateDO;
import jakarta.validation.Valid;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 消息模板 Service 接口
 *
 * @author dengsl
 */
public interface MsgTemplateService {

    /**
     * 创建消息模板
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMsgTemplate(@Valid MsgTemplateSaveReqVO createReqVO);

    /**
     * 更新消息模板
     *
     * @param updateReqVO 更新信息
     */
    void updateMsgTemplate(@Valid MsgTemplateSaveReqVO updateReqVO);

    /**
     * 删除消息模板
     *
     * @param id 编号
     */
    void deleteMsgTemplate(Long id);

    /**
     * 批量删除消息模板
     *
     * @param ids 编号
     */
    void deleteMsgTemplateListByIds(List<Long> ids);

    /**
     * 获得消息模板
     *
     * @param id 编号
     * @return 消息模板
     */
    MsgTemplateDO getMsgTemplate(Long id);

    /**
     * 获得消息模板分页
     *
     * @param pageReqVO 分页查询
     * @return 消息模板分页
     */
    PageResult<MsgTemplateDO> getMsgTemplatePage(MsgTemplatePageReqVO pageReqVO);


    /**
     * 同步公众号已添加的消息模板
     *
     * @throws WxErrorException
     */
    void syncWxTemplate(Long accountId) throws WxErrorException;

    /**
     * 获得公众号已添加的消息模板
     *
     * @param appId 公众号 AppId
     * @return 模板列表
     */
    MsgTemplateDO getWxTemplate(String appId, String templateId);

    /**
     * 批量消息发送
     *
     * @param batchReqVO
     */
    void sendMsgBatch(MsgTemplateBatchReqVO batchReqVO);
}