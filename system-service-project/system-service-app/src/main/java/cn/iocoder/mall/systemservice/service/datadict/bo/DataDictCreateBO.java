package cn.iocoder.mall.systemservice.service.datadict.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
* 数据字典创建 BO
*/
@Data
@Accessors(chain = true)
public class DataDictCreateBO {

    /**
     * 大类枚举值
     */
    @NotEmpty(message = "大类枚举值不能为空")
    private String enumValue;
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
