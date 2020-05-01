package cn.iocoder.mall.system.biz.dto.admin;

import cn.iocoder.common.framework.vo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 管理员模块 - 管理员分页 DTO
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
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
