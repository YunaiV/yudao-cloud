package cn.iocoder.mall.promotionservice.service.banner.bo;

import cn.iocoder.mall.promotion.api.rpc.banner.dto.BannerRespDTO;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Banner 分页 DTO
 */
@Data
@Accessors(chain = true)
public class BannerPageBO implements Serializable {

    /**
     * Banner 数组
     */
    private List<BannerBO> list;
    /**
     * 总量
     */
    private Integer total;
}
