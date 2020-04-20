package cn.iocoder.mall.system.biz.dao.sms;

import cn.iocoder.mall.system.biz.dataobject.sms.SmsTemplateDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 短信 template
 *
 * @author Sin
 * @time 2019/5/16 6:18 PM
 */
@Repository
public interface SmsTemplateMapper extends BaseMapper<SmsTemplateDO> {
}
