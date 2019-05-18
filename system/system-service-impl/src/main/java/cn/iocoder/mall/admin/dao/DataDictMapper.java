package cn.iocoder.mall.admin.dao;

import cn.iocoder.mall.admin.dataobject.DataDictDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface DataDictMapper extends BaseMapper<DataDictDO> {

    DataDictDO selectByEnumValueAndValue(
            @Param("enumValue") String enumValue,
            @Param("value") String value
    );

    List<DataDictDO> selectByEnumValueAndValues(
            @Param("enumValue") String enumValue,
            @Param("values") Collection<String> values
    );

    List<DataDictDO> selectByEnumValue(
            @Param("enumValue") String enumValue
    );

    default List<DataDictDO> selectList() {
        return selectList(new QueryWrapper<>());
    }


}
