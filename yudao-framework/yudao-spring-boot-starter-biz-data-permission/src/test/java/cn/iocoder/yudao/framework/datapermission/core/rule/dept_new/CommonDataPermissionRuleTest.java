package cn.iocoder.yudao.framework.datapermission.core.rule.dept_new;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.iocoder.yudao.framework.common.enums.UserTypeEnum;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.collection.SetUtils;
import cn.iocoder.yudao.framework.datapermission.core.dal.dataobject.Channel;
import cn.iocoder.yudao.framework.datapermission.core.dal.dataobject.UserChannel;
import cn.iocoder.yudao.framework.datapermission.core.dal.mapper.ChannelMapper;
import cn.iocoder.yudao.framework.datapermission.core.dal.mapper.UserChannelMapper;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;
import cn.iocoder.yudao.module.system.api.permission.PermissionApi;
import cn.iocoder.yudao.module.system.api.permission.dto.DeptDataPermissionRespDTO;
import cn.iocoder.yudao.module.system.api.tenant.TenantApi;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;

import javax.annotation.Resource;
import java.util.Map;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.datapermission.core.rule.dept_new.CommonDataPermissionRule.EXPRESSION_NULL;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.randomPojo;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.randomString;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.TENANT_NOT_BUILT_IN_SYSTEM;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

/**
 * {@link CommonDataPermissionRule} 的单元测试
 *
 * @author 芋道源码
 */
class CommonDataPermissionRuleTest extends BaseDbUnitTest {

    @InjectMocks
    private CommonDataPermissionRule rule;

    @Mock
    private PermissionApi permissionApi;
    @Mock
    private TenantApi tenantApi;

    @Resource
    private ChannelMapper channelMapper;
    @Resource
    private UserChannelMapper userChannelMapper;

    @BeforeEach
    @SuppressWarnings("unchecked")
    public void setUp() {
        // 清空 rule
        rule.getTableNames().clear();
        ((Map<String, String>) ReflectUtil.getFieldValue(rule, "deptColumns")).clear();
        ((Map<String, String>) ReflectUtil.getFieldValue(rule, "deptColumns")).clear();
    }

    @Test // 无 LoginUser
    public void testGetExpression_noLoginUser() {
        // 准备参数
        String tableName = randomString();
        Alias tableAlias = new Alias(randomString());
        // mock 方法

        // 调用
        Expression expression = rule.getExpression(tableName, tableAlias);
        // 断言
        assertNull(expression);
    }

    @Test // 无数据权限时
    public void testGetExpression_noDeptDataPermission() {
        try (MockedStatic<SecurityFrameworkUtils> securityFrameworkUtilsMock
                     = mockStatic(SecurityFrameworkUtils.class)) {
            // 准备参数
            String tableName = "channel";
            Alias tableAlias = new Alias("u");
            // mock 方法
            LoginUser loginUser = randomPojo(LoginUser.class, o -> o.setId(1L)
                    .setUserType(UserTypeEnum.ADMIN.getValue()));
            securityFrameworkUtilsMock.when(SecurityFrameworkUtils::getLoginUser).thenReturn(loginUser);
            // mock 方法（permissionApi 返回 null）
            when(permissionApi.getDeptDataPermission(eq(loginUser.getId()))).thenReturn(success(null));
            // 添加 user 字段配置
            rule.addUserColumn(Channel::getId, UserChannel::getChannelId, UserChannel::getUserId);
            // 添加 dept 字段配置
            rule.addDeptColumn(Channel::getId, UserChannel::getChannelId, UserChannel::getDeptId);
            // 调用
            NullPointerException exception = assertThrows(NullPointerException.class,
                    () -> rule.getExpression(tableName, tableAlias));
            // 断言
            assertEquals("LoginUser(1) Table(channel/u) 未返回数据权限", exception.getMessage());
        }
    }

