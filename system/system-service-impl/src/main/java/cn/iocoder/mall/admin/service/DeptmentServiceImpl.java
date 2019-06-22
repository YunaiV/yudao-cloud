package cn.iocoder.mall.admin.service;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.admin.api.DeptmentService;
import cn.iocoder.mall.admin.api.bo.deptment.DeptmentBO;
import cn.iocoder.mall.admin.api.constant.AdminErrorCodeEnum;
import cn.iocoder.mall.admin.api.constant.DeptmentConstants;
import cn.iocoder.mall.admin.api.dto.depetment.DeptmentAddDTO;
import cn.iocoder.mall.admin.api.dto.depetment.DeptmentPageDTO;
import cn.iocoder.mall.admin.convert.DeptmentConvert;
import cn.iocoder.mall.admin.dao.DeptmentMapper;
import cn.iocoder.mall.admin.dataobject.DeptmentDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-06-14
 * @time: 19:30
 */
@Service
public class DeptmentServiceImpl implements DeptmentService {

    @Autowired
    private DeptmentMapper deptmentMapper;

    @Override
    public DeptmentBO addDeptment(Integer adminId, DeptmentAddDTO deptmentAddDTO) {
        if (deptmentAddDTO.getPid() != 0 &&
                deptmentMapper.selectById(deptmentAddDTO.getPid()) == null) {
            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.DEPT_PARENT_NOT_EXITS.getCode());
        }
        //不同的大部门下好像可以小部门名字一样，验证同级别部门名字
        if (null != deptmentMapper.findDeptByNameAndPid(deptmentAddDTO.getName(), deptmentAddDTO.getPid())) {
            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.DEPT_SAME_LEVEL_NAME_EXITS.getCode());
        }
        DeptmentDO deptmentDO = DeptmentConvert.INSTANCE.convert(deptmentAddDTO);
        deptmentMapper.insert(deptmentDO);
        return DeptmentConvert.INSTANCE.convert(deptmentDO);
    }

    @Override
    public PageResult<DeptmentBO> getPageRootDeptment(DeptmentPageDTO deptmentPageDTO) {
        IPage<DeptmentDO> page = deptmentMapper.selectDeptPage(deptmentPageDTO, DeptmentConstants.PID_ROOT);
        return DeptmentConvert.INSTANCE.convert(page);
    }

    @Override
    public List<DeptmentBO> getAllDeptments() {
        List<DeptmentDO> list = deptmentMapper.getDeptByPid(null);
        return DeptmentConvert.INSTANCE.convert(list);
    }

    @Override
    public List<DeptmentBO> getAllNotRootDeptment() {
        List<DeptmentDO> list = deptmentMapper.getDeptExcudePid(DeptmentConstants.PID_ROOT);
        return DeptmentConvert.INSTANCE.convert(list);
    }

}
