package cn.iocoder.mall.order.rest.response.comment;

import io.swagger.annotations.ApiModel;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * UsersOrderCommentPageResponse
 *
 * @author xiaofeng
 * @version 1.0
 * @date 2020/05/20 23:32
 */
@ApiModel("用户 - Order 模块 - 订单评论分页列表")
@Data
@Accessors(chain = true)
public class UsersOrderCommentPageResponse {

    /**
     * 总条数
     */
    private Integer total;

    /**
     * 评论列表
     */
    private List<UsersOrderCommentPageResponse.OrderCommentItem> orderCommentItems;


    @Data
    @Accessors(chain = true)
    @AllArgsConstructor
    public static class OrderCommentItem {

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
         * 商家回复列表 只展示最近的一条
         */
        private String replyContent;

    }

}
