package cn.iocoder.yudao.module.product.api.sku.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import java.util.List;

@Schema(description = "RPC 服务 - 商品 SKU 更新库存 Request DTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSkuUpdateStockReqDTO {

    @Schema(description = "商品 SKU 数组", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "商品 SKU 不能为空")
    private List<Item> items;

    @Data
    public static class Item {

        @Schema(description = "商品 SKU 编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
        @NotNull(message = "商品 SKU 编号不能为空")
        private Long id;

        @Schema(description = "库存变化数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "100")
        @NotNull(message = "库存变化数量不能为空")
        private Integer incrCount; // 正数：增加库存；负数：扣减库存

    }

}
