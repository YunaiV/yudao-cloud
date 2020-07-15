package cn.iocoder.mall.systemservice.dal.mysql.mapper.datadict;

import cn.iocoder.mall.systemservice.dal.mysql.dataobject.datadict.DataDictDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface DataDictMapper extends BaseMapper<DataDictDO> {

    default DataDictDO selectByEnumValueAndValue(String enumValue, String value) {
        return selectOne(new QueryWrapper<DataDictDO>()
                .eq("enum_value", enumValue).eq("value", value));
    }

    default List<DataDictDO> selectByEnumValueAndValues(String enumValue, Collection<String> values) {
        return selectList(new QueryWrapper<DataDictDO>()
                .eq("enum_value", enumValue).in("value", values));
    }

    default List<DataDictDO> selectByEnumValue(String enumValue) {
        return selectList(new QueryWrapper<DataDictDO>().eq("enum_value", enumValue));
    }

}
