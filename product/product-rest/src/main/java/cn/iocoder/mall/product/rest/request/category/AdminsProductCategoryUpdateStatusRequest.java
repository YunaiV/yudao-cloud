package cn.iocoder.mall.product.rest.request.category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @Author: jiangweifan
 * @Date: 2020/5/6
 * @Description: 商品分类 - 更新商品分类状态Request
 */
@ApiModel("更新商品分类状态Request")
@Data
@Accessors(chain = true)
public class AdminsProductCategoryUpdateStatusRequest {
    /**
     * 商品分类编号
     */
    @ApiModelProperty(name = "id", value = "分类编号", required = true, example = "1")
    @NotNull(message = "编号不能为空")
    private Integer id;
    /**
     * 更新状态
     */
    @ApiModelProperty(name = "status", value = "状态。1 - 开启；2 - 禁用", required = true, example = "1")
    @NotNull(message = "状态不能为空")
    private Integer status;
}
