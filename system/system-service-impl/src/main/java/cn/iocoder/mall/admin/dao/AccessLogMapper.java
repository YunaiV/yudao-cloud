package cn.iocoder.mall.admin.dao;

import cn.iocoder.common.framework.mybatis.QueryWrapperX;
import cn.iocoder.mall.admin.api.dto.admin.AdminPageDTO;
import cn.iocoder.mall.admin.api.dto.systemlog.AccessLogPageDTO;
import cn.iocoder.mall.admin.dataobject.AccessLogDO;
import cn.iocoder.mall.admin.dataobject.AdminDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessLogMapper extends BaseMapper<AccessLogDO> {

    default IPage<AccessLogDO> selectPage(AccessLogPageDTO accessLogPageDTO) {
        return selectPage(new Page<>(accessLogPageDTO.getPageNo(), accessLogPageDTO.getPageSize()),
                new QueryWrapperX<AccessLogDO>().eqIfPresent("user_id", accessLogPageDTO.getUserId()));
    }

}
