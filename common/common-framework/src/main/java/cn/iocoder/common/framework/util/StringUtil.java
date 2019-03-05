package cn.iocoder.common.framework.util;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class StringUtil {

    public static String join(Collection<?> coll, String delim) {
        return StringUtils.collectionToDelimitedString(coll, delim);
    }

    public static List<String> split(String toSplit, String delim) {
        String[] stringArray = StringUtils.tokenizeToStringArray(toSplit, delim);
        return Arrays.asList(stringArray);
    }

}