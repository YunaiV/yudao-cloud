package cn.iocoder.mall.admin.application.controller.admins;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.admin.api.DeptmentService;
import cn.iocoder.mall.admin.api.bo.deptment.DeptmentBO;
import cn.iocoder.mall.admin.api.constant.ResourceConstants;
import cn.iocoder.mall.admin.api.dto.depetment.DeptmentAddDTO;
import cn.iocoder.mall.admin.api.dto.depetment.DeptmentPageDTO;
import cn.iocoder.mall.admin.api.dto.depetment.DeptmentUpdateDTO;
import cn.iocoder.mall.admin.application.convert.DeptmentConvert;
import cn.iocoder.mall.admin.application.vo.deptment.DeptmentVO;
import cn.iocoder.mall.admin.application.vo.resource.ResourceTreeNodeVO;
import cn.iocoder.mall.admin.sdk.context.AdminSecurityContext;
import cn.iocoder.mall.admin.sdk.context.AdminSecurityContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.iocoder.common.framework.vo.CommonResult.success;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-06-14
 * @time: 19:07
 */
@RestController
@RequestMapping("admins/dept")
@Api("部门模块")
public class DeptmentController {

    @Autowired
    private DeptmentService deptmentService;

    @GetMapping("tree/all")
    @ApiOperation(value = "根部门的部门树")
    public CommonResult<List<DeptmentVO>> treeAll(){
        List<DeptmentBO> list = deptmentService.getAllDeptments();
        List<DeptmentVO> voList = DeptmentConvert.INSTANCE.convert(list);
        Map<Integer, DeptmentVO> nodeMap = calcNodeMap(voList);
        // 获得到所有的根节点
        List<DeptmentVO> rootNodes = nodeMap.values().stream()
                .filter(node -> node.getPid().equals(ResourceConstants.PID_ROOT))
                .collect(Collectors.toList());
        return success(rootNodes);
    }

    @GetMapping("tree/page")
    @ApiOperation(value = "根部门分页的部门树")
    public CommonResult<PageResult<DeptmentVO>> treePage(@Validated DeptmentPageDTO deptmentPageDTO){
        PageResult<DeptmentBO> pageResult = deptmentService.getPageRootDeptment(deptmentPageDTO);
        PageResult<DeptmentVO> voPageResult = DeptmentConvert.INSTANCE.convert(pageResult);
        List<DeptmentBO> list = deptmentService.getAllDeptments();
        List<DeptmentVO> voList = DeptmentConvert.INSTANCE.convert(list);
        Map<Integer, DeptmentVO> nodeMap = calcNodeMap(voList);
        voPageResult.getList().forEach(d->{
            d.setChildren(nodeMap.get(d.getId()).getChildren());
        });
        return success(voPageResult);
    }

    @PostMapping("add")
    @ApiOperation(value = "新增部门", notes = "选择部门名称，父级部门")
    public CommonResult<DeptmentBO> add(@RequestBody DeptmentAddDTO deptmentAddDTO){
        return success(deptmentService.addDeptment(
                AdminSecurityContextHolder.getContext().getAdminId(), deptmentAddDTO));

    }

    @PostMapping("delete")
    @ApiOperation(value = "删除部门")
    @ApiImplicitParam(name = "id", value = "部门id", required = true, example = "1")
    public CommonResult<Boolean> delete(@RequestParam("id") Integer id){

        return success(deptmentService.deleteDeptment(
                AdminSecurityContextHolder.getContext().getAdminId(), id
        ));
    }

    @PostMapping("update")
    @ApiOperation(value = "更新部门")
    public CommonResult<Boolean> update(@RequestBody DeptmentUpdateDTO deptmentUpdateDTO){
        return success(deptmentService.updateDeptment(
                AdminSecurityContextHolder.getContext().getAdminId(), deptmentUpdateDTO
        ));
    }

    private Map<Integer, DeptmentVO> calcNodeMap(List<DeptmentVO> voList){
        Map<Integer, DeptmentVO> nodeMap = voList.stream().collect(Collectors.toMap(e->e.getId(), e->e));

        nodeMap.values().stream()
                .filter(node -> !node.getPid().equals(ResourceConstants.PID_ROOT))
                .forEach((childNode) -> {
                    // 获得父节点
                    DeptmentVO parentNode = nodeMap.get(childNode.getPid());
                    if (parentNode.getChildren() == null) { // 初始化 children 数组
                        parentNode.setChildren(new ArrayList<>());
                    }
                    // 将自己添加到父节点中
                    parentNode.getChildren().add(childNode);
                });
        return nodeMap;
    }


}
