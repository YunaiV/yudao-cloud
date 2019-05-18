package cn.iocoder.mall.admin.dao;

import cn.iocoder.mall.admin.dataobject.OAuth2RefreshTokenDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuth2RefreshTokenMapper extends BaseMapper<OAuth2RefreshTokenDO> {

    default int updateToInvalid(Integer userId, Integer userType) {
        QueryWrapper<OAuth2RefreshTokenDO> query = new QueryWrapper<OAuth2RefreshTokenDO>()
                .eq("user_id", userId).eq("user_type", userType)
                .eq("valid", true);
        return update(new OAuth2RefreshTokenDO().setValid(false), query);
    }

}
