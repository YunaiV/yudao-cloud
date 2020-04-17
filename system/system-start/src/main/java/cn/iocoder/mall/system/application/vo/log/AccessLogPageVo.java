package cn.iocoder.mall.system.application.vo.log;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author:ycjx
 * @descriptio
 * @create:2019-06-23 17:03
 */
@ApiModel("访问日志分页 VO")
@Data
@Accessors(chain = true)
public class AccessLogPageVo {


    @ApiModelProperty(value = "访问数据")
    private List<AccessLogVo> list;
    @ApiModelProperty(value = "访问总数")
    private Integer total;
}
