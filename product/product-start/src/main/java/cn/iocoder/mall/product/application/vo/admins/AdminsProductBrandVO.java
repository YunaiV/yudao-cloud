package cn.iocoder.mall.product.application.vo.admins;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 商品品牌 VO
 */

@ApiModel(value = "商品品牌 VO")
@Data
@Accessors(chain = true)
public class AdminsProductBrandVO {

    /**
     * 品牌编号
     */
    @ApiModelProperty(value = "品牌编号", required = true, example = "1")
    private Integer id;


    @ApiModelProperty(value = "名称", required = true, example = "安踏")
    private String name;


    @ApiModelProperty(value = "描述", required = true, example = "安踏拖鞋")
    private String description;


    @ApiModelProperty(value = "图片地址", required = true, example = "http://www.iocoder.cn")
    private String picUrl;

    @ApiModelProperty(value = "状态 1-开启 2-禁用", required = true, example = "1")
    private Integer status;

}
