package cn.iocoder.yudao.module.system.api.logger.dto;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "RPC 服务 - 操作日志分页 Request DTO")
@Data
public class OperateLogPageReqDTO extends PageParam {

    @Schema(description = "模块类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "订单")
    private String type;

    @Schema(description = "模块数据编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "188")
    private Long bizId;

    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "666")
    private Long userId;

}
