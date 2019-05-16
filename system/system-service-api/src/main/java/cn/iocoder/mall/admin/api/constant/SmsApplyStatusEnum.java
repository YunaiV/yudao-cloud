package cn.iocoder.mall.admin.api.constant;

/**
 * 短信审核状态
 *
 * @author Sin
 * @time 2019/5/16 12:48 PM
 */
public enum SmsApplyStatusEnum {

    CHECKING(1, "审核中"),
    SUCCESS(2, "审核成功"),
    FAIL(3, "审核失败"),
    ;

    private final int code;
    private final String message;

    SmsApplyStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
