package cn.iocoder.yudao.module.system.dal.mysql.sms;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.QueryWrapperX;
import cn.iocoder.yudao.module.system.dal.dataobject.sms.SmsCodeDO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface SmsCodeMapper extends BaseMapperX<SmsCodeDO> {

    /**
     * 获得手机号的最后一个手机验证码
     *
     * @param mobile 手机号
     * @param scene 发送场景，选填
     * @param code 验证码 选填
     * @return 手机验证码
     */
    default SmsCodeDO selectLastByMobile(String mobile, String code, Integer scene) {
        return selectOne(new QueryWrapperX<SmsCodeDO>()
                .eq("mobile", mobile)
                .eqIfPresent("scene", scene)
                .eqIfPresent("code", code)
                .orderByDesc("id")
                .limitN(1));
    }
    /**
     * 获得对应时间内的ip申请验证码时间
     *
     * @param ip ip
     * @param beginTime 今天的开始时间
     * @return 对应时间内的ip申请验证码时间
     */
    default List<LocalDateTime> selectTimeByIpAndCreateTime(String ip, LocalDateTime beginTime) {
        return selectObjs(new QueryWrapperX<SmsCodeDO>()
                .eq("create_ip", ip)
                .ge("create_time", beginTime)
                .select("create_time"));
    }
}
