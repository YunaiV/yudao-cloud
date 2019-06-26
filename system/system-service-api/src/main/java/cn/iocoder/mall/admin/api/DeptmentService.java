package cn.iocoder.mall.admin.api;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.admin.api.bo.deptment.DeptmentBO;
import cn.iocoder.mall.admin.api.dto.depetment.DeptmentAddDTO;
import cn.iocoder.mall.admin.api.dto.depetment.DeptmentPageDTO;
import cn.iocoder.mall.admin.api.dto.depetment.DeptmentUpdateDTO;

import java.util.List;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-06-14
 * @time: 19:35
 */
public interface DeptmentService {

    DeptmentBO addDeptment(Integer adminId, DeptmentAddDTO deptmentAddDTO);

    Boolean deleteDeptment(Integer adminId, Integer deptmentId);

    Boolean updateDeptment(Integer adminId, DeptmentUpdateDTO deptmentUpdateDTO);

    PageResult<DeptmentBO> getPageRootDeptment(DeptmentPageDTO deptmentPageDTO);

    List<DeptmentBO> getAllDeptments();

    List<DeptmentBO> getAllNotRootDeptment();
}
