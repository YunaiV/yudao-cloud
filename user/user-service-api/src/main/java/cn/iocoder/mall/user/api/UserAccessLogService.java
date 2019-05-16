package cn.iocoder.mall.user.api;

import cn.iocoder.mall.user.api.dto.UserAccessLogAddDTO;

@Deprecated
public interface UserAccessLogService {

    void addUserAccessLog(UserAccessLogAddDTO userAccessLogAddDTO);

}
