package cn.iocoder.mall.system.rest.request.smsSign;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;

/**
 * 添加 sign
 *
 * author: sin
 * time: 2020/4/20 11:10 上午
 */
@Data
@Accessors(chain = true)
public class AddSignRequest implements Serializable {

    private String sign;

    private Integer platform;
}
