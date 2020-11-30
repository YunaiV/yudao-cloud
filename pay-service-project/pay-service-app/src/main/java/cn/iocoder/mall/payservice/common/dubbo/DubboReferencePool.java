package cn.iocoder.mall.payservice.common.dubbo;

import cn.iocoder.common.framework.util.StringUtils;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.Data;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

@Component
public class DubboReferencePool {

    @Data
    public class ReferenceMeta {

        private final ReferenceConfig config; // TODO 芋艿，后续需要做销毁
        private final GenericService service;
        private final String methodName;

        private ReferenceMeta(ReferenceConfig config, GenericService service, String methodName) {
            this.config = config;
            this.service = service;
            this.methodName = methodName;
        }

    }

    private LoadingCache<String, ReferenceMeta> referenceMetaCache = CacheBuilder.newBuilder()
            .build(new CacheLoader<String, ReferenceMeta>() {
                @Override
                public ReferenceMeta load(String notifyUrl) {
                    return createGenericService(notifyUrl);
                }
            });

    @Value("${dubbo.registry.address}")
    private String dubboRegistryAddress;
    @Value("${dubbo.application.name}")
    private String dubboApplicationName;

    public ReferenceMeta getReferenceMeta(String notifyUrl) {
        DubboReferencePool.ReferenceMeta referenceMeta = referenceMetaCache.getUnchecked(notifyUrl);
        Assert.notNull(referenceMeta, String.format("notifyUrl(%s) 不存在对应的 ReferenceMeta 对象", notifyUrl));
        return referenceMeta;
    }

    private ReferenceMeta createGenericService(String notifyUrl) {
        // 使用 # 号分隔，格式为 服务名#方法名#版本号
        List<String> notifyUrlParts = this.parseNotifyUrl(notifyUrl);
        // 创建 ApplicationConfig 对象
        ApplicationConfig application = new ApplicationConfig();
        application.setName(dubboApplicationName);
        // 创建 RegistryConfig 对象
        RegistryConfig registry = new RegistryConfig();
//        registry.setAddress("zookeeper://127.0.0.1:2181");
        registry.setAddress(dubboRegistryAddress);
        application.setRegistry(registry);
        // 创建 ReferenceConfig 对象
        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setInterface(notifyUrlParts.get(0)); // 弱类型接口名
        reference.setGeneric(true); // 声明为泛化接口
        reference.setApplication(application);
        reference.setVersion(notifyUrlParts.size() > 2 ? notifyUrlParts.get(2) : "1.0.0"); // 如果未配置服务的版本号，则默认使用 1.0.0
        // 获得 GenericService 对象
        GenericService genericService = reference.get();
        // 构建最终的 ReferenceMeta 对象
        return new ReferenceMeta(reference, genericService, notifyUrlParts.get(1));
    }

    // TODO 芋艿，后续重构成一个对象
    private List<String> parseNotifyUrl(String notifyUrl) {
        return StringUtils.split(notifyUrl, "#");
    }

}
