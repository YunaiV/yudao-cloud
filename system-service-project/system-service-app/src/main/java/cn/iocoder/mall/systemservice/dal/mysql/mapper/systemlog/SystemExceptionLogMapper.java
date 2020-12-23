package cn.iocoder.mall.systemservice.dal.mysql.mapper.systemlog;

import cn.iocoder.mall.mybatis.core.query.QueryWrapperX;
import cn.iocoder.mall.systemservice.dal.mysql.dataobject.systemlog.SystemExceptionLogDO;
import cn.iocoder.mall.systemservice.service.systemlog.bo.SystemExceptionLogPageBO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemExceptionLogMapper extends BaseMapper<SystemExceptionLogDO> {

    default IPage<SystemExceptionLogDO> selectPage(SystemExceptionLogPageBO pageBO) {
        return selectPage(new Page<>(pageBO.getPageNo(), pageBO.getPageSize()),
                new QueryWrapperX<SystemExceptionLogDO>()
                        .eqIfPresent("user_id", pageBO.getUserId())
                        .eqIfPresent("user_type", pageBO.getUserType())
                        .eqIfPresent("application_name", pageBO.getApplicationName())
                        .eqIfPresent("process_status", pageBO.getProcessStatus())
                        .orderByDesc("exception_time"));
    }

}
