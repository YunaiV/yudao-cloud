package cn.iocoder.yudao.module.system.api.dict.dto;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("RPC 服务 - 字典数据 Response DTO")
@Data
public class DictDataRespDTO {

    @ApiModelProperty(value = "字典标签", required = true, example = "芋道")
    private String label;
    @ApiModelProperty(value = "字典值", required = true, example = "iocoder")
    private String value;
    @ApiModelProperty(value = "字典类型", required = true, example = "sys_common_sex")
    private String dictType;
    @ApiModelProperty(value = "状态", required = true, example = "1", notes = "见 CommonStatusEnum 枚举")
    private Integer status;

}
