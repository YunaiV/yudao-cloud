package cn.iocoder.yudao.module.mp.dal.mysql.template;

import org.apache.ibatis.annotations.Mapper;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.mp.controller.admin.template.vo.MsgTemplateLogPageReqVO;
import cn.iocoder.yudao.module.mp.dal.dataobject.template.MsgTemplateLogDO;

/**
 * 微信模版消息发送记录 Mapper
 *
 * @author dengsl
 */
@Mapper
public interface MsgTemplateLogMapper extends BaseMapperX<MsgTemplateLogDO> {

    default PageResult<MsgTemplateLogDO> selectPage(MsgTemplateLogPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MsgTemplateLogDO>()
                .eqIfPresent(MsgTemplateLogDO::getAppId, reqVO.getAppId())
                .eqIfPresent(MsgTemplateLogDO::getToUser, reqVO.getToUser())
                .eqIfPresent(MsgTemplateLogDO::getTemplateId, reqVO.getTemplateId())
                .eqIfPresent(MsgTemplateLogDO::getData, reqVO.getData())
                .eqIfPresent(MsgTemplateLogDO::getUrl, reqVO.getUrl())
                .eqIfPresent(MsgTemplateLogDO::getMiniProgramAppId, reqVO.getMiniProgramAppId())
                .eqIfPresent(MsgTemplateLogDO::getMiniProgramPagePath, reqVO.getMiniProgramPagePath())
                .betweenIfPresent(MsgTemplateLogDO::getSendTime, reqVO.getSendTime())
                .eqIfPresent(MsgTemplateLogDO::getSendStatus, reqVO.getSendStatus())
                .eqIfPresent(MsgTemplateLogDO::getSendResult, reqVO.getSendResult())
                .betweenIfPresent(MsgTemplateLogDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MsgTemplateLogDO::getId));
    }

}