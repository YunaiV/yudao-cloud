package cn.iocoder.mall.system.biz.dto.authorization;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collection;

/**
 * 角色模块 - 获得角色列表 DTO
 */
@Data
@Accessors(chain = true)
public class RoleGetListDTO {

    /**
     * 角色编号数组
     *
     * 如果传入空，则不进行角色编号的过滤
     */
    private Collection<Integer> ids;

}
