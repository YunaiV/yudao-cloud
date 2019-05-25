package cn.iocoder.mall.admin.api.constant;

/**
 * 短信审核状态
 *
 * @author Sin
 * @time 2019/5/16 12:48 PM
 */
public enum SmsTypeEnum {

    VERIFICATION_CODE(1, "验证码"),
    NOTICE(1, "通知"),
    MARKETING(2, "营销"),
    ;

    private final Integer value;
    private final String name;

    SmsTypeEnum(Integer code, String message) {
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
