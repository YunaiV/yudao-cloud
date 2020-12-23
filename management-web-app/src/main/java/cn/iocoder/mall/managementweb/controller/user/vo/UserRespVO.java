package cn.iocoder.mall.managementweb.controller.user.vo;

import lombok.*;
import io.swagger.annotations.*;
import java.util.*;

@ApiModel("用户 Response VO")
@Data
public class UserRespVO {

    @ApiModelProperty(value = "用户编号", required = true, example = "1")
    private Integer id;
    @ApiModelProperty(value = "昵称", example = "丑艿艿")
    private String nickname;
    @ApiModelProperty(value = "头像", example = "http://www.iocoder.cn/xxx.jpg")
    private String avatar;
    @ApiModelProperty(value = "状态", required = true, example = "1", notes = "见 CommonStatusEnum 枚举")
    private Integer status;
    @ApiModelProperty(value = "手机号", required = true, example = "15601691399")
    private String mobile;
    @ApiModelProperty(value = "注册 IP", required = true, example = "127.0.0.1")
    private String createIp;
    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

}
