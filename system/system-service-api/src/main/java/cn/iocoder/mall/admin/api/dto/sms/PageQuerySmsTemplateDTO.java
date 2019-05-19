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
public class PageQuerySmsTemplateDTO implements Serializable {


    private Long current;

    private Long size;

    private String id;

    private Integer smsSignId;

    private String template;

    private String applyStatus;
}
