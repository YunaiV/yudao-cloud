package cn.iocoder.mall.admin.api;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.bo.OAuth2AccessTokenBO;
import cn.iocoder.mall.admin.api.bo.OAuth2AuthenticationBO;

import java.util.Set;

public interface OAuth2Service {

    CommonResult<OAuth2AccessTokenBO> getAccessToken(String username, String password);

    /**
     * 校验访问令牌，获取身份信息( 不包括 accessToken 等等 )
     *
     * @param accessToken 访问令牌
     * @return 授权信息
     */
    CommonResult<OAuth2AuthenticationBO> checkToken(String accessToken);

    /**
     * 校验权限（鉴权）
     *
     * @param adminId 管理员编号
     * @param roleIds 管理员拥有的角色编号的集合
     * @param url 指定 URL
     * @return 是否有权限
     */
    CommonResult<Boolean> checkPermission(Integer adminId, Set<Integer> roleIds, String url);

    // TODO @see 刷新 token

    // TODO @see 移除 token

}