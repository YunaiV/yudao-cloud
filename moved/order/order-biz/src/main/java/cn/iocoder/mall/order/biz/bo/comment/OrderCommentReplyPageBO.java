package cn.iocoder.mall.order.biz.bo.comment;


import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * 评论回复分页展示
 *
 * @author wtz
 * @time 2019-05-19 14:19
 *
 */
@Data
@Accessors(chain = true)
public class OrderCommentReplyPageBO implements Serializable {

    /**
     * 评论回复总数
     */
    private Integer total;

    /**
     * 用户回复
     */
    List<OrderCommentReplayItem> orderCommentReplayItems;


    @Data
    @Accessors(chain = true)
    public static class OrderCommentReplayItem{
        /**
         * 回复 id
         */
        private Integer id;

        /**
         * 回复的类型
         */
        private Integer replyType;

        /**
         * 回复的内容
         */
        private String replyContent;

        /**
         * 回复的用户 id
         */
        private int replyUserId;

        /**
         * 回复用户的真实姓名
         */
        private String replyUserNickName;

        /**
         * 回复用户的头像
         */
        private String replyUserAvatar;

        /**
         * 回复的点赞数
         */
        private int replyCollectCount;

        /**
         * 回复目标用户昵称
         */
        private String parentUserNickName;

        /**
         * 创建时间
         */
        private Date createTime;

    }
}
