package cn.iocoder.mall.order.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;


/**
 * 订单评论超时
 *
 * @author wtz
 * @time 2019-06-15 13:52
 */
@Data
@Accessors(chain = true)
public class OrderCommentTimeOutBO implements Serializable {

    /**
     * 评论 id
     */
    private Integer id;

}
