package cn.iocoder.mall.admin.service;

import cn.iocoder.common.framework.util.StringUtil;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.admin.api.SystemLogService;
import cn.iocoder.mall.admin.api.bo.systemlog.AccessLogBO;
import cn.iocoder.mall.admin.api.bo.systemlog.AccessLogPageBO;
import cn.iocoder.mall.admin.api.dto.systemlog.AccessLogAddDTO;
import cn.iocoder.mall.admin.api.dto.systemlog.AccessLogPageDTO;
import cn.iocoder.mall.admin.api.dto.systemlog.ExceptionLogAddDTO;
import cn.iocoder.mall.admin.convert.AccessLogConvert;
import cn.iocoder.mall.admin.dao.AccessLogMapper;
import cn.iocoder.mall.admin.dao.ExceptionLogMapper;
import cn.iocoder.mall.admin.dataobject.AccessLogDO;
import cn.iocoder.mall.admin.dataobject.ExceptionLogDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.AdminAccessLogService.version}")
public class SystemLogServiceImpl implements SystemLogService {

    /**
     * 请求参数最大长度。
     */
    private static final Integer QUERY_STRING_MAX_LENGTH = 4096;
    /**
     * 请求地址最大长度。
     */
    private static final Integer URI_MAX_LENGTH = 4096;
    /**
     * User-Agent 最大长度。
     */
    private static final Integer USER_AGENT_MAX_LENGTH = 1024;

    @Autowired
    private AccessLogMapper accessLogMapper;
    @Autowired
    private ExceptionLogMapper exceptionLogMapper;

    @Override
    @SuppressWarnings("Duplicates")
    public void addAccessLog(AccessLogAddDTO adminAccessLogAddDTO) {
        // 创建 AdminAccessLogDO
        AccessLogDO accessLog = AccessLogConvert.INSTANCE.convert(adminAccessLogAddDTO);
        accessLog.setCreateTime(new Date());
        // 截取最大长度
        if (accessLog.getUri().length() > URI_MAX_LENGTH) {
            accessLog.setUri(StringUtil.substring(accessLog.getUri(), URI_MAX_LENGTH));
        }
        if (accessLog.getQueryString().length() > QUERY_STRING_MAX_LENGTH) {
            accessLog.setQueryString(StringUtil.substring(accessLog.getQueryString(), QUERY_STRING_MAX_LENGTH));
        }
        if (accessLog.getUserAgent().length() > USER_AGENT_MAX_LENGTH) {
            accessLog.setUserAgent(StringUtil.substring(accessLog.getUserAgent(), USER_AGENT_MAX_LENGTH));
        }
        // 插入
        accessLogMapper.insert(accessLog);
    }

    @Override
    @SuppressWarnings("Duplicates")
    public void addExceptionLog(ExceptionLogAddDTO exceptionLogAddDTO) {
        // 创建 AdminAccessLogDO
        ExceptionLogDO exceptionLog = AccessLogConvert.INSTANCE.convert(exceptionLogAddDTO);
        exceptionLog.setCreateTime(new Date());
        // 截取最大长度
        if (exceptionLog.getUri().length() > URI_MAX_LENGTH) {
            exceptionLog.setUri(StringUtil.substring(exceptionLog.getUri(), URI_MAX_LENGTH));
        }
        if (exceptionLog.getQueryString().length() > QUERY_STRING_MAX_LENGTH) {
            exceptionLog.setQueryString(StringUtil.substring(exceptionLog.getQueryString(), QUERY_STRING_MAX_LENGTH));
        }
        if (exceptionLog.getUserAgent().length() > USER_AGENT_MAX_LENGTH) {
            exceptionLog.setUserAgent(StringUtil.substring(exceptionLog.getUserAgent(), USER_AGENT_MAX_LENGTH));
        }
        // 插入
        exceptionLogMapper.insert(exceptionLog);
    }

    @Override
    @SuppressWarnings("Duplicates")
    public AccessLogPageBO getAccessLogPage(AccessLogPageDTO accessLogPageDTO) {
        AccessLogPageBO accessLogPageBO = new AccessLogPageBO();
        PageResult<AccessLogBO> accessLogPageBOPageResult = AccessLogConvert.INSTANCE.convert(
                accessLogMapper.selectPage(accessLogPageDTO));
        accessLogPageBO.setList(accessLogPageBOPageResult.getList());
        accessLogPageBO.setTotal(accessLogPageBOPageResult.getTotal());
        return accessLogPageBO;
    }

}
