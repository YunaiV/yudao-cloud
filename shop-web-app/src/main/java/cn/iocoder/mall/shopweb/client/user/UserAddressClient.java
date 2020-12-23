package cn.iocoder.mall.shopweb.client.user;

import cn.iocoder.mall.userservice.rpc.address.UserAddressRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class UserAddressClient {

    @DubboReference(version = "${dubbo.consumer.UserAddressRpc.version}")
    private UserAddressRpc userAddressRpc;

    

}
