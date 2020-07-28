package cn.iocoder.mall.managementweb.controller.product.vo.spu;

import lombok.*;
import io.swagger.annotations.*;
import java.util.*;

@ApiModel("商品 SPU Response VO")
@Data
public class ProductSpuRespVO {

    @ApiModelProperty(value = "SPU 编号", required = true)
    private Integer id;
    @ApiModelProperty(value = "SPU 名字", required = true, example = "芋艿")
    private String name;
    @ApiModelProperty(value = "卖点", required = true, example = "好吃好玩")
    private String sellPoint;
    @ApiModelProperty(value = "描述", required = true, example = "我是哈哈哈")
    private String description;
    @ApiModelProperty(value = "分类编号", required = true, example = "1")
    private Integer cid;
    @ApiModelProperty(value = "商品主图地址", required = true, example = "http://www.iocoder.cn/xxx.jpg", notes = "多个之间，使用逗号分隔")
    private List<String> picUrls;
    @ApiModelProperty(value = "是否上架商品", required = true, example = "true")
    private Boolean visible;
    @ApiModelProperty(value = "排序字段", required = true, example = "1024")
    private Integer sort;
    @ApiModelProperty(value = "价格", required = true, example = "233", notes = "单位：分")
    private Integer price;
    @ApiModelProperty(value = "库存数量", required = true, example = "1024")
    private Integer quantity;
    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

}
