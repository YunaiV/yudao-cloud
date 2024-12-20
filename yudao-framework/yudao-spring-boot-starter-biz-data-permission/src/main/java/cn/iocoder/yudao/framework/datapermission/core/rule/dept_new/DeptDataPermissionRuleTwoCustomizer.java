package cn.iocoder.yudao.framework.datapermission.core.rule.dept_new;



/**
 * {@link DeptDataPermissionRule} 的自定义配置接口
 *
 * @author jiahaojie
 */
@FunctionalInterface
public interface DeptDataPermissionRuleTwoCustomizer {

    /**
     * 自定义该权限规则
     * 1. 调用 {@link DeptDataPermissionRuleTwo#addDeptColumn(DeptDataPermissionRuleTwo.DataPermissionConf)} 方法，配置基于 dept_id 的过滤规则
     * 2. 调用 {@link DeptDataPermissionRuleTwo#addUserColumn(DeptDataPermissionRuleTwo.DataPermissionConf)} 方法，配置基于 user_id 的过滤规则
     *
     * @param rule 权限规则
     */
    void customize(DeptDataPermissionRuleTwo rule);

}
