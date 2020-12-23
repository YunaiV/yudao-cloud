package cn.iocoder.mall.promotion.api.rpc.banner.dto;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.validator.InEnum;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Banner 更新 Request DTO
 */
@Data
@Accessors(chain = true)
public class BannerUpdateReqDTO implements Serializable {

    /**
     * 编号
     */
    @NotNull(message = "编号不能为空")
    private Integer id;
    /**
     * 标题
     */
    @NotEmpty(message = "标题不能为空")
    @Length(min = 2, max = 32, message = "标题长度为 2-32 位")
    private String title;
    /**
     * 跳转链接
     */
    @NotEmpty(message = "跳转链接不能为空")
    @URL(message = "跳转链接格式不正确")
    @Length(max = 255, message = "跳转链接最大长度为 255 位")
    private String url;
    /**
     * 图片链接
     */
    @NotEmpty(message = "图片链接不能为空")
    @URL(message = "图片链接格式不正确")
    @Length(max = 255, message = "图片链接最大长度为 255 位")
    private String picUrl;
    /**
     * 排序
     */
    @NotNull(message = "排序不能为空")
    private Integer sort;
    /**
     * 状态
     */
    @NotNull(message = "状态不能为空")
    @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}")
    private Integer status;
    /**
     * 备注
     */
    @Length(max = 255, message = "备注最大长度为 255 位")
    private String memo;

}
