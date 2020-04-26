package cn.iocoder.mall.system.biz.dto.authorization;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collection;

/**
 * 资源模块 - 获得资源列表 DTO
 */
@Data
@Accessors(chain = true)
public class ResourceGetListDTO {

    /**
     * 资源编号数组
     */
    private Collection<Integer> ids;

    /**
     * 资源类型
     */
    private Integer type;

}
