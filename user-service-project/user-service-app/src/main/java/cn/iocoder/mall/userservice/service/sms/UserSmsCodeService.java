package cn.iocoder.mall.userservice.service.sms;

import cn.iocoder.common.framework.exception.ServiceException;
import cn.iocoder.common.framework.exception.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.validator.Mobile;
import cn.iocoder.mall.userservice.dal.mysql.dataobject.sms.UserSmsCodeDO;
import cn.iocoder.mall.userservice.dal.mysql.mapper.sms.UserSmsCodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

import static cn.iocoder.mall.userservice.enums.UserErrorCodeConstants.*;

@Service
@Validated
public class UserSmsCodeService {

    /**
     * 每条验证码的过期时间，单位：毫秒
     */
    @Value("${modules.user-sms-code-service.code-expire-time-millis}")
    private int codeExpireTimes;
    /**
     * 每日发送最大数量
     */
    @Value("${modules.user-sms-code-service.send-maximum-quantity-per-day}")
    private int sendMaximumQuantityPerDay;
    /**
     * 短信发送频率，单位：毫秒
     */
    @Value("${modules.user-sms-code-service.send-frequency}")
    private int sendFrequency;

    @Autowired
    private UserSmsCodeMapper userSmsCodeMapper;

    /**
     * 创建短信验证码，并返回它
     *
     * 注意，不包括发送逻辑
     *
     * @param mobile 手机号
     * @param scene 发送场景
     * @param ip IP
     * @return 短信验证码
     */
    public String createSmsCode(@Mobile String mobile, Integer scene, String ip) {
        // 校验是否可以发送验证码，不用筛选场景
        UserSmsCodeDO lastUserSmsCodeDO = userSmsCodeMapper.selectLastByMobile(mobile, null);
        if (lastUserSmsCodeDO != null) {
            if (lastUserSmsCodeDO.getTodayIndex() >= sendMaximumQuantityPerDay) { // 超过当天发送的上限。
                throw ServiceExceptionUtil.exception(USER_SMS_CODE_EXCEED_SEND_MAXIMUM_QUANTITY_PER_DAY);
            }
            if (System.currentTimeMillis() - lastUserSmsCodeDO.getCreateTime().getTime() < sendFrequency) { // 发送过于频繁
                throw ServiceExceptionUtil.exception(USER_SMS_CODE_SEND_TOO_FAST);
            }
            // TODO 提升，每个 IP 每天可发送数量
            // TODO 提升，每个 IP 每小时可发送数量
        }
        // 创建验证码记录
        UserSmsCodeDO newMobileCodePO = new UserSmsCodeDO().setMobile(mobile)
                .setCode("9999") // TODO 芋艿，随机 4 位验证码 or 6 位验证码
                .setScene(scene)
                .setTodayIndex(lastUserSmsCodeDO != null ? lastUserSmsCodeDO.getTodayIndex() : 1)
                .setCreateIp(ip).setUsed(false);
        newMobileCodePO.setCreateTime(new Date());
        userSmsCodeMapper.insert(newMobileCodePO);
        return newMobileCodePO.getCode();
    }

    /**
     * 验证短信验证码是否正确。
     * 如果正确，则将验证码标记成已使用
     * 如果错误，则抛出 {@link ServiceException} 异常
     *
     * @param mobile 手机号
     * @param code 验证码
     * @param scene 发送场景
     * @param ip IP
     */
    public void verifySmsCode(String mobile, String code, Integer scene, String ip) {
        // 校验验证码
        UserSmsCodeDO mobileCodeDO = userSmsCodeMapper.selectLastByMobile(mobile, scene);
        if (mobileCodeDO == null) { // 若验证码不存在，抛出异常
            throw ServiceExceptionUtil.exception(USER_SMS_CODE_NOT_FOUND);
        }
        if (System.currentTimeMillis() - mobileCodeDO.getCreateTime().getTime() >= codeExpireTimes) { // 验证码已过期
            throw ServiceExceptionUtil.exception(USER_SMS_CODE_EXPIRED);
        }
        if (mobileCodeDO.getUsed()) { // 验证码已使用
            throw ServiceExceptionUtil.exception(USER_SMS_CODE_USED);
        }
        if (!mobileCodeDO.getCode().equals(code)) {
            throw ServiceExceptionUtil.exception(USER_SMS_CODE_NOT_CORRECT);
        }
        // 使用验证码
        UserSmsCodeDO updateObj = new UserSmsCodeDO().setId(mobileCodeDO.getId())
                .setUsed(true).setUsedTime(new Date()).setUsedIp(ip);
        userSmsCodeMapper.updateById(updateObj);
    }

}
