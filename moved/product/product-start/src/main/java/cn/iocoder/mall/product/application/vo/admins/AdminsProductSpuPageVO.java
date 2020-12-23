package cn.iocoder.mall.product.application.vo.admins;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@ApiModel("商品 SPU 分页 VO")
@Data
@Accessors(chain = true)
public class AdminsProductSpuPageVO {

    @ApiModelProperty(value = "spu 数组", required = true)
    private List<AdminsProductSpuVO> list;
    @ApiModelProperty(value = "总数", required = true)
    private Integer total;

}
