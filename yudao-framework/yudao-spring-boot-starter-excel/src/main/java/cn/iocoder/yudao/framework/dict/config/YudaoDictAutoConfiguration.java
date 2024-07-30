package cn.iocoder.yudao.framework.dict.config;

import cn.hutool.extra.spring.SpringUtil;
import cn.iocoder.yudao.framework.dict.core.DictFrameworkUtils;
import cn.iocoder.yudao.module.system.api.dict.DictDataApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class YudaoDictAutoConfiguration {

    @Bean
    @SuppressWarnings("InstantiationOfUtilityClass")
    public DictFrameworkUtils dictUtils(DictDataApi dictDataApi) {
        // Cloud 专属逻辑：优先使用本地的 dictDataApiImpl 实现类，而不是 Feign 调用
        try {
            DictDataApi apiImpl = SpringUtil.getBean("dictDataApiImpl", DictDataApi.class);
            if (apiImpl != null) {
                dictDataApi =  apiImpl;
            }
        } catch (Exception ignored) {}
        DictFrameworkUtils.init(dictDataApi);
        return new DictFrameworkUtils();
    }

}
