package cn.iocoder.yudao.framework.datapermission.core.dal.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Channel 共享数据资源实体（共享实体可能有其他租户使用，存在不想每个租户数据权限为全部的管理员看到全部共享数据）
 *
 * @author sur1-123
 * @date 2024-12-20 14:16
 **/
@TableName("channel")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Channel {
    private Long id;
}
