package cn.iocoder.mall.user.api;

import cn.iocoder.mall.user.api.dto.UserAccessLogAddDTO;

public interface UserAccessLogService {

    void addUserAccessLog(UserAccessLogAddDTO userAccessLogAddDTO);

}
