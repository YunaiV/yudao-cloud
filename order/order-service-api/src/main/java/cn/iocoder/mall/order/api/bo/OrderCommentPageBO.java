package cn.iocoder.mall.order.api.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 *
 * 订单评论分页展示
 *
 * @author wtz
 * @time 2019-05-14 20:00:00
 */
@Data
@Accessors(chain = true)
public class OrderCommentPageBO implements Serializable {

    /**
     * 总条数
     */
    private Integer total;

//    评论标签化等等在做
//    /**
//     * 好评
//     */
//    private Integer positiveTotal;
//
//    /**
//     * 中评
//     */
//    private Integer moderateTotal;
//
//    /**
//     * 差评
//     */
//    private Integer negativeTotal;

    /**
     * 评论列表
     */
    private List<OrderCommentItem> orderCommentItems;


    @Data
    @Accessors(chain = true)
    @AllArgsConstructor
    public static class OrderCommentItem{
        /**
         * 评论 id
         */
        private Integer id;

        /**
         * 用户头像
         */
        private String userAvatar;

        /**
         * 用户的真实姓名
         */
        private String userNickName;

        /**
         * 评价星
         */
        private Integer star;

        /**
         * 评论的内容
         */
        private String commentContent;

        /**
         * 评论的图片地址
         */
        private String commentPics;

        /**
         * 回复条数
         */
        private Integer replayCount;

        /**
         * 点赞数
         */
        private Integer likeCount;

        /**
         * 创建时间
         */
        private Date createTime;

        /**
         * 商家回复列表
         * 只展示最近的一条
         */
        private String replyContent;

    }

}
