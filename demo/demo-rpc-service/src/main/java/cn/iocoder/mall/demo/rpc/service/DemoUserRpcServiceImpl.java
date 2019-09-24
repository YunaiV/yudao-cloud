package cn.iocoder.mall.demo.rpc.service;

import cn.iocoder.mall.demo.business.api.DemoUserService;
import cn.iocoder.mall.demo.business.bo.user.DemoUserBO;
import cn.iocoder.mall.demo.rpc.api.DemoUserRpcService;
import cn.iocoder.mall.demo.rpc.convert.DemoUserConvert;
import cn.iocoder.mall.demo.rpc.vo.DemoUserVO;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service(validation = "true", version = "${dubbo.provider.DemoUserRpcService.version}")
public class DemoUserRpcServiceImpl implements DemoUserRpcService {

    @Autowired
    private DemoUserService demoUserService;

    @Override
    public DemoUserVO get(Integer id) {
        DemoUserBO userBO = demoUserService.get(id);
        return DemoUserConvert.INSTANCE.convert(userBO);
    }

}
