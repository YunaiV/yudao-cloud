package cn.iocoder.mall.promotion.application.vo.admins;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@ApiModel("商品推荐分页 VO")
@Data
@Accessors(chain = true)
public class AdminsProductRecommendPageVO {

    @ApiModelProperty(value = "商品推荐数组")
    private List<AdminsProductRecommendVO> list;
    @ApiModelProperty(value = "商品推荐总数")
    private Integer total;

}
