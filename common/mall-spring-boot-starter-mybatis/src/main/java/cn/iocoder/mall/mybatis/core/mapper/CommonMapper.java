package cn.iocoder.mall.mybatis.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * Mapper 层基类
 * @author Hccake 2020/8/3
 * @version 1.0
 */
public interface CommonMapper<T> extends BaseMapper<T> {

    /**
     * 批量插入
     * @param collection 批量插入数据
     * @return ignore
     */
    int insertByBatch(@Param("collection") Collection<T> collection);
}
