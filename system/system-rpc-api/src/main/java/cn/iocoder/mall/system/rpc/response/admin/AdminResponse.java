package cn.iocoder.mall.system.rpc.response.admin;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Admin 信息 Response
 */
@Data
@Accessors(chain = true)
public class AdminResponse {

    /**
     * 管理员编号
     */
    private Integer id;
//    private String

}
