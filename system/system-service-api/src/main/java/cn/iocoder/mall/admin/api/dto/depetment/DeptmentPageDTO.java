package cn.iocoder.mall.admin.api.dto.depetment;

import cn.iocoder.common.framework.vo.PageParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-06-21
 * @time: 00:22
 */
@Data
public class DeptmentPageDTO extends PageParam {
    @ApiModelProperty(value = "根部门名字", example = "研发部")
    private String name;
}
