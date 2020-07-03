package cn.iocoder.mall.userweb.controller.passport.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 用户通信证信息
 */
@Data
@Accessors(chain = true)
public class UserPassportVO {

    /**
     * 认证信息
     */
    @Data
    @Accessors(chain = true)
    public static class Authentication {

        /**
         * 访问令牌
         */
        private String accessToken;
        /**
         * 刷新令牌
         */
        private String refreshToken;
        /**
         * 账号编号
         */
        private Integer accountId;
        /**
         * 过期时间
         */
        private Date expiresTime;

    }

    /**
     * 用户信息
     */
    @Data
    @Accessors(chain = true)
    public static class User {

        /**
         * 用户编号
         */
        private Integer id;
        /**
         * 昵称
         */
        private String nickname;
        /**
         * 头像
         */
        private String avatar;

    }

    /**
     * 用户信息
     */
    private User user;
    /**
     * 认证信息
     */
    private Authentication authorization;

}
