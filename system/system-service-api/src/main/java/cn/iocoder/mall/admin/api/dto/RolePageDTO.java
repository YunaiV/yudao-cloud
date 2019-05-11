package cn.iocoder.mall.admin.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 角色分页 DTO
 */
@Data
@Accessors(chain = true)
public class RolePageDTO implements Serializable {

    private Integer pageNo;
    private Integer pageSize;
    private String name;

    public Integer getPageNo() {
        return pageNo;
    }

    public RolePageDTO setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public RolePageDTO setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public String getName() {
        return name;
    }

    public RolePageDTO setName(String name) {
        this.name = name;
        return this;
    }
}
