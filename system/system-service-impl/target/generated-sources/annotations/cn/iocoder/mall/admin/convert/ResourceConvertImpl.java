package cn.iocoder.mall.admin.convert;

import cn.iocoder.mall.admin.api.bo.resource.ResourceBO;
import cn.iocoder.mall.admin.api.dto.resource.ResourceAddDTO;
import cn.iocoder.mall.admin.api.dto.resource.ResourceUpdateDTO;
import cn.iocoder.mall.admin.dataobject.ResourceDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-31T17:43:56+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class ResourceConvertImpl implements ResourceConvert {

    @Override
    public ResourceBO convert(ResourceDO resourceDO) {
        if ( resourceDO == null ) {
            return null;
        }

        ResourceBO resourceBO = new ResourceBO();

        resourceBO.setPermissions( translateListFromString( resourceDO.getPermissions() ) );
        resourceBO.setId( resourceDO.getId() );
        resourceBO.setType( resourceDO.getType() );
        resourceBO.setSort( resourceDO.getSort() );
        resourceBO.setDisplayName( resourceDO.getDisplayName() );
        resourceBO.setPid( resourceDO.getPid() );
        resourceBO.setHandler( resourceDO.getHandler() );
        resourceBO.setIcon( resourceDO.getIcon() );
        resourceBO.setCreateTime( resourceDO.getCreateTime() );

        return resourceBO;
    }

    @Override
    public List<ResourceBO> convert(List<ResourceDO> resourceDOs) {
        if ( resourceDOs == null ) {
            return null;
        }

        List<ResourceBO> list = new ArrayList<ResourceBO>( resourceDOs.size() );
        for ( ResourceDO resourceDO : resourceDOs ) {
            list.add( convert( resourceDO ) );
        }

        return list;
    }

    @Override
    public ResourceDO convert(ResourceAddDTO resourceAddDTO) {
        if ( resourceAddDTO == null ) {
            return null;
        }

        ResourceDO resourceDO = new ResourceDO();

        resourceDO.setPermissions( translateStringFromList( resourceAddDTO.getPermissions() ) );
        resourceDO.setType( resourceAddDTO.getType() );
        resourceDO.setSort( resourceAddDTO.getSort() );
        resourceDO.setDisplayName( resourceAddDTO.getDisplayName() );
        resourceDO.setPid( resourceAddDTO.getPid() );
        resourceDO.setHandler( resourceAddDTO.getHandler() );
        resourceDO.setIcon( resourceAddDTO.getIcon() );

        return resourceDO;
    }

    @Override
    public ResourceDO convert(ResourceUpdateDTO resourceUpdateDTO) {
        if ( resourceUpdateDTO == null ) {
            return null;
        }

        ResourceDO resourceDO = new ResourceDO();

        resourceDO.setPermissions( translateStringFromList( resourceUpdateDTO.getPermissions() ) );
        resourceDO.setId( resourceUpdateDTO.getId() );
        resourceDO.setType( resourceUpdateDTO.getType() );
        resourceDO.setSort( resourceUpdateDTO.getSort() );
        resourceDO.setDisplayName( resourceUpdateDTO.getDisplayName() );
        resourceDO.setPid( resourceUpdateDTO.getPid() );
        resourceDO.setHandler( resourceUpdateDTO.getHandler() );
        resourceDO.setIcon( resourceUpdateDTO.getIcon() );

        return resourceDO;
    }
}
