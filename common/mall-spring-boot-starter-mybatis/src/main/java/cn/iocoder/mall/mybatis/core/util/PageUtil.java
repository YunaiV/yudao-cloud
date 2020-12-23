package cn.iocoder.mall.mybatis.core.util;

import cn.iocoder.common.framework.util.CollectionUtils;
import cn.iocoder.common.framework.vo.PageParam;
import cn.iocoder.common.framework.vo.SortingField;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * 分页工具里
 *
 * 目前主要用于 {@link Page} 的构建
 */
public class PageUtil {

    public static <T> Page<T> build(PageParam pageParam) {
        return build(pageParam, null);
    }

    public static <T> Page<T> build(PageParam pageParam, Collection<SortingField> sortingFields) {
        // 页码 + 数量
        Page<T> page = new Page<>(pageParam.getPageNo(), pageParam.getPageSize());
        // 排序字段
        if (!CollectionUtils.isEmpty(sortingFields)) {
            page.addOrder(sortingFields.stream().map(sortingField -> SortingField.ORDER_ASC.equals(sortingField.getOrder()) ?
                    OrderItem.asc(sortingField.getField()) : OrderItem.desc(sortingField.getField()))
                    .collect(Collectors.toList()));
        }
        return page;
    }

}
