package cn.iocoder.mall.promotion.application.vo.admins;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@ApiModel("Banner 分页 VO")
@Data
@Accessors(chain = true)
public class AdminsBannerPageVO {

    @ApiModelProperty(value = "Banner 数组")
    private List<AdminsBannerVO> list;
    @ApiModelProperty(value = "Banner 总数")
    private Integer total;

}
