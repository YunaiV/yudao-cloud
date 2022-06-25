package cn.iocoder.yudao.framework.env.core.context;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.ArrayList;
import java.util.List;

/**
 * 开发环境上下文
 *
 * @author 芋道源码
 */
public class EnvContextHolder {

    /**
     * 标签的上下文
     *
     * 使用 {@link List} 的原因，可能存在多层设置或者清理
     */
    private static final ThreadLocal<List<String>> tagContext = TransmittableThreadLocal.withInitial(ArrayList::new);

    public static void setTag(String tag) {
        tagContext.get().add(tag);
    }

    public static String getTag() {
        return CollUtil.getLast(tagContext.get());
    }

    public static void removeTag() {
        List<String> tags = tagContext.get();
        if (CollUtil.isEmpty(tags)) {
            return;
        }
        tags.remove(tags.size() - 1);
    }

}
