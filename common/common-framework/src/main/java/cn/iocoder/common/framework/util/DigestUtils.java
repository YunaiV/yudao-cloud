package cn.iocoder.common.framework.util;

import cn.hutool.crypto.digest.BCrypt;

/**
 * 加解密工具类
 */
public class DigestUtils {

    public static String genBcryptSalt() {
        return BCrypt.gensalt();
    }

    public static String bcrypt(String key, String salt) {
        return BCrypt.hashpw(key, salt);
    }

}
