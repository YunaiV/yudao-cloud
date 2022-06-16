package cn.iocoder.mall.system.api.dto.sms;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * page 短信模板 query
 *
 * @author Sin
 * @time 2019/5/19 4:32 PM
 */
@Data
@Accessors(chain = true)
@ApiModel("短信服务查询")
public class PageQuerySmsSignDTO implements Serializable {

    @ApiModelProperty("每页大小")
    @NotNull
    private Integer size;

    @ApiModelProperty("当前页")
    @NotNull
    private Integer current;

    @ApiModelProperty("编号")
    private Integer id;

    @ApiModelProperty("签名")
    private String sign;

    @ApiModelProperty("申请状态")
    private Integer applyStatus;
}
