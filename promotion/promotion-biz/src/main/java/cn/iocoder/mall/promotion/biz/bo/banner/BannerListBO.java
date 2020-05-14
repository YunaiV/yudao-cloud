package cn.iocoder.mall.promotion.biz.bo.banner;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * banner：list
 *
 * author: sin
 * time: 2020/5/14 16:00
 */
@Data
@Accessors(chain = true)
public class BannerListBO implements Serializable {


    @ApiModelProperty("编号")
    private Integer id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("跳转链接")
    private String url;

    @ApiModelProperty("图片链接")
    private String picUrl;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("备注")
    private String memo;

    //
    // 其他

    @ApiModelProperty("更新时间")
    private Date updatedTime;

    @ApiModelProperty("创建时间")
    private Date createdTime;
}
