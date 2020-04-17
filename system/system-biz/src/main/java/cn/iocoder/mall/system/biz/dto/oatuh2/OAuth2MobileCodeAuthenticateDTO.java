package cn.iocoder.mall.system.biz.dto.oatuh2;

import lombok.Data;
import lombok.experimental.Accessors;

// TODO 注释
@Data
@Accessors(chain = true)
public class OAuth2MobileCodeAuthenticateDTO {

    private String mobile;
    private String code;

}
