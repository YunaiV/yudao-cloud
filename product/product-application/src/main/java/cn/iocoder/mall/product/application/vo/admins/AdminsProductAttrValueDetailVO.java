package cn.iocoder.mall.product.application.vo.admins;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "商品规格值 VO")
public class AdminsProductAttrValueDetailVO {

    @ApiModelProperty(value = "规格值编号", required = true, example = "1")
    private Integer id;
    @ApiModelProperty(value = "规格值名", required = true, example = "颜色")
    private String name;
    @ApiModelProperty(value = "状态", required = true, example = "1")
    private Integer status;
    @ApiModelProperty(value = "创建时间", required = true, example = "时间戳")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public AdminsProductAttrValueDetailVO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AdminsProductAttrValueDetailVO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public AdminsProductAttrValueDetailVO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public AdminsProductAttrValueDetailVO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

}