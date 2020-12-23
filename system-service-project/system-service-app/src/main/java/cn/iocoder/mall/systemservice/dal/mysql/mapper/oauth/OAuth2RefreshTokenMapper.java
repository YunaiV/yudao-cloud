package cn.iocoder.mall.systemservice.dal.mysql.mapper.oauth;

import cn.iocoder.mall.systemservice.dal.mysql.dataobject.oauth.OAuth2RefreshTokenDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuth2RefreshTokenMapper extends BaseMapper<OAuth2RefreshTokenDO> {

    default int deleteByUserIdAndUserType(Integer userId, Integer userType) {
        return delete(new QueryWrapper<OAuth2RefreshTokenDO>()
                .eq("user_id", userId).eq("user_type", userType));
    }

}
