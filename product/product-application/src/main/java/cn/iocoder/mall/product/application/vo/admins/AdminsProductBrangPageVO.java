package cn.iocoder.mall.product.application.vo.admins;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 商品品牌分页 BO
 */
@ApiModel("商品品牌分页")
@Data
@Accessors(chain = true)
public class AdminsProductBrangPageVO {

    @ApiModelProperty(value = "品牌数组", required = true)
    private List<AdminsProductBrandVO> brands;


    @ApiModelProperty(value = "总数", required = true)
    private Integer count;

}
