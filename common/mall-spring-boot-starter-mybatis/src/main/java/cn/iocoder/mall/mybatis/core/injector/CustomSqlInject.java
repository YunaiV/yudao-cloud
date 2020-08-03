package cn.iocoder.mall.mybatis.core.injector;

import cn.iocoder.mall.mybatis.core.injector.method.InsertByBatch;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;

import java.util.List;

/**
 * 自定义 Sql 注入器，继承 MybatisPlus 提供的默认注入器
 * @author Hccake 2020/8/3
 * @version 1.0
 */
public class CustomSqlInject extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        methodList.add(new InsertByBatch());
        return methodList;
    }

}
