package cn.iocoder.mall.userservice.enums.sms;

import cn.iocoder.common.framework.core.IntArrayValuable;

import java.util.Arrays;

/**
 * 用户短信验证码发送场景的枚举
 */
public enum UserSmsSceneEnum implements IntArrayValuable {

    LOGIN_BY_SMS(1, "手机号登陆"),
    CHANGE_MOBILE_BY_SMS(2, "更换手机号"),
            ;

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(UserSmsSceneEnum::getValue).toArray();

    private final Integer value;
    private final String name;

    UserSmsSceneEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public int[] array() {
        return ARRAYS;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

}
