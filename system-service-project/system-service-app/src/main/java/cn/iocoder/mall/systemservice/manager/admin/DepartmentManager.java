package cn.iocoder.mall.systemservice.manager.admin;

import cn.iocoder.mall.systemservice.convert.admin.DepartmentConvert;
import cn.iocoder.mall.systemservice.rpc.admin.dto.DepartmentCreateDTO;
import cn.iocoder.mall.systemservice.rpc.admin.dto.DepartmentUpdateDTO;
import cn.iocoder.mall.systemservice.rpc.admin.vo.DepartmentVO;
import cn.iocoder.mall.systemservice.service.admin.DepartmentService;
import cn.iocoder.mall.systemservice.service.admin.bo.DepartmentBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
* 部门 Manager
*/
@Service
public class DepartmentManager {

    @Autowired
    private DepartmentService departmentService;

    /**
    * 创建部门
    *
    * @param createDTO 创建部门 DTO
    * @return 部门
    */
    public Integer createDepartment(DepartmentCreateDTO createDTO) {
        DepartmentBO departmentBO = departmentService.createDepartment(DepartmentConvert.INSTANCE.convert(createDTO));
        return departmentBO.getId();
    }

    /**
    * 更新部门
    *
    * @param updateDTO 更新部门 DTO
    */
    public void updateDepartment(DepartmentUpdateDTO updateDTO) {
        departmentService.updateDepartment(DepartmentConvert.INSTANCE.convert(updateDTO));
    }

    /**
    * 删除部门
    *
    * @param departmentId 部门编号
    */
    public void deleteDepartment(Integer departmentId) {
        departmentService.deleteDepartment(departmentId);
    }

    /**
    * 获得部门
    *
    * @param departmentId 部门编号
    * @return 部门
    */
    public DepartmentVO getDepartment(Integer departmentId) {
        DepartmentBO departmentBO = departmentService.getDepartment(departmentId);
        return DepartmentConvert.INSTANCE.convert(departmentBO);
    }

    /**
    * 获得部门列表
    *
    * @param departmentIds 部门编号列表
    * @return 部门列表
    */
    public List<DepartmentVO> listDepartments(Collection<Integer> departmentIds) {
        List<DepartmentBO> departmentBOs = departmentService.listDepartments(departmentIds);
        return DepartmentConvert.INSTANCE.convertList02(departmentBOs);
    }

    /**
     * 获得部门全列表
     *
     * @return 资源列表
     */
    public List<DepartmentVO> listDepartments() {
        List<DepartmentBO> departmentBOs = departmentService.listDepartments();
        return DepartmentConvert.INSTANCE.convertList02(departmentBOs);
    }

}
