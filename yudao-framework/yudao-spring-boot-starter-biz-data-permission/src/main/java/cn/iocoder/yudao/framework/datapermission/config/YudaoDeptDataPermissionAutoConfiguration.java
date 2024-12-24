package cn.iocoder.yudao.framework.datapermission.config;

import cn.hutool.extra.spring.SpringUtil;
import cn.iocoder.yudao.framework.datapermission.core.rule.dept.DeptDataPermissionRule;
import cn.iocoder.yudao.framework.datapermission.core.rule.dept.DeptDataPermissionRuleCustomizer;
import cn.iocoder.yudao.framework.datapermission.core.rule.dept_new.CommonDataPermissionRule;
import cn.iocoder.yudao.framework.datapermission.core.rule.dept_new.CommonDataPermissionRuleCustomizer;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.module.system.api.permission.PermissionApi;
import cn.iocoder.yudao.module.system.api.tenant.TenantApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * 基于部门的数据权限 AutoConfiguration
 *
 * @author 芋道源码
 */
@AutoConfiguration
@ConditionalOnClass(LoginUser.class)
// @ConditionalOnBean(value = DeptDataPermissionRuleCustomizer.class)
public class YudaoDeptDataPermissionAutoConfiguration {

    @Bean
    @ConditionalOnBean(value = {DeptDataPermissionRuleCustomizer.class})
    public DeptDataPermissionRule deptDataPermissionRule(PermissionApi permissionApi,
                                                         List<DeptDataPermissionRuleCustomizer> customizers) {
        // Cloud 专属逻辑：优先使用本地的 PermissionApi 实现类，而不是 Feign 调用
        // 原因：在创建租户时，租户还没创建好，导致 Feign 调用获取数据权限时，报“租户不存在”的错误
        try {
            PermissionApi permissionApiImpl = SpringUtil.getBean("permissionApiImpl", PermissionApi.class);
            if (permissionApiImpl != null) {
                permissionApi = permissionApiImpl;
            }
        } catch (Exception ignored) {}

        // 创建 DeptDataPermissionRule 对象
        DeptDataPermissionRule rule = new DeptDataPermissionRule(permissionApi);
        // 补全表配置
        customizers.forEach(customizer -> customizer.customize(rule));
        return rule;
    }


    @Bean
    @ConditionalOnBean(value = {CommonDataPermissionRuleCustomizer.class})
    public CommonDataPermissionRule deptDataPermissionRuleTwo(PermissionApi permissionApi,
                                                              List<CommonDataPermissionRuleCustomizer> customizers) {
        // Cloud 专属逻辑：优先使用本地的 PermissionApi 实现类，而不是 Feign 调用
        // 原因：在创建租户时，租户还没创建好，导致 Feign 调用获取数据权限时，报“租户不存在”的错误
        // 为了和上面保持方法一致
        TenantApi tenantApiImpl = null;
        try {
            PermissionApi permissionApiImpl = SpringUtil.getBean("permissionApiImpl", PermissionApi.class);
            tenantApiImpl = SpringUtil.getBean("tenantApi", TenantApi.class);
            if (permissionApiImpl != null) {
                permissionApi = permissionApiImpl;
            }
        } catch (Exception ignored) {}

        // 创建 DeptDataPermissionTwoRule 对象
        CommonDataPermissionRule rule = new CommonDataPermissionRule(permissionApi, tenantApiImpl);
        // 补全表配置
        customizers.forEach(customizer -> customizer.customize(rule));
        return rule;
    }

}
