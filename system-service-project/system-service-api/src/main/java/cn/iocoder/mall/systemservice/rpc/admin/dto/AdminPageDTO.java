package cn.iocoder.mall.systemservice.rpc.admin.dto;

import cn.iocoder.common.framework.vo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 管理员分页查询 DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AdminPageDTO extends PageParam {

    /**
     * 真实名字，模糊匹配
     */
    private String name;
    /**
     * 部门编号
     */
    private Integer departmentId;

}
