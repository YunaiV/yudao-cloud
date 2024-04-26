package cn.iocoder.yudao.module.product.api.property.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "RPC 服务 - 商品属性项的明细 Response DTO")
@Data
public class ProductPropertyValueDetailRespDTO {

    @Schema(description = "属性的编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long propertyId;
    @Schema(description = "属性的名字", requiredMode = Schema.RequiredMode.REQUIRED, example = "颜色")
    private String propertyName;

    @Schema(description = "属性值的编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "2048")
    private Long valueId;
    @Schema(description = "属性值的名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "红色")
    private String valueName;

}
