package cn.iocoder.mall.system.biz.dao.oauth2;

import cn.iocoder.mall.system.biz.dataobject.oauth2.OAuth2MobileCodeDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuth2MobileCodeMapper extends BaseMapper<OAuth2MobileCodeDO> {

    /**
     * 获得手机号的最后一个手机验证码
     *
     * @param mobile 手机号
     * @return 手机验证码
     */
    default OAuth2MobileCodeDO selectLastByMobile(String mobile) {
        QueryWrapper<OAuth2MobileCodeDO> query = new QueryWrapper<OAuth2MobileCodeDO>()
                .eq("mobile", mobile)
                .orderByDesc("id")
                .last("limit 1");
        return selectOne(query);
    }

}
