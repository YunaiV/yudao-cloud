package cn.iocoder.common.framework.util;

import cn.hutool.system.SystemUtil;

/**
 * 操作系统工具类
 */
public class OSUtils {

    public static String getHostName() {
        return SystemUtil.getHostInfo().getName();
    }

}
