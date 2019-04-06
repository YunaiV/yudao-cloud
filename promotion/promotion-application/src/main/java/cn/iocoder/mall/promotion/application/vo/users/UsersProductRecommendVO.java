package cn.iocoder.mall.promotion.application.vo.users;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@ApiModel(value = "商品推荐 VO", description = "不包括 SKU 信息 VO")
@Data
@Accessors(chain = true)
public class UsersProductRecommendVO {

    @ApiModelProperty(value = "商品 SPU 编号", required = true, example = "1")
    private Integer id;

    // ========== 基本信息 =========
    @ApiModelProperty(value = "SPU 名字", required = true, example = "厮大牛逼")
    private String name;
    @ApiModelProperty(value = "卖点", required = true, example = "各种 MQ 骚操作")
    private String sellPoint;
    @ApiModelProperty(value = "商品主图地址的数组", required = true, example = "http://www.iocoder.cn")
    private List<String> picUrls;

    // ========== Sku 相关字段 =========
    /**
     * 价格
     *
     * 目前的计算方式是，以 Sku 最小价格为准
     */
    private Integer price;

}
