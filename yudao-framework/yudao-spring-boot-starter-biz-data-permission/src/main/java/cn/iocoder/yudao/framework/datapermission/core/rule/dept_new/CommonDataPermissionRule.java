package cn.iocoder.yudao.framework.datapermission.core.rule.dept_new;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.iocoder.yudao.framework.common.enums.UserTypeEnum;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import cn.iocoder.yudao.framework.datapermission.core.rule.DataPermissionRule;
import cn.iocoder.yudao.framework.datapermission.core.rule.dept.DeptDataPermissionRule;
import cn.iocoder.yudao.framework.mybatis.core.util.MyBatisUtils;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.system.api.permission.PermissionApi;
import cn.iocoder.yudao.module.system.api.permission.dto.DeptDataPermissionRespDTO;
import cn.iocoder.yudao.module.system.api.tenant.TenantApi;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.support.ColumnCache;
import com.baomidou.mybatisplus.core.toolkit.support.LambdaMeta;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.*;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.PlainSelect;
// import net.sf.jsqlparser.statement.select.SubSelect;
import net.sf.jsqlparser.util.SelectUtils;
import org.apache.ibatis.reflection.property.PropertyNamer;

import java.util.*;

/**
 * 基于部门的 {@link DataPermissionRule} 数据权限规则实现
 *
 * 注意，使用 DeptDataPermissionRule 时，需要保证表中有 dept_id 部门编号的字段，可自定义。
 *
 * 实际业务场景下，会存在一个经典的问题？当用户修改部门时，冗余的 dept_id 是否需要修改？
 * 1. 一般情况下，dept_id 不进行修改，则会导致用户看不到之前的数据。【yudao-server 采用该方案】
 * 2. 部分情况下，希望该用户还是能看到之前的数据，则有两种方式解决：【需要你改造该 DeptDataPermissionRule 的实现代码】
 *  1）编写洗数据的脚本，将 dept_id 修改成新部门的编号；【建议】
 *      最终过滤条件是 where ? in (select ? from ? where dept_id in ? )
 *  2）洗数据的话，可能涉及的数据量较大，也可以采用 user_id 进行过滤的方式，此时需要获取到 dept_id 对应的所有 user_id 用户编号；
 *      最终过滤条件是 where ? in (select ? from ? where user_id in ? )
 *  3）想要保证原 dept_id 和 user_id 都可以看的到，此时使用 dept_id 和 user_id 一起过滤；
 *      最终过滤条件是 WHERE ? in (select ? from ? where user_id in ? ) or ? in (select ? from ? where dept_id in ? )
 *
 * @author sur1-123
 */
@AllArgsConstructor
@Slf4j
public class CommonDataPermissionRule implements DataPermissionRule {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class DataPermissionConf {
        // EXPLAIN 例如 取channel数据
        //  channel为源表 tableName
        //  user_channel中的通道编码对应
        //  数据权限字段 user_id dept_id tenant_id 和
        //  数据关系表user_channel
        private String tableName; // 共享数据表名称
        private String tableFieldName; // 共享数据表和数据关系表 - 关联的字段名称（共享数据表字段）
        private String dataPermissionFieldName;// 数据关系表和共享数据表 - 关联字段名称（数据关系表字段）
        private String dataPermissionTableName;// 数据关系表名称
        private String dataPermissionJudgementFieldName;// 数据关系表-用于校验数据权限字段（dept_id/user_id）
    }

    /**
     * LoginUser 的 Context 缓存 Key
     */
    protected static final String CONTEXT_KEY = CommonDataPermissionRule.class.getSimpleName();

    static final Expression EXPRESSION_NULL = new NullValue();

    private final PermissionApi permissionApi;
    private final TenantApi tenantApi;

    /**
     * 基于部门的表字段配置
     * 一般情况下，每个表的部门编号字段是 dept_id，通过该配置自定义。
     *
     * key：表名
     * value：字段名
     */
    private final Map<String, DataPermissionConf> deptColumns = new HashMap<>();
    /**
     * 基于租户的表字段配置
     * 一般情况下，每个表的租户编号字段是 tenant_id，通过该配置自定义。
     *
     * key：表名
     * value：字段名
     */
    private final Map<String, DataPermissionConf> tenantColumns = new HashMap<>();
    /**
     * 基于用户的表字段配置
     * 一般情况下，每个表的部门编号字段是 dept_id，通过该配置自定义。
     *
     * key：表名
     * value：字段名
     */
    private final Map<String, DataPermissionConf> userColumns = new HashMap<>();
    /**
     * 所有表名，是 {@link #deptColumns} 和 {@link #userColumns} 和 {@link #tenantColumns} 的合集
     */
    private final Set<String> TABLE_NAMES = new HashSet<>();

    @Override
    public Set<String> getTableNames() {
        return TABLE_NAMES;
    }

