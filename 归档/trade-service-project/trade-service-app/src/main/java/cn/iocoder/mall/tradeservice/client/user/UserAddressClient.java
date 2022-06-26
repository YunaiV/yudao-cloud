package cn.iocoder.mall.tradeservice.client.user;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.userservice.rpc.address.UserAddressFeign;
import cn.iocoder.mall.userservice.rpc.address.dto.UserAddressRespDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserAddressClient {

    @Autowired
    private UserAddressFeign userAddressFeign;
    public UserAddressRespDTO getUserAddress(Integer userAddressId, Integer userId) {
        CommonResult<UserAddressRespDTO> getUserAddressResult = userAddressFeign.getUserAddress(userAddressId);
        getUserAddressResult.checkError();
        if (getUserAddressResult.getData() == null) {
            return null;
        }
        // 如果用户编号不匹配，则返回 null
        return Objects.equals(getUserAddressResult.getData().getUserId(), userId) ?
                getUserAddressResult.getData() : null;
    }

}
