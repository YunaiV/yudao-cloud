package cn.iocoder.yudao.module.mp.controller.admin.template;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants.BAD_REQUEST;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.error;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import java.io.IOException;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.mp.controller.admin.template.vo.MsgTemplateBatchReqVO;
import cn.iocoder.yudao.module.mp.controller.admin.template.vo.MsgTemplatePageReqVO;
import cn.iocoder.yudao.module.mp.controller.admin.template.vo.MsgTemplateRespVO;
import cn.iocoder.yudao.module.mp.controller.admin.template.vo.MsgTemplateSaveReqVO;
import cn.iocoder.yudao.module.mp.dal.dataobject.template.MsgTemplateDO;
import cn.iocoder.yudao.module.mp.service.template.MsgTemplateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import me.chanjar.weixin.common.error.WxErrorException;
/**
 * @author dengsl
 */
@Tag(name = "管理后台 - 消息模板")
@RestController
@RequestMapping("/mp/template")
@Validated
public class MsgTemplateController {

    @Resource
    private MsgTemplateService msgTemplateService;

    @PostMapping("/create")
    @Operation(summary = "创建消息模板")
    @PreAuthorize("@ss.hasPermission('mp:template:create')")
    public CommonResult<Long> createMsgTemplate(@Valid @RequestBody MsgTemplateSaveReqVO createReqVO) {
        return success(msgTemplateService.createMsgTemplate(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新消息模板")
    @PreAuthorize("@ss.hasPermission('mp:template:update')")
    public CommonResult<Boolean> updateMsgTemplate(@Valid @RequestBody MsgTemplateSaveReqVO updateReqVO) {
        msgTemplateService.updateMsgTemplate(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除消息模板")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('mp:template:delete')")
    public CommonResult<Boolean> deleteMsgTemplate(@RequestParam("id") Long id) {
        //msgTemplateService.deleteMsgTemplate(id);
        //TODO 该逻辑没有实现 删除需要判断该消息模板是否被关联
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除消息模板")
    @PreAuthorize("@ss.hasPermission('mp:template:delete')")
    public CommonResult<Boolean> deleteMsgTemplateList(@RequestBody List<Long> ids) {
        //msgTemplateService.deleteMsgTemplateListByIds(ids);
        //TODO 该逻辑没有实现 删除需要判断该消息模板是否被关联
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得消息模板")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('mp:template:query')")
    public CommonResult<MsgTemplateRespVO> getMsgTemplate(@RequestParam("id") Long id) {
        MsgTemplateDO msgTemplate = msgTemplateService.getMsgTemplate(id);
        return success(BeanUtils.toBean(msgTemplate, MsgTemplateRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得消息模板分页")
    @PreAuthorize("@ss.hasPermission('mp:template:query')")
    public CommonResult<PageResult<MsgTemplateRespVO>> getMsgTemplatePage(@Valid MsgTemplatePageReqVO pageReqVO) {
        PageResult<MsgTemplateDO> pageResult = msgTemplateService.getMsgTemplatePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MsgTemplateRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出消息模板 Excel")
    @PreAuthorize("@ss.hasPermission('mp:template:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMsgTemplateExcel(@Valid MsgTemplatePageReqVO pageReqVO,
                                       HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MsgTemplateDO> list = msgTemplateService.getMsgTemplatePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "消息模板.xls", "数据", MsgTemplateRespVO.class,
                BeanUtils.toBean(list, MsgTemplateRespVO.class));
    }

    @GetMapping("/syncMsgTemplate")
    @Operation(summary = "同步公众号模板")
    @PreAuthorize("@ss.hasPermission('mp:template:sync')")
    public CommonResult<Boolean> syncWxTemplate(@RequestParam("accountId") Long accountId) throws WxErrorException {
        msgTemplateService.syncWxTemplate(accountId);
        return success(true);
    }

    /**
     * 批量向用户发送模板消息
     * 通过用户筛选条件（一般使用标签筛选），将消息发送给数据库中所有符合筛选条件的用户
     */
    @PostMapping("/sendMsgBatch")
    @Operation(summary = "批量向用户发送模板消息")
    @PreAuthorize("@ss.hasPermission('mp:template:send')")
    public CommonResult<Boolean> sendMsgBatch(@Valid @RequestBody MsgTemplateBatchReqVO batchReqVO) {
        if (StrUtil.isEmpty(batchReqVO.getOpenid()) && StrUtil.isEmpty(batchReqVO.getUnionId())
                && StrUtil.isEmpty(batchReqVO.getNickname()) && CollUtil.isEmpty((batchReqVO.getOpenidList()))) {
            return error(BAD_REQUEST.getCode(), "请选择用户");
        }
        msgTemplateService.sendMsgBatch(batchReqVO);
        return success(true);
    }
}