    @Override
    public Expression getExpression(String tableName, Alias tableAlias) {
        // 只有有登陆用户的情况下，才进行数据权限的处理
        LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();
        if (loginUser == null) {
            return null;
        }
        // 只有 管理员类型/三方类型  的用户，才进行数据权限的处理
        if (ObjectUtil.notEqual(loginUser.getUserType(), UserTypeEnum.ADMIN.getValue())) {
            return null;
        }

        // 获得数据权限
        DeptDataPermissionRespDTO deptDataPermission = loginUser.getContext(CONTEXT_KEY, DeptDataPermissionRespDTO.class);
        // 从上下文中拿不到，则调用逻辑进行获取
        if (ObjectUtil.equal(loginUser.getUserType(), UserTypeEnum.ADMIN.getValue())) {
            if (deptDataPermission == null) {
                deptDataPermission = permissionApi.getDeptDataPermission(loginUser.getId()).getCheckedData();
                if (deptDataPermission == null) {
                    log.error("[getExpression][LoginUser({}) 获取数据权限为 null]", JsonUtils.toJsonString(loginUser));
                    throw new NullPointerException(String.format("LoginUser(%d) Table(%s/%s) 未返回数据权限",
                            loginUser.getId(), tableName, tableAlias.getName()));
                }
                // 添加到上下文中，避免重复计算
                loginUser.setContext(CONTEXT_KEY, deptDataPermission);
            }
        }

        // 情况一，如果是 ALL 可查看全部，则无需拼接条件
        // 如果开启数据权限 那么关联表中的数据也需要有租户ID的信息  非关联表（原始数据表中可以没有对应的租户编号-注明：由于覆盖性太低，数据与租户之间非一对多关系）
        if (deptDataPermission.getAll()) {
            CommonResult<Boolean> validBuiltInSystem = tenantApi.validBuiltInSystem(loginUser.getTenantId());
            if(validBuiltInSystem.isSuccess()){
                return null;
            } else {
                Expression expression = buildTenantExpression(tableName, tableAlias, loginUser.getTenantId());
                return expression;
            }
        }

        // 情况二，即不能查看部门，又不能查看自己，则说明 100% 无权限
        if (CollUtil.isEmpty(deptDataPermission.getDeptIds())
            && Boolean.FALSE.equals(deptDataPermission.getSelf())) {
            return new EqualsTo(null, null); // WHERE null = null，可以保证返回的数据为空
        }

        // 情况三，拼接 Dept 和 User 的条件，最后组合
        Expression deptExpression = buildDeptExpression(tableName,tableAlias, deptDataPermission.getDeptIds());
        Expression userExpression = buildUserExpression(tableName, tableAlias, deptDataPermission.getSelf(), loginUser.getId());
        if (deptExpression == null && userExpression == null) {
            // TODO 芋艿：获得不到条件的时候，暂时不抛出异常，而是不返回数据
            log.warn("[getExpression][LoginUser({}) Table({}/{}) DeptDataPermission({}) 构建的条件为空]",
                    JsonUtils.toJsonString(loginUser), tableName, tableAlias, JsonUtils.toJsonString(deptDataPermission));
           // throw new NullPointerException(String.format("LoginUser(%d) Table(%s/%s) 构建的条件为空",
           //         loginUser.getId(), tableName, tableAlias.getName()));
            return EXPRESSION_NULL;
        }
        if (deptExpression == null) {
            return userExpression;
        }
        if (userExpression == null) {
            return deptExpression;
        }
        // 目前，如果有指定部门 + 可查看自己，采用 OR 条件。即，WHERE (dept_id IN ? OR user_id = ?)
        return new Parenthesis(new OrExpression(deptExpression, userExpression));
    }

    private Expression buildTenantExpression(String tableName, Alias tableAlias, Long tenantId) {
        // 如果不存在配置，则无需作为条件
        DataPermissionConf columnRule = tenantColumns.get(tableName);
        if (ObjectUtil.isEmpty(columnRule)) {
            return null;
        }
        // 如果为空，则无条件
        if (ObjectUtil.isEmpty(tenantId)) {
            return null;
        }
        // 拼接条件
        PlainSelect selectBody = (PlainSelect) SelectUtils.buildSelectFromTableAndExpressions(
                new Table(columnRule.getDataPermissionTableName())
                , new Column(columnRule.getDataPermissionFieldName())).getSelectBody();
        selectBody.setWhere(new EqualsTo(
                MyBatisUtils.buildColumn(columnRule.getDataPermissionTableName(), null, "tenant_id")
                , new LongValue(tenantId))
        );
        return new InExpression(
                MyBatisUtils.buildColumn(tableName, tableAlias, columnRule.getTableFieldName()),
                new Parenthesis(selectBody)
        );
    }

