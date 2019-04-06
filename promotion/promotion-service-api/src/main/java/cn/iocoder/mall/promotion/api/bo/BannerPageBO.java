package cn.iocoder.mall.promotion.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Banner 分页 BO
 */
@Data
@Accessors(chain = true)
public class BannerPageBO {

    /**
     * Banner 数组
     */
    private List<BannerBO> list;
    /**
     * 总量
     */
    private Integer total;

}
