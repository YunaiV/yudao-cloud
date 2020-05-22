package cn.iocoder.mall.system.biz.dto.errorcode;

import cn.iocoder.common.framework.vo.PageParam;
import lombok.Data;

/**
 * @author ding
 */
@Data
public class ErrorCodePageDTO extends PageParam {
    /**
     * 错误码信息
     */
    private String message;
}
