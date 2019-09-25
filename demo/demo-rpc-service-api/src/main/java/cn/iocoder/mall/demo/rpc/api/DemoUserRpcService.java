package cn.iocoder.mall.demo.rpc.api;

import cn.iocoder.mall.demo.rpc.dto.DemoUserDTO;

public interface DemoUserRpcService {

    DemoUserDTO get(Integer id);

}
