package cn.iocoder.common.framework.util;

import java.util.Collection;

public class CollectionUtil {

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

}
