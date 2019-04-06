package cn.iocoder.mall.user.application.vo.admins;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@ApiModel("用户 VO")
@Data
@Accessors(chain = true)
public class AdminsUserVO {

    @ApiModelProperty(value = "用户编号", required = true, example = "1")
    private Integer id;
    @ApiModelProperty(value = "手机号", required = true, example = "15601691300")
    private String mobile;
    @ApiModelProperty(value = "昵称", required = true, example = "小王")
    private String nickname;
    @ApiModelProperty(value = "头像", required = true, example = "http://www.iocoder.cn/xxx.jpg")
    private String avatar;
    @ApiModelProperty(value = "账号状态", required = true, example = "1")
    private Integer status;
    @ApiModelProperty(value = "创建时间", required = true, example = "时间戳格式")
    private Date createTime;

}