    @Test // 全部数据权限 系统内置租户
    public void testGetExpression_allDeptDataPermission_system_tenant() {
        try (MockedStatic<SecurityFrameworkUtils> securityFrameworkUtilsMock
                     = mockStatic(SecurityFrameworkUtils.class)) {
            // 准备参数
            String tableName = "channel";
            Alias tableAlias = new Alias("u");
            // mock 方法（LoginUser）
            LoginUser loginUser = randomPojo(LoginUser.class, o -> o.setId(1L).setTenantId(1L)
                    .setUserType(UserTypeEnum.ADMIN.getValue()));
            securityFrameworkUtilsMock.when(SecurityFrameworkUtils::getLoginUser).thenReturn(loginUser);
            // mock 方法（DeptDataPermissionRespDTO）
            DeptDataPermissionRespDTO deptDataPermission = new DeptDataPermissionRespDTO().setAll(true);
            when(permissionApi.getDeptDataPermission(same(1L))).thenReturn(success(deptDataPermission));
            when(tenantApi.validBuiltInSystem(same(1L))).thenReturn(success(true));

            // 添加 user 字段配置
            rule.addUserColumn(Channel::getId, UserChannel::getChannelId, UserChannel::getUserId);
            // 添加 dept 字段配置
            rule.addDeptColumn(Channel::getId, UserChannel::getChannelId, UserChannel::getDeptId);
            // 调用
            Expression expression = rule.getExpression(tableName, tableAlias);
            // 断言
            assertNull(expression);
            assertSame(deptDataPermission, loginUser.getContext(CommonDataPermissionRule.CONTEXT_KEY, DeptDataPermissionRespDTO.class));
        }
    }

    @Test // 全部数据权限 非内置租户
    public void testGetExpression_allDeptDataPermission_not_system_tenant() {
        try (MockedStatic<SecurityFrameworkUtils> securityFrameworkUtilsMock
                     = mockStatic(SecurityFrameworkUtils.class)) {
            // 准备参数
            String tableName = "channel";
            Alias tableAlias = new Alias("u");
            // mock 方法（LoginUser）
            LoginUser loginUser = randomPojo(LoginUser.class, o -> o.setId(1L).setTenantId(1L)
                    .setUserType(UserTypeEnum.ADMIN.getValue()));
            securityFrameworkUtilsMock.when(SecurityFrameworkUtils::getLoginUser).thenReturn(loginUser);
            // mock 方法（DeptDataPermissionRespDTO）
            DeptDataPermissionRespDTO deptDataPermission = new DeptDataPermissionRespDTO().setAll(true);
            when(permissionApi.getDeptDataPermission(same(1L))).thenReturn(success(deptDataPermission));
            when(tenantApi.validBuiltInSystem(same(1L))).thenReturn(CommonResult.error(TENANT_NOT_BUILT_IN_SYSTEM));

            // 添加 user 字段配置
            rule.addUserColumn(Channel::getId, UserChannel::getChannelId, UserChannel::getUserId);
            // 添加 dept 字段配置
            rule.addDeptColumn(Channel::getId, UserChannel::getChannelId, UserChannel::getDeptId);
            // 调用
            Expression expression = rule.getExpression(tableName, tableAlias);
            // 断言
            assertSame("u.id IN (SELECT channel_id FROM user_channel WHERE user_channel.tenant_id = 1)", expression);
            assertSame(deptDataPermission, loginUser.getContext(CommonDataPermissionRule.CONTEXT_KEY, DeptDataPermissionRespDTO.class));
        }
    }

