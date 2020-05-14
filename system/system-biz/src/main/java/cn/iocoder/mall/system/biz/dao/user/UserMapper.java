package cn.iocoder.mall.system.biz.dao.user;

import cn.iocoder.mall.system.biz.dataobject.user.UserDO;
import cn.iocoder.mall.system.biz.dto.user.UserPageDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<UserDO> {

    default UserDO selectByAccountId(Integer accountId) {
        return selectOne(new QueryWrapper<UserDO>()
                .eq("account_id", accountId)
        );
    }

    /**
     * 根据条件分页查询用户列表
     * @param userPageDTO
     * @return
     */
    default IPage<UserDO> selectUserPage(UserPageDTO userPageDTO) {
        // TODO FROM 芋艿 to jwf1173：看下 QueryWrapperX 噢，已经提供判空啦
        // TODO FROM 伟帆 to 芋艿： 这里是使用MP原生的判空，支持lambda好，还是使用QueryWrapperX，使用字段名字符串的好呢
        return this.selectPage(new Page<>(userPageDTO.getPageNo(), userPageDTO.getPageSize()),
                Wrappers.<UserDO>query().lambda()
                .eq(StringUtils.isNotBlank(userPageDTO.getNickname()), UserDO::getNickname, userPageDTO.getNickname())
                .eq(null != userPageDTO.getStatus(), UserDO::getStatus, userPageDTO.getStatus())
        );
    }

}
