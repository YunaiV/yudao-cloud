package cn.iocoder.mall.system.biz.dao.oauth2;

import cn.iocoder.mall.system.biz.dataobject.oauth2.OAuth2AccessTokenDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuth2AccessTokenMapper extends BaseMapper<OAuth2AccessTokenDO> {

    default int updateToInvalid(Integer accountId) {
        QueryWrapper<OAuth2AccessTokenDO> query = new QueryWrapper<OAuth2AccessTokenDO>()
                .eq("account_id", accountId)
                .eq("valid", true);
        return update(new OAuth2AccessTokenDO().setValid(false), query);
    }

    default int updateToInvalidByRefreshToken(String refreshToken) {
        QueryWrapper<OAuth2AccessTokenDO> query = new QueryWrapper<OAuth2AccessTokenDO>()
                .eq("refresh_token", refreshToken).eq("valid", true);
        return update(new OAuth2AccessTokenDO().setValid(false), query);
    }

}
