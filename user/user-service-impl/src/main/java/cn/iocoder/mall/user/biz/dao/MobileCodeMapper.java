package cn.iocoder.mall.user.biz.dao;

import cn.iocoder.mall.user.biz.dataobject.MobileCodeDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository // 实际不加也没问entity，就是不想 IDEA 那看到有个报错
public interface MobileCodeMapper extends BaseMapper<MobileCodeDO> {

    /**
     * 获得手机号的最后一个手机验证码
     *
     * @param mobile 手机号
     * @return 手机验证码
     */
    default MobileCodeDO selectLast1ByMobile(String mobile) {
        QueryWrapper<MobileCodeDO> query = new QueryWrapper<MobileCodeDO>()
                .eq("mobile", mobile)
                .orderByDesc("id")
                .last("limit 1");
        return selectOne(query);
    }

}
