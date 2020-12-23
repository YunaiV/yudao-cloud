package cn.iocoder.mall.product.application.vo.admins;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@ApiModel(value = "商品规格明细分页 VO")
@Data
@Accessors(chain = true)
public class AdminsProductAttrPageVO {

    @ApiModelProperty(value = "规格数组", required = true)
    private List<AdminsProductAttrDetailVO> attrs;
    @ApiModelProperty(value = "总数", required = true)
    private Integer count;

}
