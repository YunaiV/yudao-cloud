package cn.iocoder.mall.system.api.constant;

/**
 * 短信审核状态
 *
 * @author Sin
 * @time 2019/5/16 12:48 PM
 */
public enum SmsApplyStatusEnum {

    CHECKING(1, "审核中"),
    SUCCESS(2, "审核成功"),
    FAIL(10, "审核失败"),
    ;

    private final Integer value;
    private final String name;

    SmsApplyStatusEnum(int code, String message) {
        this.value = code;
        this.name = message;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
