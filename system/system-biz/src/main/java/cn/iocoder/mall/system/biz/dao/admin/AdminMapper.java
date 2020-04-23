package cn.iocoder.mall.system.biz.dao.admin;

import cn.iocoder.mall.system.biz.dataobject.admin.AdminDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper extends BaseMapper<AdminDO> {

    default AdminDO selectByAccountId(Integer accountId) {
        return selectOne(new QueryWrapper<AdminDO>()
                .eq("account_id", accountId)
        );
    }

}
