package cn.iocoder.mall.systemservice.dal.mysql.mapper.systemlog;

import cn.iocoder.mall.systemservice.dal.mysql.dataobject.systemlog.AccessLogDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessLogMapper extends BaseMapper<AccessLogDO> {

//    default IPage<AccessLogDO> selectPage(AccessLogPageDTO accessLogPageDTO) {
//        return selectPage(new Page<>(accessLogPageDTO.getPageNo(), accessLogPageDTO.getPageSize()),
//                new QueryWrapperX<AccessLogDO>().eqIfPresent("account_id", accessLogPageDTO.getAccountId()));
//    }

}
