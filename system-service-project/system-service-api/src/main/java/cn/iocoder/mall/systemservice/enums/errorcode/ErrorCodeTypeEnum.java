package cn.iocoder.mall.systemservice.enums.errorcode;

/**
 * 错误码的类型枚举
 *
 * 考虑到便利性，我们会扫描每个项目的错误码枚举类，自动添加到错误码数据库中，并标记为 {@link #AUTO_GENERATION} 类型
 * 经过管理员手动编辑过的错误码，会标记为 {@link #MANUAL_OPERATION} 类型，并禁止自动同步
 *
 * @author ding
 */
public enum ErrorCodeTypeEnum {

    /**
     * 自动生成
     */
    AUTO_GENERATION(1),
    /**
     * 手动处理
     */
    MANUAL_OPERATION(2);

    private final Integer type;

    ErrorCodeTypeEnum(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

}
