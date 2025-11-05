package cn.iocoder.yudao.module.mp.dal.mysql.template;

import org.apache.ibatis.annotations.Mapper;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.mp.controller.admin.template.vo.MsgTemplatePageReqVO;
import cn.iocoder.yudao.module.mp.dal.dataobject.template.MsgTemplateDO;

/**
 * 消息模板 Mapper
 *
 * @author dengsl
 */
@Mapper
public interface MsgTemplateMapper extends BaseMapperX<MsgTemplateDO> {

    default PageResult<MsgTemplateDO> selectPage(MsgTemplatePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MsgTemplateDO>()
                .eqIfPresent(MsgTemplateDO::getAppId, reqVO.getAppId())
                .eqIfPresent(MsgTemplateDO::getTemplateId, reqVO.getTemplateId())
                .likeIfPresent(MsgTemplateDO::getName, reqVO.getName())
                .eqIfPresent(MsgTemplateDO::getTitle, reqVO.getTitle())
                .eqIfPresent(MsgTemplateDO::getContent, reqVO.getContent())
                .eqIfPresent(MsgTemplateDO::getData, reqVO.getData())
                .eqIfPresent(MsgTemplateDO::getUrl, reqVO.getUrl())
                .eqIfPresent(MsgTemplateDO::getMiniProgramAppId, reqVO.getMiniProgramAppId())
                .eqIfPresent(MsgTemplateDO::getMiniProgramPagePath, reqVO.getMiniProgramPagePath())
                .eqIfPresent(MsgTemplateDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(MsgTemplateDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MsgTemplateDO::getId));
    }

}