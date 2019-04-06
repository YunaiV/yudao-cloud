package cn.iocoder.mall.promotion.application.vo.admins;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@ApiModel("优惠劵（码）分页 VO")
@Data
@Accessors(chain = true)
public class AdminsCouponTemplatePageVO {

    @ApiModelProperty(value = "优惠劵（码）数组")
    private List<AdminsCouponTemplateVO> list;
    @ApiModelProperty(value = "优惠劵（码）总数")
    private Integer total;

}
