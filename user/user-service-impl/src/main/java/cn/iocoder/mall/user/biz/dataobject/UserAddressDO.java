package cn.iocoder.mall.user.biz.dataobject;

import cn.iocoder.common.framework.dataobject.DeletableDO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户地址信息
 *
 * @author Sin
 * @time 2019-04-06 13:22
 */
@Data
@Accessors(chain = true)
public class UserAddressDO extends DeletableDO {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 用户编号
     */
    private Integer userId;
    // TODO FROM 芋艿 to 小范：需要增加下省市区；
    // TODO FROM 芋艿 to 小范：想了一个增强，可以靠 API ，实现自动识别，哈哈哈；https://open.kuaidihelp.com/apitool/1019
    /**
     * 收件区域编号
     */
    private String areaNo;
    /**
     * 收件人名称
     */
    private String name;
    /**
     * 收件手机号
     */
    private String mobile;
    /**
     * 收件详细地址
     */
    private String address;
    /**
     * 是否为默认
     */
    // TODO FROM 芋艿 to 小范：是不是一起在捉摸个单词，总觉得 hasDefault 怪怪的。。
    private Integer hasDefault;

}
