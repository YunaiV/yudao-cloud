package cn.iocoder.mall.user.biz.service;

import cn.iocoder.common.framework.constant.SysErrorCodeEnum;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.util.ValidationUtil;
import cn.iocoder.mall.user.api.MobileCodeService;
import cn.iocoder.mall.user.api.constant.UserErrorCodeEnum;
import cn.iocoder.mall.user.biz.dao.MobileCodeMapper;
import cn.iocoder.mall.user.biz.dataobject.MobileCodeDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * MobileCodeService ，实现用户登陆时需要的验证码
 */
@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.MobileCodeService.version}")
public class MobileCodeServiceImpl implements MobileCodeService {

    /**
     * 每条验证码的过期时间，单位：毫秒
     */
    @Value("${modules.mobile-code-service.code-expire-time-millis}")
    private int codeExpireTimes;
    /**
     * 每日发送最大数量
     */
    @Value("${modules.mobile-code-service.send-maximum-quantity-per-day}")
    private int sendMaximumQuantityPerDay;
    /**
     * 短信发送频率，单位：毫秒
     */
    @Value("${modules.mobile-code-service.send-frequency}")
    private int sendFrequency;

    @Autowired
    private MobileCodeMapper mobileCodeMapper;
    @Autowired
    private UserServiceImpl userService;

    /**
     * 校验手机号的最后一个手机验证码是否有效
     *
     * @param mobile 手机号
     * @param code 验证码
     * @return 手机验证码信息
     */
    public MobileCodeDO validLastMobileCode(String mobile, String code) {
        // TODO: 2019-04-09 Sin 暂时先忽略掉验证码校验
        return new MobileCodeDO().setCode(code).setCreateTime(new Date()).setId(1);
//        MobileCodeDO mobileCodePO = mobileCodeMapper.selectLast1ByMobile(mobile);
//        if (mobileCodePO == null) { // 若验证码不存在，抛出异常
//            throw ServiceExceptionUtil.exception(UserErrorCodeEnum.MOBILE_CODE_NOT_FOUND.getCode());
//        }
//        if (System.currentTimeMillis() - mobileCodePO.getCreateTime().getTime() >= codeExpireTimes) { // 验证码已过期
//            throw ServiceExceptionUtil.exception(UserErrorCodeEnum.MOBILE_CODE_EXPIRED.getCode());
//        }
//        if (mobileCodePO.getUsed()) { // 验证码已使用
//            throw ServiceExceptionUtil.exception(UserErrorCodeEnum.MOBILE_CODE_USED.getCode());
//        }
//        if (!mobileCodePO.getCode().equals(code)) {
//            throw ServiceExceptionUtil.exception(UserErrorCodeEnum.MOBILE_CODE_NOT_CORRECT.getCode());
//        }
//        return mobileCodePO;
    }

    /**
     * 更新手机验证码已使用
     *
     * @param id 验证码编号
     * @param userId 用户编号
     */
    public void useMobileCode(Integer id, Integer userId) {
        MobileCodeDO update = new MobileCodeDO().setId(id).setUsed(true).setUsedUserId(userId).setUsedTime(new Date());
        mobileCodeMapper.update(update);
    }

    // TODO 芋艿，后面要返回有效时间
    public void send(String mobile) {
        if (!ValidationUtil.isMobile(mobile)) {
            throw ServiceExceptionUtil.exception(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(), "手机格式不正确"); // TODO 有点搓
        }
        // 校验是否可以发送验证码
        MobileCodeDO lastMobileCodePO = mobileCodeMapper.selectLast1ByMobile(mobile);
        if (lastMobileCodePO != null) {
            if (lastMobileCodePO.getTodayIndex() >= sendMaximumQuantityPerDay) { // 超过当天发送的上限。
                throw ServiceExceptionUtil.exception(UserErrorCodeEnum.MOBILE_CODE_EXCEED_SEND_MAXIMUM_QUANTITY_PER_DAY.getCode());
            }
            if (System.currentTimeMillis() - lastMobileCodePO.getCreateTime().getTime() < sendFrequency) { // 发送过于频繁
                throw ServiceExceptionUtil.exception(UserErrorCodeEnum.MOBILE_CODE_SEND_TOO_FAST.getCode());
            }
            // TODO 提升，每个 IP 每天可发送数量
            // TODO 提升，每个 IP 每小时可发送数量
        }
        // 创建验证码记录
        MobileCodeDO newMobileCodePO = new MobileCodeDO().setMobile(mobile)
                .setCode("9999") // TODO 芋艿，随机 4 位验证码 or 6 位验证码
                .setTodayIndex(lastMobileCodePO != null ? lastMobileCodePO.getTodayIndex() : 1)
                .setUsed(false).setCreateTime(new Date());
        mobileCodeMapper.insert(newMobileCodePO);
        // TODO 发送验证码短信
    }

}
