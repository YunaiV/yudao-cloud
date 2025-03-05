package cn.iocoder.yudao.framework.mybatis.core.mapper;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.SortablePageParam;
import cn.iocoder.yudao.framework.common.pojo.SortingField;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import cn.iocoder.yudao.framework.mybatis.core.util.JdbcUtils;
import cn.iocoder.yudao.framework.mybatis.core.util.MyBatisUtils;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.github.yulichang.base.MPJBaseMapper;
import com.github.yulichang.interfaces.MPJBaseJoin;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertSet;

/**
 * 在 MyBatis Plus 的 BaseMapper 的基础上拓展，提供更多的能力
 * <p>
 * 1. {@link BaseMapper} 为 MyBatis Plus 的基础接口，提供基础的 CRUD 能力
 * 2. {@link MPJBaseMapper} 为 MyBatis Plus Join 的基础接口，提供连表 Join 能力
 */
public interface BaseMapperX<T> extends MPJBaseMapper<T> {

    default PageResult<T> selectPage(SortablePageParam pageParam, @Param("ew") Wrapper<T> queryWrapper) {
        return selectPage(pageParam, pageParam.getSortingFields(), queryWrapper);
    }

    default PageResult<T> selectPage(PageParam pageParam, @Param("ew") Wrapper<T> queryWrapper) {
        return selectPage(pageParam, null, queryWrapper);
    }

    default PageResult<T> selectPage(PageParam pageParam, Collection<SortingField> sortingFields, @Param("ew") Wrapper<T> queryWrapper) {
        // 特殊：不分页，直接查询全部
        if (PageParam.PAGE_SIZE_NONE.equals(pageParam.getPageSize())) {
            List<T> list = selectList(queryWrapper);
            return new PageResult<>(list, (long) list.size());
        }

        // MyBatis Plus 查询
        IPage<T> mpPage = MyBatisUtils.buildPage(pageParam, sortingFields);
        selectPage(mpPage, queryWrapper);
        // 转换返回
        return new PageResult<>(mpPage.getRecords(), mpPage.getTotal());
    }

    default <D> PageResult<D> selectJoinPage(PageParam pageParam, Class<D> clazz, MPJLambdaWrapper<T> lambdaWrapper) {
        // 特殊：不分页，直接查询全部
        if (PageParam.PAGE_SIZE_NONE.equals(pageParam.getPageSize())) {
            List<D> list = selectJoinList(clazz, lambdaWrapper);
            return new PageResult<>(list, (long) list.size());
        }

        // MyBatis Plus Join 查询
        IPage<D> mpPage = MyBatisUtils.buildPage(pageParam);
        mpPage = selectJoinPage(mpPage, clazz, lambdaWrapper);
        // 转换返回
        return new PageResult<>(mpPage.getRecords(), mpPage.getTotal());
    }

    default <DTO> PageResult<DTO> selectJoinPage(PageParam pageParam, Class<DTO> resultTypeClass, MPJBaseJoin<T> joinQueryWrapper) {
        IPage<DTO> mpPage = MyBatisUtils.buildPage(pageParam);
        selectJoinPage(mpPage, resultTypeClass, joinQueryWrapper);
        // 转换返回
        return new PageResult<>(mpPage.getRecords(), mpPage.getTotal());
    }

    default T selectOne(String field, Object value) {
        return selectOne(new QueryWrapper<T>().eq(field, value));
    }

    default T selectOne(SFunction<T, ?> field, Object value) {
        return selectOne(new LambdaQueryWrapper<T>().eq(field, value));
    }

    default T selectOne(String field1, Object value1, String field2, Object value2) {
        return selectOne(new QueryWrapper<T>().eq(field1, value1).eq(field2, value2));
    }

    default T selectOne(SFunction<T, ?> field1, Object value1, SFunction<T, ?> field2, Object value2) {
        return selectOne(new LambdaQueryWrapper<T>().eq(field1, value1).eq(field2, value2));
    }

    default T selectOne(SFunction<T, ?> field1, Object value1, SFunction<T, ?> field2, Object value2,
                        SFunction<T, ?> field3, Object value3) {
        return selectOne(new LambdaQueryWrapper<T>().eq(field1, value1).eq(field2, value2)
                .eq(field3, value3));
    }

    default Long selectCount() {
        return selectCount(new QueryWrapper<>());
    }

    default Long selectCount(String field, Object value) {
        return selectCount(new QueryWrapper<T>().eq(field, value));
    }

    default Long selectCount(SFunction<T, ?> field, Object value) {
        return selectCount(new LambdaQueryWrapper<T>().eq(field, value));
    }

    default List<T> selectList() {
        return selectList(new QueryWrapper<>());
    }