    @Test // 即不能查看部门，又不能查看自己，则说明 100% 无权限
    public void testGetExpression_noDept_noSelf() {
        try (MockedStatic<SecurityFrameworkUtils> securityFrameworkUtilsMock
                     = mockStatic(SecurityFrameworkUtils.class)) {
            // 准备参数
            String tableName = "channel";
            Alias tableAlias = new Alias("u");
            // mock 方法（LoginUser）
            LoginUser loginUser = randomPojo(LoginUser.class, o -> o.setId(1L)
                    .setUserType(UserTypeEnum.ADMIN.getValue()));
            securityFrameworkUtilsMock.when(SecurityFrameworkUtils::getLoginUser).thenReturn(loginUser);
            // mock 方法（DeptDataPermissionRespDTO）
            DeptDataPermissionRespDTO deptDataPermission = new DeptDataPermissionRespDTO();
            when(permissionApi.getDeptDataPermission(same(1L))).thenReturn(success(deptDataPermission));

            // 添加 user 字段配置
            rule.addUserColumn(Channel::getId, UserChannel::getChannelId, UserChannel::getUserId);
            // 添加 dept 字段配置
            rule.addDeptColumn(Channel::getId, UserChannel::getChannelId, UserChannel::getDeptId);
            // 调用
            Expression expression = rule.getExpression(tableName, tableAlias);
            // 断言
            assertEquals("null = null", expression.toString());
            assertSame(deptDataPermission, loginUser.getContext(CommonDataPermissionRule.CONTEXT_KEY, DeptDataPermissionRespDTO.class));
        }
    }

    @Test // 拼接 Dept 和 User 的条件（字段都不符合）
    public void testGetExpression_noDeptColumn_noSelfColumn() {
        try (MockedStatic<SecurityFrameworkUtils> securityFrameworkUtilsMock
                     = mockStatic(SecurityFrameworkUtils.class)) {
            // 准备参数
            String tableName = "channel";
            Alias tableAlias = new Alias("u");
            // mock 方法（LoginUser）
            LoginUser loginUser = randomPojo(LoginUser.class, o -> o.setId(1L)
                    .setUserType(UserTypeEnum.ADMIN.getValue()));
            securityFrameworkUtilsMock.when(SecurityFrameworkUtils::getLoginUser).thenReturn(loginUser);
            // mock 方法（DeptDataPermissionRespDTO）
            DeptDataPermissionRespDTO deptDataPermission = new DeptDataPermissionRespDTO()
                    .setDeptIds(SetUtils.asSet(10L, 20L)).setSelf(true);
            when(permissionApi.getDeptDataPermission(same(1L))).thenReturn(success(deptDataPermission));

            // 调用
            Expression expression = rule.getExpression(tableName, tableAlias);
            // 断言
            assertSame(EXPRESSION_NULL, expression);
            assertSame(deptDataPermission, loginUser.getContext(CommonDataPermissionRule.CONTEXT_KEY, DeptDataPermissionRespDTO.class));
        }
    }

    @Test // 拼接 Dept 和 User 的条件（self 符合）
    public void testGetExpression_noDeptColumn_yesSelfColumn() {
        try (MockedStatic<SecurityFrameworkUtils> securityFrameworkUtilsMock
                     = mockStatic(SecurityFrameworkUtils.class)) {
            // 准备参数
            String tableName = "channel";
            Alias tableAlias = new Alias("u");
            // mock 方法（LoginUser）
            LoginUser loginUser = randomPojo(LoginUser.class, o -> o.setId(1L)
                    .setUserType(UserTypeEnum.ADMIN.getValue()));
            securityFrameworkUtilsMock.when(SecurityFrameworkUtils::getLoginUser).thenReturn(loginUser);
            // mock 方法（DeptDataPermissionRespDTO）
            DeptDataPermissionRespDTO deptDataPermission = new DeptDataPermissionRespDTO()
                    .setSelf(true);
            when(permissionApi.getDeptDataPermission(same(1L))).thenReturn(success(deptDataPermission));

            // 添加 user 字段配置
            rule.addUserColumn(Channel::getId, UserChannel::getChannelId, UserChannel::getUserId);
            // 添加 dept 字段配置
            rule.addDeptColumn(Channel::getId, UserChannel::getChannelId, UserChannel::getDeptId);

            // 调用
            Expression expression = rule.getExpression(tableName, tableAlias);
            // 断言
            assertEquals("u.id IN (SELECT channel_id FROM user_channel WHERE user_channel.user_id = 1", expression.toString());
            assertSame(deptDataPermission, loginUser.getContext(CommonDataPermissionRule.CONTEXT_KEY, DeptDataPermissionRespDTO.class));
        }
    }

