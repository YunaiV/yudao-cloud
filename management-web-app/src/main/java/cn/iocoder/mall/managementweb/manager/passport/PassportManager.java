package cn.iocoder.mall.managementweb.manager.passport;

import cn.iocoder.common.framework.enums.UserTypeEnum;
import cn.iocoder.common.framework.util.CollectionUtils;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.managementweb.controller.passport.dto.PassportLoginDTO;
import cn.iocoder.mall.managementweb.controller.passport.vo.PassportAccessTokenVO;
import cn.iocoder.mall.managementweb.controller.passport.vo.PassportAdminMenuTreeNodeVO;
import cn.iocoder.mall.managementweb.controller.passport.vo.PassportAdminVO;
import cn.iocoder.mall.managementweb.controller.permission.vo.ResourceTreeNodeVO;
import cn.iocoder.mall.managementweb.convert.passport.AdminPassportConvert;
import cn.iocoder.mall.managementweb.convert.permission.ResourceConvert;
import cn.iocoder.mall.managementweb.manager.permission.ResourceManager;
import cn.iocoder.mall.systemservice.enums.permission.ResourceTypeEnum;
import cn.iocoder.mall.systemservice.rpc.admin.AdminFeign;
import cn.iocoder.mall.systemservice.rpc.admin.vo.AdminVO;
import cn.iocoder.mall.systemservice.rpc.oauth.OAuthFeign;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2AccessTokenRespDTO;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2CreateAccessTokenReqDTO;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2RefreshAccessTokenReqDTO;
import cn.iocoder.mall.systemservice.rpc.permission.ResourceFeign;
import cn.iocoder.mall.systemservice.rpc.permission.RoleFeign;
import cn.iocoder.mall.systemservice.rpc.permission.vo.ResourceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class PassportManager {


    @Autowired
    private AdminFeign adminFeign;
    @Autowired
    private OAuthFeign oAuthFeign;
    @Autowired
    private RoleFeign roleFeign;
    @Autowired
    private ResourceFeign resourceFeign;
    public PassportAccessTokenVO login(PassportLoginDTO loginDTO, String ip) {
        // 校验管理员密码
//        CommonResult<AdminVO> verifyPasswordResult = adminFeign.verifyPassword(AdminPassportConvert.INSTANCE.convert(loginDTO).setIp(ip));
        CommonResult<AdminVO> verifyPasswordResult = adminFeign.verifyPassword(AdminPassportConvert.INSTANCE.convert(loginDTO).setIp(ip));
        verifyPasswordResult.checkError();
        // 创建访问令牌
        CommonResult<OAuth2AccessTokenRespDTO> createAccessTokenResult = oAuthFeign.createAccessToken(
                new OAuth2CreateAccessTokenReqDTO().setUserId(verifyPasswordResult.getData().getId())
                        .setUserType(UserTypeEnum.ADMIN.getValue()).setCreateIp(ip));
        createAccessTokenResult.checkError();
        // 返回
        return AdminPassportConvert.INSTANCE.convert(createAccessTokenResult.getData());
    }

    public PassportAdminVO getAdmin(Integer adminId) {
        CommonResult<AdminVO> getAdminResult = adminFeign.getAdmin(adminId);
        getAdminResult.checkError();
        return AdminPassportConvert.INSTANCE.convert(getAdminResult.getData());
    }

    public PassportAccessTokenVO refreshToken(String refreshToken, String ip) {
        CommonResult<OAuth2AccessTokenRespDTO> refreshAccessTokenResult = oAuthFeign.refreshAccessToken(
                new OAuth2RefreshAccessTokenReqDTO().setRefreshToken(refreshToken).setCreateIp(ip));
        refreshAccessTokenResult.checkError();
        return AdminPassportConvert.INSTANCE.convert(refreshAccessTokenResult.getData());
    }

    /**
     * 获得指定管理员的权限列表
     *
     * @param adminId 管理员编号
     * @return 权限列表
     */
    public Set<String> listAdminPermission(Integer adminId) {
        // 获得管理员拥有的角色编号列表
        CommonResult<Set<Integer>> listAdminRoleIdsResult = roleFeign.listAdminRoleIds(adminId);
        listAdminRoleIdsResult.checkError();
        if (CollectionUtils.isEmpty(listAdminRoleIdsResult.getData())) {
            return Collections.emptySet();
        }
        // 获得角色拥有的资源列表
        CommonResult<List<ResourceVO>> resourceVOResult = resourceFeign.listRoleResource(
                listAdminRoleIdsResult.getData(), null);
        resourceVOResult.checkError();
        return CollectionUtils.convertSet(resourceVOResult.getData(), cn.iocoder.mall.systemservice.rpc.permission.vo.ResourceVO::getPermission);
    }

    /**
     * 获得管理员的菜单树
     *
     * @param adminId 管理员编号
     * @return 菜单树
     */
    public List<PassportAdminMenuTreeNodeVO> treeAdminMenu(Integer adminId) {
        // 获得管理员拥有的角色编号列表
        CommonResult<Set<Integer>> listAdminRoleIdsResult = roleFeign.listAdminRoleIds(adminId);
        listAdminRoleIdsResult.checkError();
        if (CollectionUtils.isEmpty(listAdminRoleIdsResult.getData())) {
            return Collections.emptyList();
        }
        // 获得角色拥有的资源（菜单）列表
        CommonResult<List<cn.iocoder.mall.systemservice.rpc.permission.vo.ResourceVO>> resourceVOResult = resourceFeign.listRoleResource(
                listAdminRoleIdsResult.getData(), ResourceTypeEnum.MENU.getType());
        resourceVOResult.checkError();
        // 构建菜单树
        return this.buildAdminMenuTree(resourceVOResult.getData());
    }

    /**
     * 构建菜单树
     *
     * @param resourceVOs 资源（都是菜单）列表
     * @return 菜单树
     */
    private List<PassportAdminMenuTreeNodeVO> buildAdminMenuTree(List<cn.iocoder.mall.systemservice.rpc.permission.vo.ResourceVO> resourceVOs) {
        List<ResourceTreeNodeVO> treeNodeVOS = ResourceManager.buildResourceTree(resourceVOs);
        // 虽然多了一层转换，但是可维护性更好。
        return ResourceConvert.INSTANCE.convert(treeNodeVOS);
    }

}
