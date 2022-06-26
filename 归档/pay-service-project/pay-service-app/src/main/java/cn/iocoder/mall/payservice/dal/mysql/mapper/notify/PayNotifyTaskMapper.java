package cn.iocoder.mall.payservice.dal.mysql.mapper.notify;

import cn.iocoder.mall.payservice.dal.mysql.dataobject.notify.PayNotifyTaskDO;
import cn.iocoder.mall.payservice.enums.notify.PayNotifyStatusEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayNotifyTaskMapper extends BaseMapper<PayNotifyTaskDO> {

    /**
     * 获得需要通知的 PayTransactionNotifyTaskDO 记录。需要满足如下条件：
     *
     * 1. status 非成功
     * 2. nextNotifyTime 小于当前时间
     * 3. active 为 false 并未正在执行中
     *
     * @return PayTransactionNotifyTaskDO 数组
     */
    default List<PayNotifyTaskDO> selectListByNotify() {
        return selectList(new QueryWrapper<PayNotifyTaskDO>()
                .in("status", PayNotifyStatusEnum.WAITING.getStatus(), PayNotifyStatusEnum.REQUEST_SUCCESS.getStatus(),
                        PayNotifyStatusEnum.REQUEST_FAILURE.getStatus())
                .le("next_notify_time", "NOW()")
                .eq("active", Boolean.FALSE));
    }

    default int update(PayNotifyTaskDO update, Integer whereNotifyTimes) {
        return update(update, new QueryWrapper<PayNotifyTaskDO>()
                .eq("id", update.getId()).eq("notify_times", whereNotifyTimes));
    }

//
//        <select id="selectByNotify" resultMap="PayNotifyTaskResultMap">
//    SELECT
//            <include refid="FIELDS"/>
//            FROM notify_task
//    WHERE status IN (1, 4, 5)
//    AND next_notify_time <![CDATA[ <= ]]> NOW()
//    AND last_execute_time > next_notify_time
//            </select>

}
