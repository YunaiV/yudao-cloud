package cn.iocoder.mall.product.application.vo.admins;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@ApiModel("产品分类树节点 VO")
@Data
@Accessors(chain = true)
public class AdminsProductCategoryTreeNodeVO {

    @ApiModelProperty(value = "分类编号", required = true, example = "1")
    private Integer id;
    @ApiModelProperty(value = "父分类编号", required = true, example = "0")
    private Integer pid;
    @ApiModelProperty(value = "分类名", required = true, example = "手机")
    private String name;
    @ApiModelProperty(value = "描述", required = true, example = "这个商品很吊")
    private String description;
    @ApiModelProperty(value = "分类图片", notes = "一般情况下，只有根分类才有图片", example = "http://www.iocoder.cn/images/common/wechat_mp_2017_07_31_bak.jpg")
    private String picUrl;
    @ApiModelProperty(value = "排序值", required = true, example = "10")
    private Integer sort;
    @ApiModelProperty(value = "状态", required = true, notes = "1-开启；2-关闭", example = "1")
    private Integer status;
    @ApiModelProperty(value = "创建时间", required = true, example = "时间戳")
    private Date createTime;
    @ApiModelProperty(value = "子节点数组")
    private List<AdminsProductCategoryTreeNodeVO> children;

}
