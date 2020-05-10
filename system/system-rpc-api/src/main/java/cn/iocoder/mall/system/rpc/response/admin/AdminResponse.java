package cn.iocoder.mall.system.rpc.response.admin;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Admin 模块 - Admin 信息 Response
 */
@Data
@Accessors(chain = true)
public class AdminResponse  implements Serializable {

    /**
     * 管理员编号
     */
    private Integer id;

}
