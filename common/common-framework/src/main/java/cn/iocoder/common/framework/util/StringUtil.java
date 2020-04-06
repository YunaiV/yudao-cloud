package cn.iocoder.common.framework.util;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class StringUtil {

    public static boolean hasText(String str) {
        return StringUtils.hasText(str);
    }

    //sihai:字符串拼接
    public static String join(Collection<?> coll, String delim) {
        return StringUtils.collectionToDelimitedString(coll, delim);
    }
    //sihai:字符串split
    public static List<String> split(String toSplit, String delim) {
        String[] stringArray = StringUtils.tokenizeToStringArray(toSplit, delim);
        return Arrays.asList(stringArray);
    }
    //sihai:分割后转list<Integer>
    public static List<Integer> splitToInt(String toSplit, String delim) {
        String[] stringArray = StringUtils.tokenizeToStringArray(toSplit, delim);
        List<Integer> array = new ArrayList<>(stringArray.length);
        for (String string : stringArray) {
            array.add(Integer.valueOf(string));
        }
        return array;
    }
    //sihai:从start处截取到尾部
    public static String substring(String str, int start) {
        return org.apache.commons.lang3.StringUtils.substring(str, start);
    }
}
