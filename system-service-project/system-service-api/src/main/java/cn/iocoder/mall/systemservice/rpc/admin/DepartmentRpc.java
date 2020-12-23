package cn.iocoder.mall.systemservice.rpc.admin;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.systemservice.rpc.admin.dto.DepartmentCreateDTO;
import cn.iocoder.mall.systemservice.rpc.admin.dto.DepartmentUpdateDTO;
import cn.iocoder.mall.systemservice.rpc.admin.vo.DepartmentVO;

import java.util.Collection;
import java.util.List;

/**
* 部门 Rpc 接口
*/
public interface DepartmentRpc {

    /**
    * 创建部门
    *
    * @param createDTO 创建部门 DTO
    * @return 部门编号
    */
    CommonResult<Integer> createDepartment(DepartmentCreateDTO createDTO);

    /**
    * 更新部门
    *
    * @param updateDTO 更新部门 DTO
    */
    CommonResult<Boolean> updateDepartment(DepartmentUpdateDTO updateDTO);

    /**
    * 删除部门
    *
    * @param departmentId 部门编号
    */
    CommonResult<Boolean> deleteDepartment(Integer departmentId);

    /**
    * 获得部门
    *
    * @param departmentId 部门编号
    * @return 部门
    */
    CommonResult<DepartmentVO> getDepartment(Integer departmentId);

    /**
    * 获得部门列表
    *
    * @param departmentIds 部门编号列表
    * @return 部门列表
    */
    CommonResult<List<DepartmentVO>> listDepartments(Collection<Integer> departmentIds);

    /**
     * 获得部门全列表
     *
     * @return 资源列表
     */
    CommonResult<List<DepartmentVO>> listDepartments();

}
