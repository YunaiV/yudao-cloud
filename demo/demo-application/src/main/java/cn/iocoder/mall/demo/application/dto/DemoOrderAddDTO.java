package cn.iocoder.mall.demo.application.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("Demo 订单添加 DTO")
@Data
public class DemoOrderAddDTO {

    @ApiModelProperty(value = "商品编号", required = true, example = "1")
    private Integer productId;

}
