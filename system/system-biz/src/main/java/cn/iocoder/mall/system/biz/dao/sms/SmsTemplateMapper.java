package cn.iocoder.mall.system.biz.dao.sms;

import cn.iocoder.mall.system.biz.dataobject.sms.SmsTemplateDO;
import cn.iocoder.mall.system.biz.dto.smsTemplate.ListSmsTemplateDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

/**
 * 短信 template
 *
 * @author Sin
 * @time 2019/5/16 6:18 PM
 */
@Repository
public interface SmsTemplateMapper extends BaseMapper<SmsTemplateDO> {

    default IPage<SmsTemplateDO> listSmsTemplate(ListSmsTemplateDTO listSmsTemplateDTO) {
        QueryWrapper<SmsTemplateDO> queryWrapper = new QueryWrapper<>();
        if (listSmsTemplateDTO.getApplyStatus() != null) {
            queryWrapper.eq("apply_status", listSmsTemplateDTO.getApplyStatus());
        }
        if (listSmsTemplateDTO.getSmsSignId() != null) {
            queryWrapper.eq("sms_sign_id", listSmsTemplateDTO.getSmsSignId());
        }
        if (!StringUtils.isEmpty(listSmsTemplateDTO.getTemplate())) {
            queryWrapper.like("template", listSmsTemplateDTO.getTemplate());
        }
        if (!StringUtils.isEmpty(listSmsTemplateDTO.getId())) {
            queryWrapper.eq("id", listSmsTemplateDTO.getId());
        }

        Page<SmsTemplateDO> page = new Page<SmsTemplateDO>()
                .setSize(listSmsTemplateDTO.getPageSize())
                .setCurrent(listSmsTemplateDTO.getPageNo())
                .setDesc("create_time");
        return selectPage(page, queryWrapper);
    }
}
