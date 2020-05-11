package cn.iocoder.mall.system.biz.dataobject.errorcode;

import cn.iocoder.mall.mybatis.dataobject.DeletableDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 错误码实体
 */
@TableName(value = "error_code")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ErrorCodeDO extends DeletableDO {
    /**
     * 错误码编号
     */
    private Integer id;
    /**
     * 错误码编码
     */
    private Integer code;
    /**
     * 错误码错误信息
     */
    private String message;
    /**
     * 错误码类型
     */
    private Integer type;

    // TODO FROM 芋艿 to 鱿鱼丝：增加一个分组字段。方便做归类
    // TODO FROM 芋艿 to 鱿鱼丝：增加个备注字段，方便做备注哈。

}
