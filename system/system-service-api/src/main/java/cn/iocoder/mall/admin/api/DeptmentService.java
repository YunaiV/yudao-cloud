package cn.iocoder.mall.admin.api;

import cn.iocoder.mall.admin.api.bo.deptment.DeptmentBO;
import cn.iocoder.mall.admin.api.dto.depetment.DeptmentAddDTO;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-06-14
 * @time: 19:35
 */
public interface DeptmentService {

    DeptmentBO addDeptment(Integer adminId, DeptmentAddDTO deptmentAddDTO);
}
