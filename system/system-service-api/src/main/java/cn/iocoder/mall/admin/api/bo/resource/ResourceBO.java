package cn.iocoder.mall.admin.api.bo.resource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@ApiModel("资源 BO")
@Data
@Accessors(chain = true)
public class ResourceBO implements Serializable {

    @ApiModelProperty(value = "资源编号", required = true, example = "1")
    private Integer id;

    @ApiModelProperty(value = "资源类型", required = true, example = "1")
    private Integer type;

    @ApiModelProperty(value = "排序", required = true, example = "1")
    private Integer sort;

    @ApiModelProperty(value = "菜单展示名", required = true, example = "商品管理")
    private String displayName;

    @ApiModelProperty(value = "父级资源编号", required = true, example = "1", notes = "如果无父资源，则值为 0")
    private Integer pid;

    @ApiModelProperty(value = "操作", required = true, example = "/order/list")
    private String handler;

    @ApiModelProperty(value = "图标", example = "add")
    private String icon;

    @ApiModelProperty(value = "创建时间", required = true, example = "时间戳格式")
    private Date createTime;

}
