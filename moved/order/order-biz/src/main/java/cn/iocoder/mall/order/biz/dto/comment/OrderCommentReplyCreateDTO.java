package cn.iocoder.mall.order.biz.dto.comment;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *
 * 订单回复评论创建
 *
 * @author wtz
 * @time 2019-05-16 19:07
 *
 */
@Data
@Accessors(chain = true)
public class OrderCommentReplyCreateDTO implements Serializable {

    /**
     * 评论 id
     */
    private Integer commentId;

    /**
     * 评论目标对象 id
     */
    private Integer parentId;

    /**
     * 评论目标用户 id
     */
    private Integer parentUserId;

    /**
     * 评论目标用户昵称
     */
    private String parentUserNickName;

    /**
     * 评论目标用户头像
     */
    private String parentUserAvatar;

    /**
     * 回复内容
     */
    private String replyContent;

    /**
     * 回复用户 id
     */
    private Integer replyUserId;

    /**
     * 回复用户昵称
     */
    private String replyUserNickName;

    /**
     * 回复用户头像
     */
    private String replyUserAvatar;

    /**
     * 回复用户类型
     */
    private Integer userType;

}
