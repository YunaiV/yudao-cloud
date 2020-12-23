package cn.iocoder.mall.userservice.dal.mysql.mapper.user;

import cn.iocoder.mall.mybatis.core.query.QueryWrapperX;
import cn.iocoder.mall.userservice.dal.mysql.dataobject.user.UserDO;
import cn.iocoder.mall.userservice.service.user.bo.UserPageBO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<UserDO> {

    default UserDO selectByMobile(String mobile) {
        return selectOne(new QueryWrapper<UserDO>()
                .eq("mobile", mobile)
        );
    }

    default IPage<UserDO> selectPage(UserPageBO pageBO) {
        return selectPage(new Page<>(pageBO.getPageNo(), pageBO.getPageSize()),
                new QueryWrapperX<UserDO>().likeIfPresent("nickname", pageBO.getNickname())
                    .eqIfPresent("status", pageBO.getStatus()));
    }

}
