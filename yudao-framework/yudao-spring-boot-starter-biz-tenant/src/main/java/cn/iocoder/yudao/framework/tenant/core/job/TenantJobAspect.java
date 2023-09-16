package cn.iocoder.yudao.framework.tenant.core.job;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import cn.iocoder.yudao.framework.tenant.core.service.TenantFrameworkService;
import cn.iocoder.yudao.framework.tenant.core.util.TenantUtils;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 多租户 JobHandler AOP
 * 任务执行时，会按照租户逐个执行 Job 的逻辑
 *
 * 注意，需要保证 JobHandler 的幂等性。因为 Job 因为某个租户执行失败重试时，之前执行成功的租户也会再次执行。
 *
 * @author 芋道源码
 */
@Aspect
@RequiredArgsConstructor
@Slf4j
public class TenantJobAspect {

    private final TenantFrameworkService tenantFrameworkService;

    @Around("@annotation(xxlJob)")
    public Object around(ProceedingJoinPoint joinPoint, XxlJob xxlJob) throws Throwable {
        // 如果非多租户 Job，则跳过
        TenantJob tenantJob = getClassAnnotation(joinPoint, TenantJob.class);
        if (tenantJob == null) {
            return joinPoint.proceed();
        }

        // 如果是多租户 Job，则会按照租户逐个执行 Job 的逻辑
        execute(joinPoint, xxlJob);
        return null; // JobHandler 无返回
    }

    private void execute(ProceedingJoinPoint joinPoint, XxlJob xxlJob) {
        // 获得租户列表
        List<Long> tenantIds = tenantFrameworkService.getTenantIds();
        if (CollUtil.isEmpty(tenantIds)) {
            return;
        }

        // 逐个租户，执行 Job
        Map<Long, String> results = new ConcurrentHashMap<>();
        tenantIds.parallelStream().forEach(tenantId -> {
            // TODO 芋艿：先通过 parallel 实现并行；1）多个租户，是一条执行日志；2）异常的情况
            TenantUtils.execute(tenantId, () -> {
                try {
                    joinPoint.proceed();
                } catch (Throwable e) {
                    results.put(tenantId, ExceptionUtil.getRootCauseMessage(e));
                    // 打印异常
                    XxlJobHelper.log(StrUtil.format("[多租户({}) 执行任务({})，发生异常：{}]",
                            tenantId, xxlJob.value(), ExceptionUtils.getStackTrace(e)));
                }
            });
        });
        // 如果 results 非空，说明发生了异常，标记 XXL-Job 执行失败
        if (CollUtil.isNotEmpty(results)) {
            XxlJobHelper.handleFail(JsonUtils.toJsonString(results));
        }
    }

    @SuppressWarnings("SameParameterValue")
    private static <T extends Annotation> T getClassAnnotation(ProceedingJoinPoint joinPoint, Class<T> annotationClass) {
        return ((MethodSignature) joinPoint.getSignature()).getMethod().getDeclaringClass().getAnnotation(annotationClass);
    }

}
