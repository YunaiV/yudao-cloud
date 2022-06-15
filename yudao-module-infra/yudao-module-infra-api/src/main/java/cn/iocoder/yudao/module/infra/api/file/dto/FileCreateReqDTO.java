package cn.iocoder.yudao.module.infra.api.file.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@ApiModel("RPC 服务 - 文件创建 Request DTO")
@Data
public class FileCreateReqDTO {

    @ApiModelProperty(value = "原文件名称", example = "xxx.png")
    private String name;

    @ApiModelProperty(value = "文件路径", example = "xxx.png")
    private String path;

    @ApiModelProperty(value = "文件内容", required = true)
    @NotEmpty(message = "文件内容不能为空")
    private byte[] content;

}
