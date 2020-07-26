package cn.iocoder.mall.promotion.api.rpc.banner.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * Banner 分页 BO
 */
@Data
@Accessors(chain = true)
public class BannerPageRespDTO implements Serializable {

    /**
     * Banner 数组
     */
    private List<BannerRespDTO> list;
    /**
     * 总量
     */
    private Integer total;

}
