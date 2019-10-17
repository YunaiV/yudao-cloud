package cn.iocoder.mall.admin.dao;

import cn.iocoder.mall.admin.dataobject.DeptmentRoleDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-06-14
 * @time: 19:27
 */
public interface DeptmentRoleMapper extends BaseMapper<DeptmentRoleDO> {

    default int deleteByDeptmentId(Integer deptmentId){
        return delete(new QueryWrapper<DeptmentRoleDO>().eq("deptment_id", deptmentId));
    }

    default int deleteByRoleId(Integer roleId){
        return delete(new QueryWrapper<DeptmentRoleDO>().eq("role_id", roleId));
    }

}
