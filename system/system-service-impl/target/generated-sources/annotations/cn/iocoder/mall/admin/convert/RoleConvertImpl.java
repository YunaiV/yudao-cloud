package cn.iocoder.mall.admin.convert;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.admin.api.bo.role.RoleBO;
import cn.iocoder.mall.admin.api.dto.role.RoleAddDTO;
import cn.iocoder.mall.admin.api.dto.role.RoleUpdateDTO;
import cn.iocoder.mall.admin.dataobject.RoleDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-31T18:10:13+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class RoleConvertImpl implements RoleConvert {

    @Override
    public RoleDO convert(RoleAddDTO roleAddDTO) {
        if ( roleAddDTO == null ) {
            return null;
        }

        RoleDO roleDO = new RoleDO();

        roleDO.setName( roleAddDTO.getName() );

        return roleDO;
    }

    @Override
    public RoleDO convert(RoleUpdateDTO roleUpdateDTO) {
        if ( roleUpdateDTO == null ) {
            return null;
        }

        RoleDO roleDO = new RoleDO();

        roleDO.setId( roleUpdateDTO.getId() );
        roleDO.setName( roleUpdateDTO.getName() );

        return roleDO;
    }

    @Override
    public RoleBO convert(RoleDO roleDO) {
        if ( roleDO == null ) {
            return null;
        }

        RoleBO roleBO = new RoleBO();

        roleBO.setId( roleDO.getId() );
        roleBO.setName( roleDO.getName() );
        roleBO.setCreateTime( roleDO.getCreateTime() );

        return roleBO;
    }

    @Override
    public List<RoleBO> convert(List<RoleDO> roleDOs) {
        if ( roleDOs == null ) {
            return null;
        }

        List<RoleBO> list = new ArrayList<RoleBO>( roleDOs.size() );
        for ( RoleDO roleDO : roleDOs ) {
            list.add( convert( roleDO ) );
        }

        return list;
    }

    @Override
    public PageResult<RoleBO> convert(IPage<RoleDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<RoleBO> pageResult = new PageResult<RoleBO>();

        pageResult.setList( convert( page.getRecords() ) );
        pageResult.setTotal( (int) page.getTotal() );

        return pageResult;
    }
}
