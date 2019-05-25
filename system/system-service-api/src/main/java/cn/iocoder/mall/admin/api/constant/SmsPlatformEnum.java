package cn.iocoder.mall.admin.api.constant;

/**
 * 短信审核状态
 *
 * @author Sin
 * @time 2019/5/16 12:48 PM
 */
public enum SmsPlatformEnum {

    YunPian(1, "云片"),
    AliYun(2, "阿里云"),
    ;

    private final Integer value;
    private final String name;

    SmsPlatformEnum(Integer code, String message) {
        this.value = code;
        this.name = message;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