    @Test // 拼接 Dept 和 User 的条件（dept 符合）
    public void testGetExpression_yesDeptColumn_noSelfColumn() {
        try (MockedStatic<SecurityFrameworkUtils> securityFrameworkUtilsMock
                     = mockStatic(SecurityFrameworkUtils.class)) {
            // 准备参数
            String tableName = "channel";
            Alias tableAlias = new Alias("u");
            // mock 方法（LoginUser）
            LoginUser loginUser = randomPojo(LoginUser.class, o -> o.setId(1L)
                    .setUserType(UserTypeEnum.ADMIN.getValue()));
            securityFrameworkUtilsMock.when(SecurityFrameworkUtils::getLoginUser).thenReturn(loginUser);
            // mock 方法（DeptDataPermissionRespDTO）
            DeptDataPermissionRespDTO deptDataPermission = new DeptDataPermissionRespDTO()
                    .setDeptIds(CollUtil.newLinkedHashSet(10L, 20L));
            when(permissionApi.getDeptDataPermission(same(1L))).thenReturn(success(deptDataPermission));

            // 添加 user 字段配置
            rule.addUserColumn(Channel::getId, UserChannel::getChannelId, UserChannel::getUserId);
            // 添加 dept 字段配置
            rule.addDeptColumn(Channel::getId, UserChannel::getChannelId, UserChannel::getDeptId);

            // 调用
            Expression expression = rule.getExpression(tableName, tableAlias);
            // 断言
            assertEquals("u.id IN (SELECT channel_id FROM user_channel WHERE user_channel.dept_id IN (10, 20))", expression.toString());
            assertSame(deptDataPermission, loginUser.getContext(CommonDataPermissionRule.CONTEXT_KEY, DeptDataPermissionRespDTO.class));
        }
    }

    @Test // 拼接 Dept 和 User 的条件（dept + self 符合）
    public void testGetExpression_yesDeptColumn_yesSelfColumn() {
        try (MockedStatic<SecurityFrameworkUtils> securityFrameworkUtilsMock
                     = mockStatic(SecurityFrameworkUtils.class)) {
            // 准备参数
            String tableName = "channel";
            Alias tableAlias = new Alias("u");
            // mock 方法（LoginUser）
            LoginUser loginUser = randomPojo(LoginUser.class, o -> o.setId(1L)
                    .setUserType(UserTypeEnum.ADMIN.getValue()));
            securityFrameworkUtilsMock.when(SecurityFrameworkUtils::getLoginUser).thenReturn(loginUser);
            // mock 方法（DeptDataPermissionRespDTO）
            DeptDataPermissionRespDTO deptDataPermission = new DeptDataPermissionRespDTO()
                    .setDeptIds(CollUtil.newLinkedHashSet(10L, 20L)).setSelf(true);
            when(permissionApi.getDeptDataPermission(same(1L))).thenReturn(success(deptDataPermission));
            // 添加 user 字段配置
            rule.addUserColumn(Channel::getId, UserChannel::getChannelId, UserChannel::getUserId);
            // 添加 dept 字段配置
            rule.addDeptColumn(Channel::getId, UserChannel::getChannelId, UserChannel::getDeptId);

            // 调用
            Expression expression = rule.getExpression(tableName, tableAlias);
            // 断言
            assertEquals("(u.id IN (SELECT channel_id FROM user_channel WHERE user_channel.dept_id IN (10, 20)) OR u.id IN (SELECT channel_id FROM user_channel WHERE user_channel.user_id = 1))", expression.toString());
            assertSame(deptDataPermission, loginUser.getContext(CommonDataPermissionRule.CONTEXT_KEY, DeptDataPermissionRespDTO.class));
        }
    }

}

