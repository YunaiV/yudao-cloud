package cn.iocoder.mall.system.biz.dao.oauth2;

import cn.iocoder.mall.system.biz.dataobject.oauth2.OAuth2RefreshTokenDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuth2RefreshTokenMapper extends BaseMapper<OAuth2RefreshTokenDO> {

    default int updateToInvalid(Integer accountId) {
        QueryWrapper<OAuth2RefreshTokenDO> query = new QueryWrapper<OAuth2RefreshTokenDO>()
                .eq("account_id", accountId)
                .eq("valid", true);
        return update(new OAuth2RefreshTokenDO().setValid(false), query);
    }

}
