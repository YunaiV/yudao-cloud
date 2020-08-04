package cn.iocoder.mall.system.biz.dto.smsSign;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 添加 sign
 *
 * author: sin
 * time: 2020/4/20 11:10 上午
 */
@Data
@Accessors(chain = true)
public class AddSignDTO implements Serializable {

    private String sign;

    private Integer platform;
}
