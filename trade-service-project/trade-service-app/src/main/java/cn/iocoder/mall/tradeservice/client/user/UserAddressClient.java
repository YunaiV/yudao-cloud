package cn.iocoder.mall.tradeservice.client.user;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.userservice.rpc.address.UserAddressRpc;
import cn.iocoder.mall.userservice.rpc.address.dto.UserAddressRespDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserAddressClient {

    @DubboReference(version = "${dubbo.consumer.UserAddressRpc.version}")
    private UserAddressRpc userAddressRpc;

    public UserAddressRespDTO getUserAddress(Integer userAddressId, Integer userId) {
        CommonResult<UserAddressRespDTO> getUserAddressResult = userAddressRpc.getUserAddress(userAddressId);
        getUserAddressResult.checkError();
        if (getUserAddressResult.getData() == null) {
            return null;
        }
        // 如果用户编号不匹配，则返回 null
        return Objects.equals(getUserAddressResult.getData().getUserId(), userId) ?
                getUserAddressResult.getData() : null;
    }

}
