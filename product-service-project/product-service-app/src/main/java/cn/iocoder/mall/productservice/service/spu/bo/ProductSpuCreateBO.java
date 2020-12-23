package cn.iocoder.mall.productservice.service.spu.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 商品 SPU 创建 BO
*/
@Data
@Accessors(chain = true)
public class ProductSpuCreateBO {

    /**
     * SPU 名字
     */
    @NotEmpty(message = "SPU 名字不能为空")
    private String name;
    /**
     * 卖点
     */
    @NotEmpty(message = "卖点不能为空")
    private String sellPoint;
    /**
     * 描述
     */
    @NotEmpty(message = "描述不能为空")
    private String description;
    /**
     * 分类编号
     */
    @NotNull(message = "分类编号不能为空")
    private Integer cid;
    /**
     * 商品主图地址
     */
    @NotEmpty(message = "商品主图地址不能为空")
    private List<String> picUrls;
    /**
     * 是否上架商品
     */
    @NotNull(message = "是否上架商品不能为空")
    private Boolean visible;
    /**
     * 排序字段
     */
    @NotNull(message = "排序字段不能为空")
    private Integer sort;
    /**
     * 价格
     */
    @NotNull(message = "价格不能为空")
    private Integer price;
    /**
     * 库存数量
     */
    @NotNull(message = "库存数量不能为空")
    private Integer quantity;

}
