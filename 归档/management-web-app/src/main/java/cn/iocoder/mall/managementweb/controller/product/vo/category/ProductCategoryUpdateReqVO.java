package cn.iocoder.mall.managementweb.controller.product.vo.category;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.validator.InEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ApiModel("商品分类更新 Request VO")
@Data
public class ProductCategoryUpdateReqVO {

    @ApiModelProperty(value = "分类编号", required = true, example = "1")
    @NotNull(message = "分类编号不能为空")
    private Integer id;
    @ApiModelProperty(value = "父分类编号", required = true, example = "0")
    @NotNull(message = "父分类编号不能为空")
    private Integer pid;
    @ApiModelProperty(value = "分类名称", required = true, example = "手机")
    @NotEmpty(message = "分类名称不能为空")
    private String name;
    @ApiModelProperty(value = "分类描述", required = true, example = "这个商品很吊")
    private String description;
    @ApiModelProperty(value = "分类图片", notes = "一般情况下，只有根分类才有图片", example = "http://www.iocoder.cn/xx.jpg")
    private String picUrl;
    @ApiModelProperty(value = "分类排序", required = true, example = "10")
    @NotNull(message = "分类排序不能为空")
    private Integer sort;
    @ApiModelProperty(value = "状态", required = true, example = "1", notes = "见 CommonStatusEnum 枚举")
    @NotNull(message = "状态不能为空")
    @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}")
    private Integer status;

}
