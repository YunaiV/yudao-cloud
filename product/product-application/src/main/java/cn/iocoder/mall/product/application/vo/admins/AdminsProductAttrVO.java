package cn.iocoder.mall.product.application.vo.admins;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "商品规格 VO", description = "不带有规格值数组")
public class AdminsProductAttrVO {

    @ApiModelProperty(value = "规格编号", required = true, example = "1")
    private Integer id;
    @ApiModelProperty(value = "规格名", required = true, example = "颜色")
    private String name;
    @ApiModelProperty(value = "状态", required = true, example = "1")
    private Integer status;
    @ApiModelProperty(value = "创建时间", required = true, example = "时间戳")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public AdminsProductAttrVO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AdminsProductAttrVO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public AdminsProductAttrVO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public AdminsProductAttrVO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
}