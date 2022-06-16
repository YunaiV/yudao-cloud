package cn.iocoder.mall.managementweb.controller.user.vo;

import cn.iocoder.common.framework.vo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ApiModel("用户分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserPageReqVO extends PageParam {

    @ApiModelProperty(value = "昵称", example = "丑艿艿", notes = "模糊匹配")
    private String nickname;
    @ApiModelProperty(value = "状态", example = "1", notes = "见 CommonStatusEnum 枚举")
    private Integer status;

}
