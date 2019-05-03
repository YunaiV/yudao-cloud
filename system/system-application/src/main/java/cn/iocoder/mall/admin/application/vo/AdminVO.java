package cn.iocoder.mall.admin.application.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@ApiModel("管理员 VO")
@Data
@Accessors(chain = true)
public class AdminVO {

    @ApiModelProperty(value = "管理员编号", required = true, example = "1")
    private Integer id;
    @ApiModelProperty(value = "登陆账号", required = true, example = "15601691300")
    private String username;
    @ApiModelProperty(value = "昵称", required = true, example = "小王")
    private String nickname;
    @ApiModelProperty(value = "账号状态", required = true, example = "1")
    private Integer status;
    @ApiModelProperty(value = "创建时间", required = true, example = "时间戳格式")
    private Date createTime;

}
