package cn.iocoder.mall.promotion.application.vo.admins;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("商品推荐 VO")
public class AdminsProductRecommendVO {

    @ApiModelProperty(value = "编号", required = true, example = "1")
    private Integer id;
    @ApiModelProperty(value = "推荐类型", required = true, example = "1")
    private Integer type;
    @ApiModelProperty(value = "商品编号", required = true, example = "1")
    private Integer productSpuId;
    @ApiModelProperty(value = "排序", required = true, example = "10")
    private Integer sort;
    @ApiModelProperty(value = "状态", required = true, example = "1")
    private Integer status;
    @ApiModelProperty(value = "备注", required = true, example = "这个活动很牛逼")
    private String memo;
    @ApiModelProperty(value = "创建时间", required = true, example = "时间戳格式")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public AdminsProductRecommendVO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public AdminsProductRecommendVO setType(Integer type) {
        this.type = type;
        return this;
    }

    public Integer getProductSpuId() {
        return productSpuId;
    }

    public AdminsProductRecommendVO setProductSpuId(Integer productSpuId) {
        this.productSpuId = productSpuId;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public AdminsProductRecommendVO setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public AdminsProductRecommendVO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMemo() {
        return memo;
    }

    public AdminsProductRecommendVO setMemo(String memo) {
        this.memo = memo;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public AdminsProductRecommendVO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
}