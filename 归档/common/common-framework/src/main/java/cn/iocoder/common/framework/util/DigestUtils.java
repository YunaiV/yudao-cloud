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

    // TODO 稍后移到单元测试
    public static void main(String[] args) {
        String salt = genBcryptSalt();
        String password = "buzhidao";
        System.out.println(salt);
        System.out.println(bcrypt(password, salt));
    }

}
