package cn.iocoder.mall.user.biz.service;

import cn.iocoder.common.framework.util.StringUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.user.biz.dao.UserAccessLogMapper;
import cn.iocoder.mall.user.biz.dataobject.UserAccessLogDO;
import cn.iocoder.mall.user.api.UserAccessLogService;
import cn.iocoder.mall.user.api.dto.UserAccessLogAddDTO;
import cn.iocoder.mall.user.biz.convert.UserAccessLogConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@org.apache.dubbo.config.annotation.Service(validation = "true")
public class UserAccessLogServiceImpl implements UserAccessLogService {

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
    private UserAccessLogMapper userAccessLogMapper;

    @Override
    public CommonResult<Boolean> addUserAccessLog(UserAccessLogAddDTO userAccessLogAddDTO) {
        // 创建 UserAccessLogDO
        UserAccessLogDO accessLog = UserAccessLogConvert.INSTANCE.convert(userAccessLogAddDTO);
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
        userAccessLogMapper.insert(accessLog);
        // 返回成功
        return CommonResult.success(true);
    }

}
