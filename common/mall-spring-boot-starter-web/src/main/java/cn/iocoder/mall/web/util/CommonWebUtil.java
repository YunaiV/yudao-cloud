package cn.iocoder.mall.web.util;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.web.constant.CommonMallConstants;

import javax.servlet.ServletRequest;
import java.util.Date;

public class CommonWebUtil {

    public static Integer getAccountId(ServletRequest request) {
        return (Integer) request.getAttribute(CommonMallConstants.REQUEST_ATTR_USER_ID_KEY);
    }

    public static void setAccountId(ServletRequest request, Integer userId) {
        request.setAttribute(CommonMallConstants.REQUEST_ATTR_USER_ID_KEY, userId);
    }

    public static CommonResult getCommonResult(ServletRequest request) {
        return (CommonResult) request.getAttribute(CommonMallConstants.REQUEST_ATTR_COMMON_RESULT);
    }

    public static void setCommonResult(ServletRequest request, CommonResult result) {
        request.setAttribute(CommonMallConstants.REQUEST_ATTR_COMMON_RESULT, result);
    }

    public static void setAccessStartTime(ServletRequest request, Date startTime) {
        request.setAttribute(CommonMallConstants.REQUEST_ATTR_ACCESS_START_TIME, startTime);
    }

    public static Date getAccessStartTime(ServletRequest request) {
        return (Date) request.getAttribute(CommonMallConstants.REQUEST_ATTR_ACCESS_START_TIME);
    }

}
