package cn.iocoder.mall.admin.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 数据字典更新 DTO
 */
@Data
@Accessors(chain = true)
public class DataDictUpdateDTO {

    /**
     * 编号
     */
    @NotNull(message = "编号不能为空")
    private Integer id;
    /**
     * 小类数值
     */
    @NotEmpty(message = "小类数值不能为空")
    private String value;
    /**
     * 展示名
     */
    @NotEmpty(message = "展示名不能为空")
    private String displayName;
    /**
     * 排序值
     */
    @NotNull(message = "排序值不能为空")
    private Integer sort;
    /**
     * 备注
     */
    private String memo;

}
