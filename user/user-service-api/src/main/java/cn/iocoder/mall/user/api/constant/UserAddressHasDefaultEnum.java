package cn.iocoder.mall.user.api.constant;

/**
 * 用户地址 - 用户默认地址
 *
 * @author Sin
 * @time 2019-04-10 22:02
 */
public enum UserAddressHasDefaultEnum {

    DEFAULT_ADDRESS_NO (1, "不是默认地址"),
    DEFAULT_ADDRESS_YES (2, "默认地址")
    ;

    private final int value;
    private final String name;

    UserAddressHasDefaultEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
