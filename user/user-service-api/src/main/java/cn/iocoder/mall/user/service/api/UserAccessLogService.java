package cn.iocoder.mall.user.service.api;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.user.service.api.dto.UserAccessLogAddDTO;

public interface UserAccessLogService {

    CommonResult<Boolean> addUserAccessLog(UserAccessLogAddDTO userAccessLogAddDTO);

}