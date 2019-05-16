package cn.iocoder.mall.order.api.dto;

import java.io.Serializable;

/**
 *
 * 订单回复评论创建
 *
 * @author wtz
 * @time 2019-05-16 19:07
 *
 */
public class OrderCommentReplyCreateDTO implements Serializable {

    /**
     * 评论id
     */
    private Integer commentId;

    /**
     * 评论目标对象id
     */
    private Integer parentId;

    /**
     * 评论目标用户id
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
     * 回复用户id
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
    private Integer replyUserType;

}
