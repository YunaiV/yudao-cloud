package cn.iocoder.mall.shopweb.controller.product.vo.category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("商品分类 Response VO")
@Data
public class ProductCategoryRespVO {

    @ApiModelProperty(value = "分类编号", required = true, example = "1")
    private Integer id;
    @ApiModelProperty(value = "分类名称", required = true, example = "手机")
    private String name;
    @ApiModelProperty(value = "分类图片", notes = "一般情况下，只有根分类才有图片", example = "http://www.iocoder.cn/xx.jpg")
    private String picUrl;

}
