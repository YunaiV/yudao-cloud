package cn.iocoder.mall.product.application.vo.admins;

import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel("商品 SPU 分页 VO")
public class AdminsProductSpuPageVO {

    /**
     * spu 数组
     */
    private List<AdminsProductSpuVO> spus;
    /**
     * 总数
     */
    private Integer count;

    public List<AdminsProductSpuVO> getSpus() {
        return spus;
    }

    public AdminsProductSpuPageVO setSpus(List<AdminsProductSpuVO> spus) {
        this.spus = spus;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public AdminsProductSpuPageVO setCount(Integer count) {
        this.count = count;
        return this;
    }

}