package cn.iocoder.mall.systemservice.dal.mysql.mapper.permission;

import cn.iocoder.mall.systemservice.dal.mysql.dataobject.permission.AdminRoleDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface AccountRoleMapper extends BaseMapper<AdminRoleDO> {

    default List<AdminRoleDO> selectByAccountId(Integer accountId) {
        return selectList(new QueryWrapper<AdminRoleDO>().eq("account_id", accountId));
    }

    default List<AdminRoleDO> selectListByAccountIds(Collection<Integer> accountIds) {
        return selectList(new QueryWrapper<AdminRoleDO>().in("account_id", accountIds));
    }

    default int deleteByAccountId(Integer accountId) {
        return delete(new QueryWrapper<AdminRoleDO>().eq("account_id", accountId));
    }

    default int deleteByRoleId(Integer roleId) {
        return delete(new QueryWrapper<AdminRoleDO>().eq("role_id", roleId));
    }

    /**
     * 批量插入。因为 MyBaits Plus 的批量插入是基于 Service 实现，所以只好写 XML
     *
     * @param accountRoleDOs 数组
     */
    int insertList(@Param("accountRoleDOs") List<AdminRoleDO> accountRoleDOs);

}
