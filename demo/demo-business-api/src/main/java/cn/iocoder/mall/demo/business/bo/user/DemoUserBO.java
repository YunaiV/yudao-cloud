package cn.iocoder.mall.demo.business.bo.user;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Demo 用户 BO
 */
@Data
@Accessors(chain = true)
public class DemoUserBO {

    /**
     * 用户编号
     */
    private Integer id;
    /**
     * 昵称
     */
    private String name;
    /**
     * 性别
     */
    private Integer gender;

}
