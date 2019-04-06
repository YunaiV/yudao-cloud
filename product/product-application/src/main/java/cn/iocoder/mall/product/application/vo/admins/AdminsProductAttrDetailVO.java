package cn.iocoder.mall.product.application.vo.admins;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@ApiModel(value = "商品规格明细 VO", description = "带有规格值数组")
@Data
@Accessors(chain = true)
public class AdminsProductAttrDetailVO {

    @ApiModelProperty(value = "规格编号", required = true, example = "1")
    private Integer id;
    @ApiModelProperty(value = "规格名", required = true, example = "颜色")
    private String name;
    @ApiModelProperty(value = "状态", required = true, example = "1")
    private Integer status;
    @ApiModelProperty(value = "创建时间", required = true, example = "时间戳")
    private Date createTime;
    @ApiModelProperty(value = "规格值数组", required = true)
    private List<AdminsProductAttrValueDetailVO> values;

}
