package cn.iocoder.mall.admin.application.convert;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.admin.api.bo.admin.AdminBO;
import cn.iocoder.mall.admin.api.bo.role.RoleBO;
import cn.iocoder.mall.admin.application.vo.admin.AdminInfoVO;
import cn.iocoder.mall.admin.application.vo.admin.AdminRoleVO;
import cn.iocoder.mall.admin.application.vo.admin.AdminVO;
import cn.iocoder.mall.admin.application.vo.admin.AdminVO.Role;
import cn.iocoder.mall.admin.sdk.context.AdminSecurityContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-31T18:11:49+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class AdminConvertImpl implements AdminConvert {

    @Override
    public AdminInfoVO convert(AdminSecurityContext adminSecurityContext) {
        if ( adminSecurityContext == null ) {
            return null;
        }

        AdminInfoVO adminInfoVO = new AdminInfoVO();

        adminInfoVO.setAdminId( adminSecurityContext.getAdminId() );
        Set<Integer> set = adminSecurityContext.getRoleIds();
        if ( set != null ) {
            adminInfoVO.setRoleIds( new HashSet<Integer>( set ) );
        }

        return adminInfoVO;
    }

    @Override
    public AdminVO convert(AdminBO adminBO) {
        if ( adminBO == null ) {
            return null;
        }

        AdminVO adminVO = new AdminVO();

        adminVO.setId( adminBO.getId() );
        adminVO.setUsername( adminBO.getUsername() );
        adminVO.setNickname( adminBO.getNickname() );
        adminVO.setStatus( adminBO.getStatus() );
        adminVO.setCreateTime( adminBO.getCreateTime() );

        return adminVO;
    }

    @Override
    public CommonResult<AdminVO> convert2(CommonResult<AdminBO> result) {
        if ( result == null ) {
            return null;
        }

        CommonResult<AdminVO> commonResult = new CommonResult<AdminVO>();

        commonResult.setCode( result.getCode() );
        commonResult.setMessage( result.getMessage() );
        commonResult.setData( convert( result.getData() ) );

        return commonResult;
    }

    @Override
    public List<AdminRoleVO> convert(List<RoleBO> roleList) {
        if ( roleList == null ) {
            return null;
        }

        List<AdminRoleVO> list = new ArrayList<AdminRoleVO>( roleList.size() );
        for ( RoleBO roleBO : roleList ) {
            list.add( roleBOToAdminRoleVO( roleBO ) );
        }

        return list;
    }

    @Override
    public PageResult<AdminVO> convertAdminVOPage(PageResult<AdminBO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<AdminVO> pageResult = new PageResult<AdminVO>();

        pageResult.setList( adminBOListToAdminVOList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }

    @Override
    public List<Role> convertAdminVORoleList(Collection<RoleBO> list) {
        if ( list == null ) {
            return null;
        }

        List<Role> list1 = new ArrayList<Role>( list.size() );
        for ( RoleBO roleBO : list ) {
            list1.add( roleBOToRole( roleBO ) );
        }

        return list1;
    }

    protected AdminRoleVO roleBOToAdminRoleVO(RoleBO roleBO) {
        if ( roleBO == null ) {
            return null;
        }

        AdminRoleVO adminRoleVO = new AdminRoleVO();

        adminRoleVO.setId( roleBO.getId() );
        adminRoleVO.setName( roleBO.getName() );

        return adminRoleVO;
    }

    protected List<AdminVO> adminBOListToAdminVOList(List<AdminBO> list) {
        if ( list == null ) {
            return null;
        }

        List<AdminVO> list1 = new ArrayList<AdminVO>( list.size() );
        for ( AdminBO adminBO : list ) {
            list1.add( convert( adminBO ) );
        }

        return list1;
    }

    protected Role roleBOToRole(RoleBO roleBO) {
        if ( roleBO == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( roleBO.getId() );
        role.setName( roleBO.getName() );

        return role;
    }
}
