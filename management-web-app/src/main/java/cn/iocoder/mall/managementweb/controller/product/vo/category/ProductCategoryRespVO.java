package cn.iocoder.mall.managementweb.controller.product.vo.category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel("商品分类 Response VO")
@Data
public class ProductCategoryRespVO {

    @ApiModelProperty(value = "分类编号", required = true, example = "1")
    private Integer id;
    @ApiModelProperty(value = "父分类编号", required = true, example = "0")
    private Integer pid;
    @ApiModelProperty(value = "分类名称", required = true, example = "手机")
    private String name;
    @ApiModelProperty(value = "分类描述", required = true, example = "这个商品分类很吊")
    private String description;
    @ApiModelProperty(value = "分类图片", notes = "一般情况下，只有根分类才有图片", example = "http://www.iocoder.cn/xx.jpg")
    private String picUrl;
    @ApiModelProperty(value = "分类排序", required = true, example = "10")
    private Integer sort;
    @ApiModelProperty(value = "状态", required = true, example = "1", notes = "见 CommonStatusEnum 枚举")
    private Integer status;
    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

}
