package cn.iocoder.mall.user.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class OAuth2AuthenticationBO implements Serializable {

    /**
     * 用户编号
     */
    private Integer userId;

}
