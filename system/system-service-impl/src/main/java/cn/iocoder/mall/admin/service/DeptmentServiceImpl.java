package cn.iocoder.mall.admin.service;

import cn.iocoder.common.framework.util.ExceptionUtil;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.admin.api.DeptmentService;
import cn.iocoder.mall.admin.api.bo.deptment.DeptmentBO;
import cn.iocoder.mall.admin.api.constant.AdminErrorCodeEnum;
import cn.iocoder.mall.admin.api.constant.DeptmentConstants;
import cn.iocoder.mall.admin.api.dto.depetment.DeptmentAddDTO;
import cn.iocoder.mall.admin.api.dto.depetment.DeptmentPageDTO;
import cn.iocoder.mall.admin.api.dto.depetment.DeptmentUpdateDTO;
import cn.iocoder.mall.admin.convert.DeptmentConvert;
import cn.iocoder.mall.admin.dao.DeptmentMapper;
import cn.iocoder.mall.admin.dao.DeptmentRoleMapper;
import cn.iocoder.mall.admin.dataobject.DeptmentDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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

    @Autowired
    private DeptmentRoleMapper deptmentRoleMapper;

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
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteDeptment(Integer adminId, Integer deptmentId) {
        if(deptmentMapper.selectById(deptmentId) == null){
            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.DEPT_NOT_EXITS.getCode());
        }

        if(!CollectionUtils.isEmpty(deptmentMapper.getDeptByPid(deptmentId))){
            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.DEPT_EXITS_CHILDREN.getCode());
        }

        deptmentMapper.deleteById(deptmentId);

        deptmentRoleMapper.deleteByDeptmentId(deptmentId);

        return true;
    }

    @Override
    @Transactional
    public Boolean updateDeptment(Integer adminId, DeptmentUpdateDTO deptmentUpdateDTO) {
        //判断需要更新的部门是否存在
        if(deptmentMapper.selectById(deptmentUpdateDTO.getId()) == null){
            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.DEPT_NOT_EXITS.getCode());
        }

        //不能选择当前部门作为自己的父级部门
        if(deptmentUpdateDTO.getId().equals(deptmentUpdateDTO.getPid())){
            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.DEPT_PARENT_NOT_LEGAL.getCode());
        }

        if(deptmentUpdateDTO.getPid()!=null &&
                !DeptmentConstants.PID_ROOT.equals(deptmentUpdateDTO.getPid())
                && deptmentMapper.selectById(deptmentUpdateDTO.getPid())==null){
            throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.DEPT_PARENT_NOT_EXITS.getCode());
        }

        DeptmentDO deptmentDO = DeptmentConvert.INSTANCE.convert(deptmentUpdateDTO);
        deptmentMapper.updateById(deptmentDO);

        return true;
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
