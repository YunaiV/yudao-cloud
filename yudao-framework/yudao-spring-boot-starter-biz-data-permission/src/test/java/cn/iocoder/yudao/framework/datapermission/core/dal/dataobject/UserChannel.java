package cn.iocoder.yudao.framework.datapermission.core.dal.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserChannel 共享数据授权实体
 *
 * @author sur1-123
 * @date 2024-12-20 14:17
 **/
@TableName("user_channel")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserChannel {
    private Long id;
    private Long userId;
    private Long deptId;
    private Long channelId;
    private Long tenantId;
}
