package cn.iocoder.mall.managementweb.controller.product.vo.spu;

import lombok.*;
import io.swagger.annotations.*;
import java.util.*;

@ApiModel("商品 SPU Response VO")
@Data
public class ProductSpuRespVO {

    @ApiModelProperty(value = "SPU 编号", required = true)
    private Integer id;
    @ApiModelProperty(value = "SPU 名字", required = true)
    private String name;
    @ApiModelProperty(value = "卖点", required = true)
    private String sellPoint;
    @ApiModelProperty(value = "描述", required = true)
    private String description;
    @ApiModelProperty(value = "分类编号", required = true)
    private Integer cid;
    @ApiModelProperty(value = "商品主图地址", required = true)
    private List<String> picUrls;
    @ApiModelProperty(value = "是否上架商品", required = true)
    private Boolean visible;
    @ApiModelProperty(value = "排序字段", required = true)
    private Integer sort;
    @ApiModelProperty(value = "价格", required = true)
    private Integer price;
    @ApiModelProperty(value = "库存数量", required = true)
    private Integer quantity;
    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;
    @ApiModelProperty(value = "最后更新时间", required = true)
    private Date updateTime;

}
