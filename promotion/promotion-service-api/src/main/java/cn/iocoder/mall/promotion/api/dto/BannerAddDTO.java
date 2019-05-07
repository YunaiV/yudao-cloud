package cn.iocoder.mall.promotion.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Banner 添加 DTO
 */
@Data
@Accessors(chain = true)
public class BannerAddDTO implements Serializable {

    @NotEmpty(message = "标题不能为空")
    @Length(min = 2, max = 32, message = "标题长度为 2-32 位")
    private String title;
    @NotEmpty(message = "跳转链接不能为空")
    @URL(message = "跳转链接格式不正确")
    @Length(max = 255, message = "跳转链接最大长度为 255 位")
    private String url;
    @NotEmpty(message = "图片链接不能为空")
    @URL(message = "图片链接格式不正确")
    @Length(max = 255, message = "图片链接最大长度为 255 位")
    private String picUrl;
    @NotNull(message = "排序不能为空")
    private Integer sort;
    @Length(max = 255, message = "备注最大长度为 255 位")
    private String memo;

}
