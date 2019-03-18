package cn.iocoder.mall.admin.api;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.dto.AdminAccessLogAddDTO;

/**
 * 管理员访问日志 Service 接口
 */
public interface AdminAccessLogService {

    CommonResult<Boolean> addAdminAccessLog(AdminAccessLogAddDTO adminAccessLogAddDTO);

}
