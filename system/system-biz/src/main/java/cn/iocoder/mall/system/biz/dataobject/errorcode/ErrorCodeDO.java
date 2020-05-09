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
}
