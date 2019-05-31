package cn.iocoder.mall.admin.application.convert;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.bo.resource.ResourceBO;
import cn.iocoder.mall.admin.application.vo.admin.AdminMenuTreeNodeVO;
import cn.iocoder.mall.admin.application.vo.resource.ResourceTreeNodeVO;
import cn.iocoder.mall.admin.application.vo.resource.ResourceVO;
import cn.iocoder.mall.admin.application.vo.role.RoleResourceTreeNodeVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-31T18:11:49+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class ResourceConvertImpl implements ResourceConvert {

    @Override
    public AdminMenuTreeNodeVO convert(ResourceBO resourceBO) {
        if ( resourceBO == null ) {
            return null;
        }

        AdminMenuTreeNodeVO adminMenuTreeNodeVO = new AdminMenuTreeNodeVO();

        adminMenuTreeNodeVO.setId( resourceBO.getId() );
        adminMenuTreeNodeVO.setHandler( resourceBO.getHandler() );
        adminMenuTreeNodeVO.setPid( resourceBO.getPid() );
        adminMenuTreeNodeVO.setSort( resourceBO.getSort() );
        adminMenuTreeNodeVO.setDisplayName( resourceBO.getDisplayName() );

        return adminMenuTreeNodeVO;
    }

    @Override
    public ResourceTreeNodeVO convert2(ResourceBO resourceBO) {
        if ( resourceBO == null ) {
            return null;
        }

        ResourceTreeNodeVO resourceTreeNodeVO = new ResourceTreeNodeVO();

        resourceTreeNodeVO.setId( resourceBO.getId() );
        resourceTreeNodeVO.setType( resourceBO.getType() );
        resourceTreeNodeVO.setSort( resourceBO.getSort() );
        resourceTreeNodeVO.setDisplayName( resourceBO.getDisplayName() );
        resourceTreeNodeVO.setPid( resourceBO.getPid() );
        resourceTreeNodeVO.setHandler( resourceBO.getHandler() );
        resourceTreeNodeVO.setIcon( resourceBO.getIcon() );
        List<String> list = resourceBO.getPermissions();
        if ( list != null ) {
            resourceTreeNodeVO.setPermissions( new ArrayList<String>( list ) );
        }
        resourceTreeNodeVO.setCreateTime( resourceBO.getCreateTime() );

        return resourceTreeNodeVO;
    }

    @Override
    public RoleResourceTreeNodeVO convert4(ResourceBO resourceBO) {
        if ( resourceBO == null ) {
            return null;
        }

        RoleResourceTreeNodeVO roleResourceTreeNodeVO = new RoleResourceTreeNodeVO();

        roleResourceTreeNodeVO.setId( resourceBO.getId() );
        roleResourceTreeNodeVO.setHandler( resourceBO.getHandler() );
        roleResourceTreeNodeVO.setPid( resourceBO.getPid() );
        roleResourceTreeNodeVO.setSort( resourceBO.getSort() );
        roleResourceTreeNodeVO.setDisplayName( resourceBO.getDisplayName() );

        return roleResourceTreeNodeVO;
    }

    @Override
    public ResourceVO convert3(ResourceBO resourceBO) {
        if ( resourceBO == null ) {
            return null;
        }

        ResourceVO resourceVO = new ResourceVO();

        resourceVO.setId( resourceBO.getId() );
        resourceVO.setType( resourceBO.getType() );
        resourceVO.setSort( resourceBO.getSort() );
        resourceVO.setDisplayName( resourceBO.getDisplayName() );
        resourceVO.setCreateTime( resourceBO.getCreateTime() );
        resourceVO.setPid( resourceBO.getPid() );
        resourceVO.setHandler( resourceBO.getHandler() );

        return resourceVO;
    }

    @Override
    public CommonResult<ResourceVO> convert3(CommonResult<ResourceBO> resourceBO) {
        if ( resourceBO == null ) {
            return null;
        }

        CommonResult<ResourceVO> commonResult = new CommonResult<ResourceVO>();

        commonResult.setCode( resourceBO.getCode() );
        commonResult.setMessage( resourceBO.getMessage() );
        commonResult.setData( convert3( resourceBO.getData() ) );

        return commonResult;
    }
}
