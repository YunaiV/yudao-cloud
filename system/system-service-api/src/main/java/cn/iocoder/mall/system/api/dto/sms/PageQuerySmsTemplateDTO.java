package cn.iocoder.mall.system.api.dto.sms;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
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

    @NotNull
    private Long current;

    @NotNull
    private Long size;

    @NotNull
    private String id;

    @NotNull
    private Integer smsSignId;

    @NotNull
    private String template;

    @NotNull
    private String applyStatus;
}
