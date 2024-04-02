package com.fhs.trans.service;

import com.fhs.core.trans.vo.VO;

import java.util.ArrayList;
import java.util.List;

/**
 * 只有实现了这个接口的才能自动翻译
 *
 * 为什么要赋值粘贴到 yudao-common 包下？
 * 因为 AutoTransable 属于 easy-trans-service 下，无法方便的在 yudao-module-xxx-api 模块下使用
 *
 * @author jackwang
 * @since  2020-05-19 10:26:15
 */
public interface AutoTransable<V extends VO> {

    /**
     * 根据 ids 查询数据列表
     *
     * 改方法已过期啦，请使用 selectByIds
     *
     * @param ids 编号数组
     * @return 数据列表
     */
    @Deprecated
    default List<V> findByIds(List<? extends Object> ids){
        return new ArrayList<>();
    }

    /**
     * 根据 ids 查询
     *
     * @param ids 编号数组
     * @return 数据列表
     */
    default List<V> selectByIds(List<? extends Object> ids){
        return this.findByIds(ids);
    }

    /**
     * 获取 db 中所有的数据
     *
     * @return db 中所有的数据
     */
    default List<V> select(){
        return new ArrayList<>();
    }

    /**
     * 根据 id 获取 vo
     *
     * @param primaryValue id
     * @return vo
     */
    V selectById(Object primaryValue);

}
