package cn.iocoder.mall.promotion.application.vo.users;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@ApiModel("优惠劵分页 VO")
@Data
@Accessors(chain = true)
public class UsersCouponCardPageVO {

    @ApiModelProperty(value = "优惠劵数组")
    private List<UsersCouponCardVO> list;
    @ApiModelProperty(value = "优惠劵总数")
    private Integer total;

}
