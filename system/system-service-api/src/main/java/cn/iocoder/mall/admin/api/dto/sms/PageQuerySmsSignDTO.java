package cn.iocoder.mall.admin.api.dto.sms;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * page 短信模板 query
 *
 * @author Sin
 * @time 2019/5/19 4:32 PM
 */
@Data
@Accessors(chain = true)
public class PageQuerySmsSignDTO implements Serializable {

    private Integer pageSize;

    private Integer pageCurrent;

    private String sign;

    private Integer applyStatus;
}
