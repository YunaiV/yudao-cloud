package cn.iocoder.mall.system.biz.dto.smsSign;

import cn.iocoder.common.framework.vo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * page 短信模板 query
 *
 * @author Sin
 * @time 2019/5/19 4:32 PM
 */
@Data
@Accessors(chain = true)
@ApiModel("短信服务查询")
public class ListSmsSignDTO extends PageParam {

    @ApiModelProperty("编号")
    private Integer id;

    @ApiModelProperty("签名")
    private String sign;

    @ApiModelProperty("申请状态")
    private Integer applyStatus;

}
