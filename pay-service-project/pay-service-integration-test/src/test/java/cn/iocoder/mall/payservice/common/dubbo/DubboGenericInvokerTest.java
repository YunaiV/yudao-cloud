package cn.iocoder.mall.payservice.common.dubbo;

import cn.iocoder.common.framework.vo.CommonResult;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.Map;

public class DubboGenericInvokerTest {

    public static void main(String[] args) {
        ApplicationConfig application = new ApplicationConfig();
        application.setName("api-generic-consumer");

        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("nacos://400-infra.server.iocoder.cn:8848?namespace=dev");

        application.setRegistry(registry);

        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        // 弱类型接口名
        reference.setInterface("cn.iocoder.mall.tradeservice.rpc.order.TradeOrderRpc");
        reference.setVersion("1.0.0");
        // 声明为泛化接口
        reference.setGeneric(true);

        reference.setApplication(application);

        // 用com.alibaba.dubbo.rpc.service.GenericService可以替代所有接口引用
        GenericService genericService = reference.get();

        Object result = genericService.$invoke("updateTradeOrderPaySuccess",
                new String[]{String.class.getName(), Integer.class.getName()},
                new Object[]{"1", 100});
        CommonResult<Boolean> commonResult = parseCommonResult((Map<String, Object>) result);
        System.out.println(result);
    }

    private static CommonResult<Boolean> parseCommonResult(Map<String, Object> dubboResult) {
        CommonResult<Boolean> commonResult = new CommonResult<>();
        commonResult.setCode((Integer) dubboResult.get("code"));
        commonResult.setMessage((String) dubboResult.get("message"));
        commonResult.setData((Boolean) dubboResult.get("data"));
        return commonResult;
    }

}
