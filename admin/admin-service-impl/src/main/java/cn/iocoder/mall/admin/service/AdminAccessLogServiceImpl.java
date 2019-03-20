package cn.iocoder.mall.admin.service;

import cn.iocoder.common.framework.util.StringUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.AdminAccessLogService;
import cn.iocoder.mall.admin.api.dto.AdminAccessLogAddDTO;
import cn.iocoder.mall.admin.convert.AdminAccessLogConvert;
import cn.iocoder.mall.admin.dao.AdminAccessLogMapper;
import cn.iocoder.mall.admin.dataobject.AdminAccessLogDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@com.alibaba.dubbo.config.annotation.Service(validation = "true")
public class AdminAccessLogServiceImpl implements AdminAccessLogService {

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
    private AdminAccessLogMapper adminAccessLogMapper;

    @Override
    public CommonResult<Boolean> addAdminAccessLog(AdminAccessLogAddDTO adminAccessLogAddDTO) {
        // 创建 AdminAccessLogDO
        AdminAccessLogDO accessLog = AdminAccessLogConvert.INSTANCE.convert(adminAccessLogAddDTO);
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
        adminAccessLogMapper.insert(accessLog);
        // 返回成功
        return CommonResult.success(true);
    }

}