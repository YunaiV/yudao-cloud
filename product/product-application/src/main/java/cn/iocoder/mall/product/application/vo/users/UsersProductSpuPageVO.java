package cn.iocoder.mall.product.application.vo.users;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("商品 SPU 分页 VO")
public class UsersProductSpuPageVO {

    @ApiModelProperty(value = "spu 数组", required = true)
    private List<UsersProductSpuVO> spus;
    @ApiModelProperty(value = "总数", required = true)
    private Integer count;

    public List<UsersProductSpuVO> getSpus() {
        return spus;
    }

    public UsersProductSpuPageVO setSpus(List<UsersProductSpuVO> spus) {
        this.spus = spus;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public UsersProductSpuPageVO setCount(Integer count) {
        this.count = count;
        return this;
    }

}