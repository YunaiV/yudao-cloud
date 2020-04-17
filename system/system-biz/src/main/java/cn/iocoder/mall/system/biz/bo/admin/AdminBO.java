package cn.iocoder.mall.system.biz.bo.admin;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 管理员模块 - 账号信息 BO
 */
@Data
@Accessors(chain = true)
public class AdminBO {

    /**
     * 管理员编号
     */
    private Integer id;
    /**
     * 真实名字
     */
    private String name;

}
