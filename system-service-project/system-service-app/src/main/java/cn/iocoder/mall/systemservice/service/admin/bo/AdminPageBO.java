package cn.iocoder.mall.systemservice.service.admin.bo;

import cn.iocoder.common.framework.vo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AdminPageBO extends PageParam {

    /**
     * 真实名字，模糊匹配
     */
    private String name;
    /**
     * 部门编号
     */
    private Integer departmentId;

}
