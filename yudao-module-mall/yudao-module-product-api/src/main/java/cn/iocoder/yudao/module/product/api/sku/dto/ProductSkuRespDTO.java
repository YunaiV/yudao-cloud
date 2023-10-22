package cn.iocoder.yudao.module.product.api.sku.dto;

import cn.iocoder.yudao.module.product.api.property.dto.ProductPropertyValueDetailRespDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "RPC 服务 - 商品 SKU 信息 Response DTO")
@Data
public class ProductSkuRespDTO {

    @Schema(description = "商品 SKU 编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long id;
    @Schema(description = "SPU 编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "2048")
    private Long spuId;

    @Schema(description = "属性数组", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<ProductPropertyValueDetailRespDTO> properties;

    @Schema(description = "销售价格，单位：分", requiredMode = Schema.RequiredMode.REQUIRED, example = "100")
    private Integer price;
    @Schema(description = "市场价，单位：分", requiredMode = Schema.RequiredMode.REQUIRED, example = "200")
    private Integer marketPrice;
    @Schema(description = "成本价，单位：分", requiredMode = Schema.RequiredMode.REQUIRED, example = "300")
    private Integer costPrice;
    @Schema(description = "SKU 的条形码", requiredMode = Schema.RequiredMode.REQUIRED, example = "123456789")
    private String barCode;
    @Schema(description = "图片地址", requiredMode = Schema.RequiredMode.REQUIRED, example = "https://www.iocoder.cn/xxx.jpg")
    private String picUrl;

    @Schema(description = "库存", requiredMode = Schema.RequiredMode.REQUIRED, example = "100")
    private Integer stock;
    @Schema(description = "商品重量，单位：kg 千克", requiredMode = Schema.RequiredMode.REQUIRED, example = "1.5")
    private Double weight;
    @Schema(description = "商品体积，单位：m^3 平米", requiredMode = Schema.RequiredMode.REQUIRED, example = "3.0")
    private Double volume;

    @Schema(description = "一级分销的佣金，单位：分", requiredMode = Schema.RequiredMode.REQUIRED, example = "550")
    private Integer firstBrokeragePrice;
    @Schema(description = "二级分销的佣金，单位：分", requiredMode = Schema.RequiredMode.REQUIRED, example = "250")
    private Integer secondBrokeragePrice;

}
