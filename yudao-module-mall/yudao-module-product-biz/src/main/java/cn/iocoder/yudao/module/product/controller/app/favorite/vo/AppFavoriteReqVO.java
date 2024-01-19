package cn.iocoder.yudao.module.product.controller.app.favorite.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Schema(description = "用户 APP - 商品收藏的单个 Request VO") // 用于收藏、取消收藏、获取收藏
@Data
public class AppFavoriteReqVO {

    @Schema(description = "商品 SPU 编号", requiredMode = REQUIRED, example = "29502")
    @NotNull(message = "商品 SPU 编号不能为空")
    private Long spuId;

}
