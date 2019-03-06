package cn.iocoder.mall.product.application.vo.admins;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

@ApiModel(value = "商品规格明细 VO", description = "带有规格值数组")
public class AdminsProductAttrDetailVO {

    @ApiModelProperty(value = "规格编号", required = true, example = "1")
    private Integer id;
    @ApiModelProperty(value = "规格名", required = true, example = "颜色")
    private String name;
    @ApiModelProperty(value = "状态", required = true, example = "1")
    private Integer status;
    @ApiModelProperty(value = "创建时间", required = true, example = "时间戳")
    private Date createTime;
    @ApiModelProperty(value = "规格值数组", required = true)
    private List<AdminsProductAttrValueDetailVO> values;

    public Integer getId() {
        return id;
    }

    public AdminsProductAttrDetailVO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AdminsProductAttrDetailVO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public AdminsProductAttrDetailVO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public AdminsProductAttrDetailVO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public List<AdminsProductAttrValueDetailVO> getValues() {
        return values;
    }

    public AdminsProductAttrDetailVO setValues(List<AdminsProductAttrValueDetailVO> values) {
        this.values = values;
        return this;
    }

}