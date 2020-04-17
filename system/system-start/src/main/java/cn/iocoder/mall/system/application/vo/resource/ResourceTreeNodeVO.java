package cn.iocoder.mall.system.application.vo.resource;

import cn.iocoder.mall.system.api.bo.resource.ResourceBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@ApiModel("资源树结构 VO")
@Data
@Accessors(chain = true)
public class ResourceTreeNodeVO extends ResourceBO {

    @ApiModelProperty(value = "子节点数组")
    private List<ResourceTreeNodeVO> children;

}
