package cn.iocoder.mall.system.biz.dao.system;

import cn.iocoder.mall.mybatis.query.QueryWrapperX;
import cn.iocoder.mall.system.biz.dataobject.systemlog.AccessLogDO;
import cn.iocoder.mall.system.biz.dto.system.AccessLogPageDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Repository;

/**
 * 访问日志
 * @author:mac
 * @descriptio
 * @create: 2020-5-12 20：43：00
 */
@Repository
public interface AccessLogMapper extends BaseMapper<AccessLogDO> {

    default IPage<AccessLogDO> selectPage(AccessLogPageDTO accessLogPageDTO) {
        return selectPage(new Page<>(accessLogPageDTO.getPageNo(), accessLogPageDTO.getPageSize()),
                new QueryWrapperX<AccessLogDO>().eqIfPresent("account_id", accessLogPageDTO.getAccountId()));
    }

}
