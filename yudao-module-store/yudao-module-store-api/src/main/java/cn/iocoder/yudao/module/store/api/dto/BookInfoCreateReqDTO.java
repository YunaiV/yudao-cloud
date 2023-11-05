package cn.iocoder.yudao.module.store.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Schema(description = "RPC Service - Create Book Request DTO")
@Data
public class BookInfoCreateReqDTO {
    @Schema(description = "订单编号", example = "223")
    private Long id;
    @Schema(description = "订单编号", example = "223")
    private Long isbn;
    @Schema(description = "订单编号", example = "223")
    private String title;
    @Schema(description = "订单编号", example = "223")
    private String author;
    @Schema(description = "订单编号", example = "223")
    private String press;
    @Schema(description = "订单编号", example = "223")
    private Date publishDate;
    @Schema(description = "订单编号", example = "223")
    private Float price;
    @Schema(description = "订单编号", example = "223")
    private String briefIntro;
    @Schema(description = "订单编号", example = "223")
    private String imgUrl;
}
