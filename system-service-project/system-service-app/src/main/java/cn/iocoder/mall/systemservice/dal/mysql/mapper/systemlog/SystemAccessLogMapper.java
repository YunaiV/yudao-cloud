package cn.iocoder.mall.systemservice.dal.mysql.mapper.systemlog;

import cn.iocoder.mall.mybatis.core.query.QueryWrapperX;
import cn.iocoder.mall.systemservice.dal.mysql.dataobject.systemlog.SystemAccessLogDO;
import cn.iocoder.mall.systemservice.service.systemlog.bo.SystemAccessLogPageBO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Repository;

/**
 * 系统访问日志 Mapper
 */
@Repository
public interface SystemAccessLogMapper extends BaseMapper<SystemAccessLogDO> {

    default IPage<SystemAccessLogDO> selectPage(SystemAccessLogPageBO pageBO) {
        return selectPage(new Page<>(pageBO.getPageNo(), pageBO.getPageSize()),
                new QueryWrapperX<SystemAccessLogDO>()
                        .eqIfPresent("user_id", pageBO.getUserId())
                        .eqIfPresent("user_type", pageBO.getUserType())
                        .eqIfPresent("application_name", pageBO.getApplicationName())
                .orderByDesc("start_time"));
    }

}
