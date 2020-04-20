package cn.iocoder.mall.system.biz.dto.smsSign;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 更新签名
 * <p>
 * author: sin
 * time: 2020/4/20 11:05 上午
 */
@Data
@Accessors(chain = true)
public class UpdateSignDTO implements Serializable {

    private Integer id;

    private String sign;

    private Integer platform;

}
