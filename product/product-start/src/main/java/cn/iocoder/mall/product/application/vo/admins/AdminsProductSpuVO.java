package cn.iocoder.mall.product.application.vo.admins;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@ApiModel(value = "商品 SPU VO", description = "不包括 SKU 信息 VO")
@Data
@Accessors(chain = true)
public class AdminsProductSpuVO {

    @ApiModelProperty(value = "SPU 编号", required = true, example = "1")
    private Integer id;

    // ========== 基本信息 =========
    @ApiModelProperty(value = "SPU 名字", required = true, example = "厮大牛逼")
    private String name;
    @ApiModelProperty(value = "卖点", required = true, example = "各种 MQ 骚操作")
    private String sellPoint;
    @ApiModelProperty(value = "描述", required = true, example = "你就说强不强")
    private String description;
    @ApiModelProperty(value = "分类编号", required = true, example = "反正我是信了")
    private Integer cid;
    @ApiModelProperty(value = "商品主图地址的数组", required = true, example = "http://www.iocoder.cn")
    private List<String> picUrls;
    @ApiModelProperty(value = "库存数量", required = true, example = "10")
    private Integer quantity;

    // ========== 其他信息 =========
    @ApiModelProperty(value = "是否上架商品（是否可见）", required = true, example = "true")
    private Boolean visible;
    @ApiModelProperty(value = "排序字段", required = true, example = "10")
    private Integer sort;

}
