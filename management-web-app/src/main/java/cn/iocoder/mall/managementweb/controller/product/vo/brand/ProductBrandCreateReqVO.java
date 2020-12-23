package cn.iocoder.mall.managementweb.controller.product.vo.brand;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("商品品牌创建 Request VO")
@Data
public class ProductBrandCreateReqVO {

    @ApiModelProperty(value = "品牌名称", required = true, example = "这个商品品牌很吊")
    private String name;
    @ApiModelProperty(value = "品牌描述", example = "这个商品描述很吊")
    private String description;
    @ApiModelProperty(value = "品牌名图片", example = "http://www.iocoder.cn/xx.jpg")
    private String picUrl;
    @ApiModelProperty(value = "状态", required = true, example = "1", notes = "见 CommonStatusEnum 枚举")
    private Integer status;

}
