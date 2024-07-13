package cn.iocoder.yudao.module.system.api.social.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * @see <a href="https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/qrcode-link/qr-code/getUnlimitedQRCode.html">获取不限制的小程序码</a>
 */
@Schema(description = "RPC 服务 - 获得获取小程序码 Request DTO")
@Data
public class SocialWxQrcodeReqDTO {

    /**
     * 页面路径不能携带参数（参数请放在scene字段里）
     */
    public static final String SCENE = "";
    /**
     * 二维码宽度
     */
    public static final Integer WIDTH = 430;
    /**
     * 自动配置线条颜色，如果颜色依然是黑色，则说明不建议配置主色调
     */
    public static final Boolean AUTO_COLOR = true;
    /**
     * 检查 page 是否存在
     */
    public static final Boolean CHECK_PATH = true;
    /**
     * 是否需要透明底色
     *
     * hyaline 为 true 时，生成透明底色的小程序码
     */
    public static final Boolean HYALINE = true;

    @Schema(description = "场景", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
    @NotEmpty(message = "场景不能为空")
    private String scene;

    @Schema(description = "页面路径", requiredMode = Schema.RequiredMode.REQUIRED, example = "pages/goods/index")
    @NotEmpty(message = "页面路径不能为空")
    private String path;

    @Schema(description = "二维码宽度", example = "430")
    private Integer width;

    @Schema(description = "是否需要透明底色", example = "true")
    private Boolean autoColor;

    @Schema(description = "是否检查 page 是否存在", example = "true")
    private Boolean checkPath;

    @Schema(description = "是否需要透明底色", example = "true")
    private Boolean hyaline;

}
