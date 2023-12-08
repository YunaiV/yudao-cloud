package cn.iocoder.yudao.module.product.api.comment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.util.List;

@Schema(description = "RPC 服务 - 商品评论创建 Request DTO")
@Data
public class ProductCommentCreateReqDTO {

    @Schema(description = "商品 SKU 编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "商品 SKU 编号不能为空")
    private Long skuId;
    @Schema(description = "订单编号", example = "223")
    private Long orderId;
    @Schema(description = "交易订单项编号", example = "666")
    private Long orderItemId;

    @Schema(description = "描述星级 1-5 分", requiredMode = Schema.RequiredMode.REQUIRED, example = "5")
    @NotNull(message = "描述星级不能为空")
    private Integer descriptionScores;
    @Schema(description = "服务星级 1-5 分", requiredMode = Schema.RequiredMode.REQUIRED, example = "5")
    @NotNull(message = "服务星级不能为空")
    private Integer benefitScores;
    @Schema(description = "评论内容", requiredMode = Schema.RequiredMode.REQUIRED, example = "好评")
    @NotNull(message = "评论内容不能为空")
    private String content;
    @Schema(description = "评论图片地址数组，以逗号分隔最多上传 9 张", example = "https://www.iocoder.cn/xxx.jpg,http://www.iocoder.cn/yyy.jpg")
    private List<String> picUrls;

    @Schema(description = "是否匿名", requiredMode = Schema.RequiredMode.REQUIRED, example = "true")
    @NotNull(message = "是否匿名不能为空")
    private Boolean anonymous;
    @Schema(description = "评价人", requiredMode = Schema.RequiredMode.REQUIRED, example = "888")
    @NotNull(message = "评价人不能为空")
    private Long userId;

}
