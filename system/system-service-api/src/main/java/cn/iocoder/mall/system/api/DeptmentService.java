package cn.iocoder.mall.system.api;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.system.api.bo.deptment.DeptmentBO;
import cn.iocoder.mall.system.api.dto.depetment.DeptmentAddDTO;
import cn.iocoder.mall.system.api.dto.depetment.DeptmentPageDTO;
import cn.iocoder.mall.system.api.dto.depetment.DeptmentUpdateDTO;

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
