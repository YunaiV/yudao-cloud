package cn.iocoder.mall.system.biz.dto.oatuh2;

import lombok.Data;
import lombok.experimental.Accessors;

// TODO 注释
@Data
@Accessors(chain = true)
public class OAuth2MobileCodeSendDTO {

    private String mobile;
    private String ip;

}
