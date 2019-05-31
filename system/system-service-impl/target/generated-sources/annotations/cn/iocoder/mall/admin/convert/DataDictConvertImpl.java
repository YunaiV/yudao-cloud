package cn.iocoder.mall.admin.convert;

import cn.iocoder.mall.admin.api.bo.datadict.DataDictBO;
import cn.iocoder.mall.admin.api.dto.datadict.DataDictAddDTO;
import cn.iocoder.mall.admin.api.dto.datadict.DataDictUpdateDTO;
import cn.iocoder.mall.admin.dataobject.DataDictDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-31T17:43:56+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class DataDictConvertImpl implements DataDictConvert {

    @Override
    public DataDictDO convert(DataDictAddDTO dataDictAddDTO) {
        if ( dataDictAddDTO == null ) {
            return null;
        }

        DataDictDO dataDictDO = new DataDictDO();

        dataDictDO.setEnumValue( dataDictAddDTO.getEnumValue() );
        dataDictDO.setValue( dataDictAddDTO.getValue() );
        dataDictDO.setDisplayName( dataDictAddDTO.getDisplayName() );
        dataDictDO.setSort( dataDictAddDTO.getSort() );
        dataDictDO.setMemo( dataDictAddDTO.getMemo() );

        return dataDictDO;
    }

    @Override
    public DataDictDO convert(DataDictUpdateDTO dataDictUpdateDTO) {
        if ( dataDictUpdateDTO == null ) {
            return null;
        }

        DataDictDO dataDictDO = new DataDictDO();

        dataDictDO.setId( dataDictUpdateDTO.getId() );
        dataDictDO.setEnumValue( dataDictUpdateDTO.getEnumValue() );
        dataDictDO.setValue( dataDictUpdateDTO.getValue() );
        dataDictDO.setDisplayName( dataDictUpdateDTO.getDisplayName() );
        dataDictDO.setSort( dataDictUpdateDTO.getSort() );
        dataDictDO.setMemo( dataDictUpdateDTO.getMemo() );

        return dataDictDO;
    }

    @Override
    public DataDictBO convert(DataDictDO dataDictDO) {
        if ( dataDictDO == null ) {
            return null;
        }

        DataDictBO dataDictBO = new DataDictBO();

        dataDictBO.setId( dataDictDO.getId() );
        dataDictBO.setEnumValue( dataDictDO.getEnumValue() );
        dataDictBO.setValue( dataDictDO.getValue() );
        dataDictBO.setDisplayName( dataDictDO.getDisplayName() );
        dataDictBO.setSort( dataDictDO.getSort() );
        dataDictBO.setMemo( dataDictDO.getMemo() );
        dataDictBO.setCreateTime( dataDictDO.getCreateTime() );

        return dataDictBO;
    }

    @Override
    public List<DataDictBO> convert(List<DataDictDO> dataDictDOs) {
        if ( dataDictDOs == null ) {
            return null;
        }

        List<DataDictBO> list = new ArrayList<DataDictBO>( dataDictDOs.size() );
        for ( DataDictDO dataDictDO : dataDictDOs ) {
            list.add( convert( dataDictDO ) );
        }

        return list;
    }
}
