package cn.iocoder.mall.admin.convert;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.admin.api.bo.admin.AdminAuthenticationBO;
import cn.iocoder.mall.admin.api.bo.admin.AdminBO;
import cn.iocoder.mall.admin.api.dto.admin.AdminAddDTO;
import cn.iocoder.mall.admin.api.dto.admin.AdminUpdateDTO;
import cn.iocoder.mall.admin.dataobject.AdminDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-31T18:10:13+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class AdminConvertImpl implements AdminConvert {

    @Override
    public AdminBO convert(AdminDO adminDO) {
        if ( adminDO == null ) {
            return null;
        }

        AdminBO adminBO = new AdminBO();

        adminBO.setId( adminDO.getId() );
        adminBO.setUsername( adminDO.getUsername() );
        adminBO.setNickname( adminDO.getNickname() );
        adminBO.setStatus( adminDO.getStatus() );
        adminBO.setCreateTime( adminDO.getCreateTime() );

        return adminBO;
    }

    @Override
    public AdminAuthenticationBO convert2(AdminDO admin) {
        if ( admin == null ) {
            return null;
        }

        AdminAuthenticationBO adminAuthenticationBO = new AdminAuthenticationBO();

        adminAuthenticationBO.setId( admin.getId() );
        adminAuthenticationBO.setNickname( admin.getNickname() );

        return adminAuthenticationBO;
    }

    @Override
    public AdminDO convert(AdminAddDTO adminAddDTO) {
        if ( adminAddDTO == null ) {
            return null;
        }

        AdminDO adminDO = new AdminDO();

        adminDO.setUsername( adminAddDTO.getUsername() );
        adminDO.setNickname( adminAddDTO.getNickname() );
        adminDO.setPassword( adminAddDTO.getPassword() );

        return adminDO;
    }

    @Override
    public AdminDO convert(AdminUpdateDTO adminUpdateDTO) {
        if ( adminUpdateDTO == null ) {
            return null;
        }

        AdminDO adminDO = new AdminDO();

        adminDO.setId( adminUpdateDTO.getId() );
        adminDO.setUsername( adminUpdateDTO.getUsername() );
        adminDO.setNickname( adminUpdateDTO.getNickname() );
        adminDO.setPassword( adminUpdateDTO.getPassword() );

        return adminDO;
    }

    @Override
    public List<AdminBO> convert(List<AdminDO> adminBOs) {
        if ( adminBOs == null ) {
            return null;
        }

        List<AdminBO> list = new ArrayList<AdminBO>( adminBOs.size() );
        for ( AdminDO adminDO : adminBOs ) {
            list.add( convert( adminDO ) );
        }

        return list;
    }

    @Override
    public PageResult<AdminBO> convert(IPage<AdminDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<AdminBO> pageResult = new PageResult<AdminBO>();

        pageResult.setList( convert( page.getRecords() ) );
        pageResult.setTotal( (int) page.getTotal() );

        return pageResult;
    }
}
