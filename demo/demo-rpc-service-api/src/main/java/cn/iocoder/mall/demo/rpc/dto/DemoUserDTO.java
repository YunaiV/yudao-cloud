package cn.iocoder.mall.demo.rpc.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DemoUserDTO {

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
