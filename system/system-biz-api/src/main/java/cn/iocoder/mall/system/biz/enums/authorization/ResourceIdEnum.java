package cn.iocoder.mall.system.biz.enums.authorization;

public enum  ResourceIdEnum {

    ROOT(0);

    private final Integer id;

    ResourceIdEnum(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

}