    default List<T> selectList(String field, Object value) {
        return selectList(new QueryWrapper<T>().eq(field, value));
    }

    default List<T> selectList(SFunction<T, ?> field, Object value) {
        return selectList(new LambdaQueryWrapper<T>().eq(field, value));
    }

    default List<T> selectList(String field, Collection<?> values) {
        if (CollUtil.isEmpty(values)) {
            return CollUtil.newArrayList();
        }
        return selectList(new QueryWrapper<T>().in(field, values));
    }

    default List<T> selectList(SFunction<T, ?> field, Collection<?> values) {
        if (CollUtil.isEmpty(values)) {
            return CollUtil.newArrayList();
        }
        return selectList(new LambdaQueryWrapper<T>().in(field, values));
    }

    default List<T> selectList(SFunction<T, ?> field1, Object value1, SFunction<T, ?> field2, Object value2) {
        return selectList(new LambdaQueryWrapper<T>().eq(field1, value1).eq(field2, value2));
    }

    /**
     * 批量插入，适合大量数据插入
     *
     * @param entities 实体们
     */
    default Boolean insertBatch(Collection<T> entities) {
        // 特殊：SQL Server 批量插入后，获取 id 会报错，因此通过循环处理
        DbType dbType = JdbcUtils.getDbType();
        if (JdbcUtils.isSQLServer(dbType)) {
            entities.forEach(this::insert);
            return CollUtil.isNotEmpty(entities);
        }
        return Db.saveBatch(entities);
    }

    /**
     * 批量插入，适合大量数据插入
     *
     * @param entities 实体们
     * @param size     插入数量 Db.saveBatch 默认为 1000
     */
    default Boolean insertBatch(Collection<T> entities, int size) {
        // 特殊：SQL Server 批量插入后，获取 id 会报错，因此通过循环处理
        DbType dbType = JdbcUtils.getDbType();
        if (JdbcUtils.isSQLServer(dbType)) {
            entities.forEach(this::insert);
            return CollUtil.isNotEmpty(entities);
        }
        return Db.saveBatch(entities, size);
    }

    default int updateBatch(T update) {
        return update(update, new QueryWrapper<>());
    }

    default Boolean updateBatch(Collection<T> entities) {
        return Db.updateBatchById(entities);
    }

    default Boolean updateBatch(Collection<T> entities, int size) {
        return Db.updateBatchById(entities, size);
    }

    default int delete(String field, String value) {
        return delete(new QueryWrapper<T>().eq(field, value));
    }

    default int delete(SFunction<T, ?> field, Object value) {
        return delete(new LambdaQueryWrapper<T>().eq(field, value));
    }

    /**
     * 通用的子表批量更新方法(设置父级ID并清空更新人和更新时间)
     *
     * @param parentId       父级ID
     * @param parentIdSetter 设置父级ID的函数
     * @param newList        新的数据列表
     * @param dbList         数据库中已存在的数据列表
     * @param idGetter       子表获取ID的函数
     * @param <ID>           ID类型
     * @param <P>            父级ID类型
     */
    default <P, ID> void subListUpdateBatch(P parentId,
                                            BiConsumer<T, P> parentIdSetter,
                                            List<T> newList,
                                            List<T> dbList,
                                            Function<T, ID> idGetter) {
        if (CollUtil.isEmpty(newList)) return;

        // 设置父级ID和清空一些字段
        newList.forEach(item -> {
            parentIdSetter.accept(item, parentId);
            if (item instanceof BaseDO) {
                ((BaseDO) item).setUpdater(null).setUpdateTime(null); // 解决更新情况下：updateTime 不更新
            }
        });

        Set<ID> dbIds = convertSet(dbList, idGetter);
        Set<ID> ids = convertSet(newList, idGetter);

        // 1. 带id的是需要修改的 2. 没有id的是需要添加的
        // 根据是否有Id进行分类 (True: 需要更新  False: 需要新增)
        Map<Boolean, List<T>> partitioned = newList.stream()
                .collect(Collectors.partitioningBy(o -> idGetter.apply(o) != null));

        // 3. 数据库存的比前端传多的数据需要删除
        Collection<ID> deleteIds = CollUtil.subtract(dbIds, CollUtil.emptyIfNull(ids));

        // 批量操作
        if (CollUtil.isNotEmpty(partitioned.get(true))) {
            updateBatch(partitioned.get(true));
        }
        if (CollUtil.isNotEmpty(partitioned.get(false))) {
            insertBatch(partitioned.get(false));
        }
        if (CollUtil.isNotEmpty(deleteIds)) {
            deleteByIds(deleteIds);
        }
    }

}
