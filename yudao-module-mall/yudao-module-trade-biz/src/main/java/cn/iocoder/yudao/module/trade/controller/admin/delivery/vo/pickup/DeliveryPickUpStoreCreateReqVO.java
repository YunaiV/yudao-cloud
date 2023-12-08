package cn.iocoder.yudao.module.trade.controller.admin.delivery.vo.pickup;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "管理后台 - 自提门店创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeliveryPickUpStoreCreateReqVO extends DeliveryPickUpStoreBaseVO {

}
