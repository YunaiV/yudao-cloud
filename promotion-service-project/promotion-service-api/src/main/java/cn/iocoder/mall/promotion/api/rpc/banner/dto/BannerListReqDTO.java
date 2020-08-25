package cn.iocoder.mall.promotion.api.rpc.banner.dto;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.validator.InEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Banner 列表 Request DTO
 */
@Data
@Accessors(chain = true)
public class BannerListReqDTO implements Serializable {

    /**
     * 状态
     */
    @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}")
    private Integer status;

}
