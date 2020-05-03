package cn.iocoder.mall.system.biz.dao.sms;

import cn.iocoder.mall.system.biz.dataobject.sms.SmsSignDO;
import cn.iocoder.mall.system.biz.dto.smsSign.ListSmsSignDTO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

/**
 * 短信
 *
 * @author Sin
 * @time 2019/5/16 6:18 PM
 */
@Repository
public interface SmsSignMapper extends BaseMapper<SmsSignDO> {

    default IPage<SmsSignDO> listSmsSign(ListSmsSignDTO queryDTO) {
        LambdaQueryWrapper<SmsSignDO> queryWrapper = new LambdaQueryWrapper<>();
        if (queryDTO.getApplyStatus() != null) {
            queryWrapper.eq(SmsSignDO::getApplyStatus, queryDTO.getApplyStatus());
        }
        if (!StringUtils.isEmpty(queryDTO.getSign())) {
            queryWrapper.like(SmsSignDO::getSign, queryDTO.getSign());
        }
        if (!StringUtils.isEmpty(queryDTO.getId())) {
            queryWrapper.eq(SmsSignDO::getId, queryDTO.getId());
        }

        Page<SmsSignDO> page = new Page<SmsSignDO>()
                .setSize(queryDTO.getPageSize())
                .setCurrent(queryDTO.getPageNo())
                .setDesc("create_time");
        return selectPage(page, queryWrapper);
    }
}
