package cn.iocoder.mall.demo.rpc.api;

import cn.iocoder.mall.demo.rpc.dto.DemoProductDTO;

public interface DemoProductRpcService {

    DemoProductDTO get(Integer id);

}
