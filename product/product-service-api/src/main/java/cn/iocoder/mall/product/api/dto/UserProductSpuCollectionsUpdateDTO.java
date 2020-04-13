package cn.iocoder.mall.product.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 更新商品收藏参数
 * @author xiaofeng
 * @date 2019/07/01 20:38
 * @version 1.0
 */
@Data
@Accessors(chain = true)
public class UserProductSpuCollectionsUpdateDTO implements Serializable {

    /**
     * id自增长
     */
    private Integer id;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除状态
     */
    private Integer deleted;


}
