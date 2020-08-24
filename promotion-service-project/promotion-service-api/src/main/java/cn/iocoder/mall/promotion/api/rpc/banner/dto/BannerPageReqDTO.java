package cn.iocoder.mall.promotion.api.rpc.banner.dto;

import cn.iocoder.common.framework.vo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Banner 分页 Request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class BannerPageReqDTO extends PageParam {

    /**
     * 标题，模糊匹配
     */
    private String title;

}
