package cn.iocoder.mall.system.biz.dto.smsTemplate;

import cn.iocoder.common.framework.vo.PageParam;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * page 短信模板 query
 *
 * @author Sin
 * @time 2019/5/19 4:32 PM
 */
@Data
@Accessors(chain = true)
public class ListSmsTemplateDTO extends PageParam {

    @NotNull
    private String id;

    @NotNull
    private Integer smsSignId;

    @NotNull
    private String template;

    @NotNull
    private String applyStatus;
}
