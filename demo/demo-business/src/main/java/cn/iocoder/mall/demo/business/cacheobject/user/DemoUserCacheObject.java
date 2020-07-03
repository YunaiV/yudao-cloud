package cn.iocoder.mall.demo.business.cacheobject.user;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户缓存对象
 */
@Data
@Accessors(chain = true)
public class DemoUserCacheObject {

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
