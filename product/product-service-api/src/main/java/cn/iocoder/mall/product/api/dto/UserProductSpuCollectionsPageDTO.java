package cn.iocoder.mall.product.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 商品收藏分页参数
 * @author xiaofeng
 * @date 2019/07/06 18:40
 * @version 1.0
 */
@Data
@Accessors(chain = true)
public class UserProductSpuCollectionsPageDTO implements Serializable {

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 当前页
     */
    @NotNull(message = "页码不能为空")
    private Integer pageNo;

    /**
     * 每页显示的条数
     */
    @NotNull(message = "每页条数不能为空")
    private Integer pageSize;
}