    private Expression buildDeptExpression(String tableName, Alias tableAlias, Set<Long> deptIds) {
        // 如果不存在配置，则无需作为条件
        DataPermissionConf columnRule = deptColumns.get(tableName);
        if (ObjectUtil.isEmpty(columnRule)) {
            return null;
        }
        // 如果为空，则无条件
        if (CollUtil.isEmpty(deptIds)) {
            return null;
        }
        // 拼接条件
        PlainSelect selectBody = (PlainSelect) SelectUtils.buildSelectFromTableAndExpressions(
                new Table(columnRule.getDataPermissionTableName())
                , new Column(columnRule.getDataPermissionFieldName())).getSelectBody();
        selectBody.setWhere(new InExpression(
                MyBatisUtils.buildColumn(columnRule.getDataPermissionTableName(), null, columnRule.getDataPermissionJudgementFieldName())
                , new ParenthesedExpressionList(CollectionUtils.convertList(deptIds, LongValue::new))));
        return new InExpression(
                MyBatisUtils.buildColumn(tableName, tableAlias, columnRule.getTableFieldName()),
                new Parenthesis(selectBody)
        );
    }

    private Expression buildUserExpression(String tableName, Alias tableAlias, Boolean self, Long userId) {
        // 如果不查看自己，则无需作为条件
        if (Boolean.FALSE.equals(self)) {
            return null;
        }
        DataPermissionConf columnRule = userColumns.get(tableName);
        if (ObjectUtil.isEmpty(columnRule)) {
            return null;
        }
        // 拼接条件

        PlainSelect selectBody = (PlainSelect) SelectUtils.buildSelectFromTableAndExpressions(
                    new Table(columnRule.getDataPermissionTableName())
                    , new Column(columnRule.getDataPermissionFieldName())
                    ).getSelectBody();
        selectBody.setWhere(new EqualsTo(MyBatisUtils.buildColumn(columnRule.getDataPermissionTableName(), null, columnRule.getDataPermissionJudgementFieldName())
                , userId != null ? new LongValue(userId) : null));

        return new InExpression(
                MyBatisUtils.buildColumn(tableName, tableAlias, columnRule.getTableFieldName()),
                new Parenthesis(selectBody)
        );
    }

    // ==================== 添加配置 ====================

    public <E, A, P> void addDeptColumn(SFunction<E, ?> source, SFunction<A, ?> permission, SFunction<P, ?> sp) {
        DataPermissionConf dataPermissionConf = new DataPermissionConf();
        initDataPermissionRule(1, dataPermissionConf, source);
        initDataPermissionRule(2, dataPermissionConf, permission);
        initDataPermissionRule(3, dataPermissionConf, sp);
        addDeptColumn(dataPermissionConf);
        addTenantColumn(dataPermissionConf);
    }

    public void addDeptColumn(DataPermissionConf dataPermissionConf) {
        deptColumns.put(dataPermissionConf.getTableName(), dataPermissionConf);
        TABLE_NAMES.add(dataPermissionConf.getTableName());
    }

    public <E, A, P> void addUserColumn(SFunction<E, ?> source, SFunction<A, ?> permission, SFunction<P, ?> sp) {
        DataPermissionConf dataPermissionConf = new DataPermissionConf();
        initDataPermissionRule(1, dataPermissionConf, source);
        initDataPermissionRule(2, dataPermissionConf, permission);
        initDataPermissionRule(3, dataPermissionConf, sp);
        addUserColumn(dataPermissionConf);
        addTenantColumn(dataPermissionConf);
    }

    public void addUserColumn(DataPermissionConf dataPermissionConf) {
        userColumns.put(dataPermissionConf.getTableName(), dataPermissionConf);
        TABLE_NAMES.add(dataPermissionConf.getTableName());
    }

    public void addTenantColumn(DataPermissionConf dataPermissionConf) {
        tenantColumns.put(dataPermissionConf.getTableName(), dataPermissionConf);
        TABLE_NAMES.add(dataPermissionConf.getTableName());
    }

    public void initDataPermissionRule(Integer type, DataPermissionConf dataPermissionConf, SFunction<?, ?> fn){
        LambdaMeta extract = LambdaUtils.extract(fn);
        Class<?> instantiatedClass = extract.getInstantiatedClass();
        TableInfo tableInfo = TableInfoHelper.getTableInfo(instantiatedClass);
        String fieldName = PropertyNamer.methodToProperty(extract.getImplMethodName());
        Map<String, ColumnCache> columnMap = LambdaUtils.getColumnMap(instantiatedClass);
        ColumnCache columnCache =  columnMap.get(fieldName.toUpperCase());
        String tableName = tableInfo.getTableName();
        String column = columnCache.getColumn();

        switch (type){
            case 1:{
                dataPermissionConf.setTableName(tableName).setTableFieldName(column);
                break;
            }
            case 2:{
                dataPermissionConf.setDataPermissionTableName(tableName)
                        .setDataPermissionFieldName(column);
                break;
            }
            case 3:{
                dataPermissionConf.setDataPermissionJudgementFieldName(column);
                break;
            }
            default:{}
        }
    }

}
