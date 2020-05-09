package cn.iocoder.mall.system.biz.enums.errorcode;

/**
 * 错误码枚举，内置错误码是在  枚举中
 * @author ding
 */
public enum ErrorCodeTypeEnum {
    /**
     * 内置错误码
     */
    SYSTEM(1),
    /**
     * 自定义错误码
     */
    CUSTOM(2);

    private final Integer type;

    ErrorCodeTypeEnum(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
