package cn.iocoder.yudao.module.system.api.logger.dto;

import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import com.fhs.core.trans.anno.Trans;
import com.fhs.core.trans.constant.TransType;
import com.fhs.core.trans.vo.VO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(name = "RPC 服务 - 系统操作日志 Response DTO")
@Data
public class OperateLogRespDTO implements VO {

    @Schema(description = "日志编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long id;

    @Schema(description = "链路追踪编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "89aca178-a370-411c-ae02-3f0d672be4ab")
    private String traceId;
    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "666")
    @Trans(type = TransType.AUTO_TRANS, key = AdminUserApi.PREFIX, fields = "nickname", ref = "userName")
    private Long userId;
    @Schema(description = "用户名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋道")
    private String userName;
    @Schema(description = "用户类型，参见 UserTypeEnum 枚举", requiredMode = Schema.RequiredMode.REQUIRED, example = "2" )
    private Integer userType;
    @Schema(description = "操作模块类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "订单")
    private String type;
    @Schema(description = "操作名", requiredMode = Schema.RequiredMode.REQUIRED, example = "创建订单")
    private String subType;
    @Schema(description = "操作模块业务编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "188")
    private Long bizId;
    @Schema(description = "操作内容", requiredMode = Schema.RequiredMode.REQUIRED,
            example = "修改编号为 1 的用户信息，将性别从男改成女，将姓名从芋道改成源码")
    private String action;
    @Schema(description = "拓展字段", example = "{\"orderId\": \"1\"}")
    private String extra;

    @Schema(description = "请求方法名", requiredMode = Schema.RequiredMode.REQUIRED, example = "GET")
    private String requestMethod;
    @Schema(description = "请求地址", requiredMode = Schema.RequiredMode.REQUIRED, example = "/order/get")
    private String requestUrl;
    @Schema(description = "用户 IP", requiredMode = Schema.RequiredMode.REQUIRED, example = "127.0.0.1")
    private String userIp;
    @Schema(description = "浏览器 UserAgent", requiredMode = Schema.RequiredMode.REQUIRED, example = "Mozilla/5.0")
    private String userAgent;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
