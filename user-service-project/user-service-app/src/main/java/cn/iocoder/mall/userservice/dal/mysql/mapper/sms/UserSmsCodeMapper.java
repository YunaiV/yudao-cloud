package cn.iocoder.mall.userservice.dal.mysql.mapper.sms;

import cn.iocoder.mall.mybatis.core.query.QueryWrapperX;
import cn.iocoder.mall.userservice.dal.mysql.dataobject.sms.UserSmsCodeDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSmsCodeMapper extends BaseMapper<UserSmsCodeDO> {

    /**
     * 获得手机号的最后一个手机验证码
     *
     * @param mobile 手机号
     * @param scene 发送场景，选填
     * @return 手机验证码
     */
    default UserSmsCodeDO selectLastByMobile(String mobile, Integer scene) {
        QueryWrapperX<UserSmsCodeDO> query = new QueryWrapperX<UserSmsCodeDO>()
                .eq("mobile", mobile)
                .eqIfPresent("scene", scene)
                .orderByDesc("id")
                .last("limit 1");
        return selectOne(query);
    }

}
