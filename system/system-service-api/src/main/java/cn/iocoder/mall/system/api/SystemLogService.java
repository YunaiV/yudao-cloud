package cn.iocoder.mall.system.api;

import cn.iocoder.mall.system.api.bo.systemlog.AccessLogPageBO;
import cn.iocoder.mall.system.api.dto.systemlog.AccessLogAddDTO;
import cn.iocoder.mall.system.api.dto.systemlog.AccessLogPageDTO;
import cn.iocoder.mall.system.api.dto.systemlog.ExceptionLogAddDTO;

/**
 * 系统日志 Service 接口
 *
 * 例如说，访问日志、错误日志、操作日志等等
 */
public interface SystemLogService {

    AccessLogPageBO getAccessLogPage(AccessLogPageDTO accessLogPageDTO);

}
