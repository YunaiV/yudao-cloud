package cn.iocoder.mall.demo.rpc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("Demo 商品 DTO")
@Data
public class DemoProductDTO {

    @ApiModelProperty(value = "编号", required = true, example = "1")
    private Integer id;

    @ApiModelProperty(value = "华为 Mate30 Pro", required = true, example = "小王")
    private String name;

    @ApiModelProperty(value = "价格，单位：分", required = true, example = "10")
    private Integer price;
    @ApiModelProperty(value = "库存数量", required = true, example = "100")
    private Integer quantity;

}
