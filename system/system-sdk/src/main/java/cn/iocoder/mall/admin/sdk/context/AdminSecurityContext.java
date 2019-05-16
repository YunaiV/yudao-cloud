package cn.iocoder.mall.admin.sdk.context;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 * Security 上下文
 */
@Data
@Accessors(chain = true)
public class AdminSecurityContext {

    private Integer adminId;
    private Set<Integer> roleIds;

}
