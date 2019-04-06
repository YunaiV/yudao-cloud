package cn.iocoder.mall.user.api;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.user.api.dto.UserAccessLogAddDTO;

public interface UserAccessLogService {

    CommonResult<Boolean> addUserAccessLog(UserAccessLogAddDTO userAccessLogAddDTO);

}