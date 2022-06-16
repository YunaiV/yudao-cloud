package cn.iocoder.mall.system.biz.dao.sms;

import cn.iocoder.mall.system.biz.dataobject.sms.SmsSendLogDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 短信
 *
 * @author Sin
 * @time 2019/5/16 6:18 PM
 */
@Repository
public interface SmsSendMapper extends BaseMapper<SmsSendLogDO> {
}
