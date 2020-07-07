package cn.iocoder.mall.userservice.dal.mysql.mapper.user;

import cn.iocoder.mall.userservice.dal.mysql.dataobject.user.UserDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<UserDO> {

    default UserDO selectByMobile(String mobile) {
        return selectOne(new QueryWrapper<UserDO>()
                .eq("mobile", mobile)
        );
    }

//    /**
//     * 根据条件分页查询用户列表
//     * @param userPageDTO
//     * @return
//     */
//    default IPage<UserDO> selectUserPage(UserPageDTO userPageDTO) {
//        // TODO FROM 芋艿 to jwf1173：看下 QueryWrapperX 噢，已经提供判空啦 [DONE]
//        return this.selectPage(new Page<>(userPageDTO.getPageNo(), userPageDTO.getPageSize()),
//                new QueryWrapperX<UserDO>()
//                        .eq(StringUtils.isNotBlank(userPageDTO.getNickname()), "nickname", userPageDTO.getNickname())
//                        .eq(null != userPageDTO.getStatus(), "status", userPageDTO.getStatus())
//        );
//    }

}
