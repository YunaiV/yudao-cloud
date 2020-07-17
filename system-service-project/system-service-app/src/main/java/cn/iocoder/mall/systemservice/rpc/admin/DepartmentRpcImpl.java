package cn.iocoder.mall.systemservice.rpc.admin;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.systemservice.manager.admin.DepartmentManager;
import cn.iocoder.mall.systemservice.rpc.admin.dto.DepartmentCreateDTO;
import cn.iocoder.mall.systemservice.rpc.admin.dto.DepartmentUpdateDTO;
import cn.iocoder.mall.systemservice.rpc.admin.vo.DepartmentVO;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
* 部门 Rpc 实现类
*/
@Service(version = "${dubbo.provider.DepartmentRpc.version}")
public class DepartmentRpcImpl implements DepartmentRpc {

    @Autowired
    private DepartmentManager departmentManager;

    @Override
    public CommonResult<Integer> createDepartment(DepartmentCreateDTO createDTO) {
        return success(departmentManager.createDepartment(createDTO));
    }

    @Override
    public CommonResult<Boolean> updateDepartment(DepartmentUpdateDTO updateDTO) {
        departmentManager.updateDepartment(updateDTO);
        return success(true);
    }

    @Override
    public CommonResult<Boolean> deleteDepartment(Integer departmentId) {
        departmentManager.deleteDepartment(departmentId);
        return success(true);
    }

    @Override
    public CommonResult<DepartmentVO> getDepartment(Integer departmentId) {
        return success(departmentManager.getDepartment(departmentId));
    }

    @Override
    public CommonResult<List<DepartmentVO>> listDepartments(Collection<Integer> departmentIds) {
        return success(departmentManager.listDepartments(departmentIds));
    }

    @Override
    public CommonResult<List<DepartmentVO>> listDepartments() {
        return success(departmentManager.listDepartments());
    }

}
