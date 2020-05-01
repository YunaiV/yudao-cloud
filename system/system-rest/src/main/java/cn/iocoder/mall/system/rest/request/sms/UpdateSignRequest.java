package cn.iocoder.mall.system.rest.request.sms;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 更新签名
 * <p>
 * author: sin
 * time: 2020/4/20 11:02 上午
 */
@Data
@Accessors(chain = true)
public class UpdateSignRequest implements Serializable {

    private Integer id;

    private String sign;

    private Integer platform;
}
