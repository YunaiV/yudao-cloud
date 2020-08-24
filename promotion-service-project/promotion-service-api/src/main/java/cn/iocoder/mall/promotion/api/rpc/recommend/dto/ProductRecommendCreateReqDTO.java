package cn.iocoder.mall.promotion.api.rpc.recommend.dto;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.validator.InEnum;
import cn.iocoder.mall.promotion.api.enums.recommend.ProductRecommendTypeEnum;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 商品推荐创建 Request DTO
 */
@Data
@Accessors(chain = true)
public class ProductRecommendCreateReqDTO implements Serializable {

    /**
     * 推荐类型
     */
    @InEnum(value = ProductRecommendTypeEnum.class, message = "修改推荐类型必须是 {value}")
    @NotNull(message = "推荐类型不能为空")
    private Integer type;
    /**
     * 商品编号
     */
    @NotNull(message = "商品编号不能为空")
    private Integer productSpuId;
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
