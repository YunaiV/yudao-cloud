package cn.iocoder.mall.shopweb.client.user;

import cn.iocoder.mall.userservice.rpc.address.UserAddressFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAddressClient {
    @Autowired
    private UserAddressFeign userAddressFeign;

    

}
