package cn.iocoder.mall.system.biz.dao.system;

import cn.iocoder.mall.system.biz.dataobject.systemlog.AccessLogDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessLogMapper extends BaseMapper<AccessLogDO> {

//    default IPage<AccessLogDO> selectPage(AccessLogPageDTO accessLogPageDTO) {
//        return selectPage(new Page<>(accessLogPageDTO.getPageNo(), accessLogPageDTO.getPageSize()),
//                new QueryWrapperX<AccessLogDO>().eqIfPresent("user_id", accessLogPageDTO.getUserId()));
//    }

}
