package cn.iocoder.mall.managementweb.controller.product.vo.attr;

import lombok.*;
import io.swagger.annotations.*;
import java.util.*;

@ApiModel("商品规格值 Response VO")
@Data
public class ProductAttrValueRespVO {

    @ApiModelProperty(value = "规格值编号", required = true, example = "1")
    private Integer id;
    @ApiModelProperty(value = "规格键编号", required = true, example = "2")
    private Integer attrKeyId;
    @ApiModelProperty(value = "规格值名字", required = true, example = "XXL")
    private String name;
    @ApiModelProperty(value = "状态", required = true, example = "1", notes = "见 CommonStatusEnum 枚举")
    private Integer status;
    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

}
