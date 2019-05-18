package cn.iocoder.mall.user.biz.dataobject;

import cn.iocoder.common.framework.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

// TODO 优化，IP
@TableName("mobile_code")
@Data
@Accessors(chain = true)
public class MobileCodeDO extends BaseDO {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 验证码
     */
    private String code;
    /**
     * 今日发送的第几条
     */
    private Integer todayIndex;
    /**
     * 是否使用
     */
    private Boolean used;
    /**
     * 注册的用户编号
     */
    private Integer usedUserId;
    /**
     * 使用时间
     */
    private Date usedTime;

}
