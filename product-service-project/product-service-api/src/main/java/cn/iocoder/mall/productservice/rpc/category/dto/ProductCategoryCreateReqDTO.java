package cn.iocoder.mall.productservice.rpc.category.dto;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.validator.InEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
* 商品分类创建 Request DTO
*/
@Data
@Accessors(chain = true)
public class ProductCategoryCreateReqDTO implements Serializable {

    /**
     * 父分类编号
     */
    @NotNull(message = "父分类编号不能为空")
    private Integer pid;
    /**
     * 分类名称
     */
    @NotEmpty(message = "分类名称不能为空")
    private String name;
    /**
     * 分类描述
     */
    private String description;
    /**
     * 分类图片
     */
    private String picUrl;
    /**
     * 分类排序
     */
    @NotNull(message = "分类排序不能为空")
    private Integer sort;
    /**
     * 状态
     */
    @NotNull(message = "状态不能为空")
    @InEnum(value = CommonStatusEnum.class, message = "状态必须是 {value}")
    private Integer status;

}
