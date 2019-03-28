package cn.iocoder.common.framework.util;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

public class CollectionUtil {

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static <T> Set<T> asSet(T... objs) {
        return new HashSet<>(Arrays.asList(objs));
    }


    /**
     * 创建指定属性为KEY, objs的每个元素为值的Multimap的Map集合。
     *
     * @param objs     数组
     * @param keyClazz 值类型，即{@code property}的类型
     * @param valClazz 值类型
     * @param property 属性名
     * @param <K>      泛型
     * @param <V>      泛型
     * @return 指定属性的Map集合
     */
    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, List<V>> buildMultimap(List<V> objs, Class<K> keyClazz, Class<V> valClazz,
                                                       String property) {
        if (objs.isEmpty()) {
            return Collections.EMPTY_MAP;
        }
        Map<K, List<V>> results = new HashMap<>(objs.size());
        try {
            Field field = getField(objs, property);
            for (V obj : objs) {
                K key = (K) field.get(obj);
                List<V> value = results.get(key);
                if (value == null) {
                    results.put(key, value = new ArrayList<>());
                }
                value.add(obj);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    /**
     * 获取 object 属性 field
     *
     * @param objs     对象数组
     * @param property 属性
     * @return 对象元素里的指定属性Field, 并设置该field可以被访问
     */
    public static Field getField(List<?> objs, String property) {
        Field field = ReflectionUtils.findField(objs.get(0).getClass(), property);
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        return field;
    }
}