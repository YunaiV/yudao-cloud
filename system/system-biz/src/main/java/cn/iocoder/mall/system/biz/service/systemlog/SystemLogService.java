package cn.iocoder.mall.system.biz.service.systemlog;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.system.biz.bo.systemlog.AccessLogBO;
import cn.iocoder.mall.system.biz.dto.system.AccessLogAddDTO;
import cn.iocoder.mall.system.biz.dto.system.AccessLogPageDTO;
import cn.iocoder.mall.system.biz.dto.system.ExceptionLogAddDTO;

public interface SystemLogService {

    void addAccessLog(AccessLogAddDTO accessLogAddDTO);

    void addExceptionLog(ExceptionLogAddDTO exceptionLogAddDTO);

    PageResult<AccessLogBO> getAccessLogPage(AccessLogPageDTO accessLogPageDTO);

}
