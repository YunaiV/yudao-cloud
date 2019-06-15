package cn.iocoder.mall.admin.dao;

import cn.iocoder.mall.admin.dataobject.AdminDO;
import cn.iocoder.mall.admin.dataobject.DeptmentDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-06-14
 * @time: 19:26
 */
public interface DeptmentMapper extends BaseMapper<DeptmentDO> {

    default DeptmentDO findDeptByNameAndPid(String name, Integer pid){
        return selectOne(new QueryWrapper<DeptmentDO>()
                .eq("name", name)
                .eq("pid", pid)
                .eq("deleted", false)
        );
    }


}
