package cn.iocoder.mall.admin.dao;

import cn.iocoder.mall.admin.dataobject.DataDictDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface DataDictMapper {

    DataDictDO selectById(@Param("id") Integer id);

    DataDictDO selectByEnumValueAndValue(
            @Param("enumValue") String enumValue,
            @Param("value") String value
    );

    List<DataDictDO> selectByEnumValueAndValues(
            @Param("enumValue") String enumValue,
            @Param("values") Collection<String> values
    );


    List<DataDictDO> selectList();

    void insert(DataDictDO dataDict);

    int update(DataDictDO dataDict);

}
