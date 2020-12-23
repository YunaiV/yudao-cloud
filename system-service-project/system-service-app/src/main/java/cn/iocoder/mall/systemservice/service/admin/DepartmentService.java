package cn.iocoder.mall.systemservice.service.admin;

import cn.iocoder.common.framework.exception.util.ServiceExceptionUtil;
import cn.iocoder.mall.systemservice.convert.admin.DepartmentConvert;
import cn.iocoder.mall.systemservice.dal.mysql.dataobject.admin.DepartmentDO;
import cn.iocoder.mall.systemservice.dal.mysql.mapper.admin.AdminMapper;
import cn.iocoder.mall.systemservice.dal.mysql.mapper.admin.DepartmentMapper;
import cn.iocoder.mall.systemservice.enums.admin.DepartmentIdEnum;
import cn.iocoder.mall.systemservice.service.admin.bo.DepartmentBO;
import cn.iocoder.mall.systemservice.service.admin.bo.DepartmentCreateBO;
import cn.iocoder.mall.systemservice.service.admin.bo.DepartmentUpdateBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.mall.systemservice.enums.SystemErrorCodeConstants.*;

/**
* 部门 Service
*/
@Service
@Validated
public class DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private AdminMapper adminMapper;

    /**
    * 创建部门
    *
    * @param createBO 创建部门 BO
    * @return 部门
    */
    public DepartmentBO createDepartment(@Valid DepartmentCreateBO createBO) {
        // 校验父部门存在
        checkParentDepartment(createBO.getPid(), null);
        // 校验部门（自己）
        checkDepartment(createBO.getPid(), createBO.getName(), null);
        // 插入到数据库
        DepartmentDO departmentDO = DepartmentConvert.INSTANCE.convert(createBO);
        departmentMapper.insert(departmentDO);
        // 返回
        return DepartmentConvert.INSTANCE.convert(departmentDO);
    }

    /**
    * 更新部门
    *
    * @param updateBO 更新部门 BO
    */
    public void updateDepartment(@Valid DepartmentUpdateBO updateBO) {
        // 校验更新的部门是否存在
        if (departmentMapper.selectById(updateBO.getId()) == null) {
            throw ServiceExceptionUtil.exception(DEPARTMENT_NOT_FOUND);
        }
        // 校验父部门存在
        checkParentDepartment(updateBO.getPid(), updateBO.getId());
        // 校验部门（自己）
        checkDepartment(updateBO.getPid(), updateBO.getName(), updateBO.getId());
        // 更新到数据库
        DepartmentDO updateObject = DepartmentConvert.INSTANCE.convert(updateBO);
        departmentMapper.updateById(updateObject);
    }

    /**
    * 删除部门
    *
    * @param departmentId 部门编号
    */
    public void deleteDepartment(Integer departmentId) {
        // 校验删除的部门是否存在
        if (departmentMapper.selectById(departmentId) == null) {
            throw ServiceExceptionUtil.exception(DEPARTMENT_NOT_FOUND);
        }
        // 校验部门里没管理员
        if (adminMapper.selectCountByDepartmentId(departmentId) > 0) {
            throw ServiceExceptionUtil.exception(DEPARTMENT_NOT_FOUND);
        }
        // 标记删除
        departmentMapper.deleteById(departmentId);
    }

    /**
    * 获得部门
    *
    * @param departmentId 部门编号
    * @return 部门
    */
    public DepartmentBO getDepartment(Integer departmentId) {
        DepartmentDO departmentDO = departmentMapper.selectById(departmentId);
        return DepartmentConvert.INSTANCE.convert(departmentDO);
    }

    /**
    * 获得部门列表
    *
    * @param departmentIds 部门编号列表
    * @return 部门列表
    */
    public List<DepartmentBO> listDepartments(Collection<Integer> departmentIds) {
        List<DepartmentDO> departmentDOs = departmentMapper.selectBatchIds(departmentIds);
        return DepartmentConvert.INSTANCE.convertList(departmentDOs);
    }

    /**
     * 获得部门全列表
     *
     * @return 资源列表
     */
    public List<DepartmentBO> listDepartments() {
        List<DepartmentDO> departmentDOs = departmentMapper.selectList(null);
        return DepartmentConvert.INSTANCE.convertList(departmentDOs);
    }

    /**
     * 校验父部门是否合法
     *
     * 1. 不能设置自己为父部门
     * 2. 父部门不存在
     *
     * @param pid 父部门编号
     * @param childId 当前部门编号
     */
    private void checkParentDepartment(Integer pid, Integer childId) {
        if (pid == null || DepartmentIdEnum.ROOT.getId().equals(pid)) {
            return;
        }
        // 不能设置自己为父部门
        if (pid.equals(childId)) {
            throw ServiceExceptionUtil.exception(DEPARTMENT_PARENT_ERROR);
        }
        DepartmentDO resource = departmentMapper.selectById(pid);
        // 父部门不存在
        if (resource == null) {
            throw ServiceExceptionUtil.exception(DEPARTMENT_PARENT_NOT_EXITS);
        }
    }

    /**
     * 校验部门是否合法
     *
     * 1. 校验相同父部门编号下，是否存在相同的部门名
     *
     * @param name 部门名字
     * @param pid 父部门编号
     * @param id 部门编号
     */
    private void checkDepartment(Integer pid, String name, Integer id) {
        DepartmentDO resource = departmentMapper.selectByPidAndName(pid, name);
        if (resource == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的部门
        if (id == null) {
            throw ServiceExceptionUtil.exception(DEPARTMENT_NAME_DUPLICATE);
        }
        if (!resource.getId().equals(id)) {
            throw ServiceExceptionUtil.exception(DEPARTMENT_NAME_DUPLICATE);
        }
    }

}
