package cn.iocoder.yudao.framework.datapermission.core.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * UserChannel
 *
 * @author dxsc_jhj
 * @date 2024-12-20 14:17
 **/
@TableName("user_channel")
@Data
public
class UserChannel {
    private Long id;
    private Long userId;
    private Long deptId;
    private Long channelId;
}
