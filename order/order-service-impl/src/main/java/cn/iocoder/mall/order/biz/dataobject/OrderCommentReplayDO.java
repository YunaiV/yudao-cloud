package cn.iocoder.mall.order.biz.dataobject;

import cn.iocoder.common.framework.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 商品评价回复表
 *
 * // TODO FROM 芋艿 TO wtz 商品评价回复表 =》订单评论回复表
 *
 * @author wtz
 * @time 2019-05-14 21:00
 *
 */
@Data
@Accessors(chain = true)
@TableName(value = "order_comment_replay")
public class OrderCommentReplayDO extends BaseDO {

    /**
     * 回复id
     */
    private Integer id;

    /**
     * 评论id
     */
    private Integer commentId;

    /**
     * 回复的类型 // TODO FROM 芋艿 TO wtz 记得加下枚举类
     */
    private Integer replyType;

    /**
     * 父id
     */
    private Integer parentId;

    /**
     * 回复目标用户id
     */
    private Integer parentUserId;

    /**
     * 回复目标用户昵称
     */
    private String parentUserNickName;

    /**
     * 回复目标用户头像
     */
    private String parentUserAvatar;

    /**
     * 回复的数量
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
     * 回复用户身份 // TODO FROM 芋艿 TO wtz 【提示】userType 和 UserTypeEnum 记录保持一致。
     */
    private Integer replyUserType;

    /**
     * 回复点赞数
     */
    private Integer replyCollectCount;

}
