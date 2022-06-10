package cn.iocoder.yudao.module.system.api.user;

import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
import cn.iocoder.yudao.module.system.enums.ApiConstants;
import io.swagger.annotations.Api;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@FeignClient(name = ApiConstants.NAME) // TODO 芋艿：fallbackFactory =
@Api(tags = "RPC 服务 - 管理员用户")
public interface AdminUserApi {

    String PREFIX = ApiConstants.PREFIX + "/user";

    /**
     * 通过用户 ID 查询用户
     *
     * @param id 用户ID
     * @return 用户对象信息
     */
    @GetMapping(PREFIX + "/get")
    AdminUserRespDTO getUser(Long id);

    /**
     * 通过用户 ID 查询用户们
     *
     * @param ids 用户 ID 们
     * @return 用户对象信息
     */
    @GetMapping(PREFIX + "/list")
    List<AdminUserRespDTO> getUsers(Collection<Long> ids);

    /**
     * 获得指定部门的用户数组
     *
     * @param deptIds 部门数组
     * @return 用户数组
     */
    @GetMapping(PREFIX + "/list-by-dept-id")
    List<AdminUserRespDTO> getUsersByDeptIds(Collection<Long> deptIds);

    /**
     * 获得指定岗位的用户数组
     *
     * @param postIds 岗位数组
     * @return 用户数组
     */
    @GetMapping(PREFIX + "/list-by-post-id")
    List<AdminUserRespDTO> getUsersByPostIds(Collection<Long> postIds);

    /**
     * 获得用户 Map
     *
     * @param ids 用户编号数组
     * @return 用户 Map
     */
    default Map<Long, AdminUserRespDTO> getUserMap(Collection<Long> ids) {
        List<AdminUserRespDTO> users = getUsers(ids);
        return CollectionUtils.convertMap(users, AdminUserRespDTO::getId);
    }

    /**
     * 校验用户们是否有效。如下情况，视为无效：
     * 1. 用户编号不存在
     * 2. 用户被禁用
     *
     * @param ids 用户编号数组
     */
    @GetMapping(PREFIX + "/valid")
    void validUsers(Set<Long> ids);

}
