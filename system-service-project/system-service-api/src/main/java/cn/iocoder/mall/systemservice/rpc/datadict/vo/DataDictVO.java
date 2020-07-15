package cn.iocoder.mall.systemservice.rpc.datadict.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
* 数据字典 VO
*/
@Data
@Accessors(chain = true)
public class DataDictVO implements Serializable {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 大类枚举值
     */
    private String enumValue;
    /**
     * 小类数值
     */
    private String value;
    /**
     * 展示名
     */
    private String displayName;
    /**
     * 排序值
     */
    private Integer sort;
    /**
     * 备注
     */
    private String memo;
    /**
     * 创建时间
     */
    private Date createTime;

}
