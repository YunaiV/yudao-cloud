package cn.iocoder.mall.system.api.dto.admin;

import cn.iocoder.common.framework.vo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel(value = "管理员分页 DTO")
@Data
@Accessors(chain = true)
public class AdminPageDTO extends PageParam {

    @ApiModelProperty(value = "昵称，模糊匹配", example = "小王")
    private String nickname;


    @ApiModelProperty(value = "所在部门ID")
    private Integer deptmentId;

}
