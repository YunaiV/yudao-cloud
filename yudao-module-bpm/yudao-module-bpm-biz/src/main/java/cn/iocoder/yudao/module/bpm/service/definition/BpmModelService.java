package cn.iocoder.yudao.module.bpm.service.definition;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.bpm.controller.admin.definition.vo.model.BpmModelCreateReqVO;
import cn.iocoder.yudao.module.bpm.controller.admin.definition.vo.model.BpmModelPageReqVO;
import cn.iocoder.yudao.module.bpm.controller.admin.definition.vo.model.BpmModelUpdateReqVO;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.repository.Model;

import javax.validation.Valid;

/**
 * Flowable流程模型接口
 *
 * @author yunlongn
 */
public interface BpmModelService {

    /**
     * 获得流程模型分页
     *
     * @param pageVO 分页查询
     * @return 流程模型分页
     */
    PageResult<Model> getModelPage(BpmModelPageReqVO pageVO);

    /**
     * 创建流程模型
     *
     * @param modelVO 创建信息
     * @param bpmnXml BPMN XML
     * @return 创建的流程模型的编号
     */
    String createModel(@Valid BpmModelCreateReqVO modelVO, String bpmnXml);

    /**
     * 获得流程模块
     *
     * @param id 编号
     * @return 流程模型
     */
    Model getModel(String id);

    /**
     * 获得流程模型的 BPMN XML
     *
     * @param id 编号
     * @return BPMN XML
     */
    byte[] getModelBpmnXML(String id);

    /**
     * 修改流程模型
     *
     * @param updateReqVO 更新信息
     */
    void updateModel(@Valid BpmModelUpdateReqVO updateReqVO);

    /**
     * 将流程模型，部署成一个流程定义
     *
     * @param id 编号
     */
    void deployModel(String id);

    /**
     * 删除模型
     *
     * @param id 编号
     */
    void deleteModel(String id);

    /**
     * 修改模型的状态，实际更新的部署的流程定义的状态
     *
     * @param id    编号
     * @param state 状态
     */
    void updateModelState(String id, Integer state);

    /**
     * 获得流程定义编号对应的 BPMN Model
     *
     * @param processDefinitionId 流程定义编号
     * @return BPMN Model
     */
    BpmnModel getBpmnModelByDefinitionId(String processDefinitionId);

}
