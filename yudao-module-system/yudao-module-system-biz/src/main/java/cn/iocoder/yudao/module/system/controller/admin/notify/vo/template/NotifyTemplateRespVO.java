package cn.iocoder.yudao.module.system.controller.admin.notify.vo.template;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@ApiModel("管理后台 - 站内信模版 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class NotifyTemplateRespVO extends NotifyTemplateBaseVO {

    @ApiModelProperty(value = "ID", required = true, example = "1024")
    private Long id;

    @ApiModelProperty(value = "参数数组", example = "name,code")
    private List<String> params;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

}
