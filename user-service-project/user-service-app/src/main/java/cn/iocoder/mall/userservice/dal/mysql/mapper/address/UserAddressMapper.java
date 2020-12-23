package cn.iocoder.mall.userservice.dal.mysql.mapper.address;

import cn.iocoder.mall.mybatis.core.query.QueryWrapperX;
import cn.iocoder.mall.userservice.dal.mysql.dataobject.address.UserAddressDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import java.util.List;

@Repository
public interface UserAddressMapper extends BaseMapper<UserAddressDO> {

    default List<UserAddressDO> selectListByUserIdAndType(Integer userId, @Nullable Integer type) {
        return selectList(new QueryWrapperX<UserAddressDO>().eq("user_id", userId)
            .eqIfPresent("type", type));
    }

}
