package cn.iocoder.mall.managementweb.manager.admin;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.managementweb.controller.admin.dto.DepartmentCreateDTO;
import cn.iocoder.mall.managementweb.controller.admin.dto.DepartmentUpdateDTO;
import cn.iocoder.mall.managementweb.controller.admin.vo.DepartmentTreeNodeVO;
import cn.iocoder.mall.managementweb.controller.admin.vo.DepartmentVO;
import cn.iocoder.mall.managementweb.convert.admin.DepartmentConvert;
import cn.iocoder.mall.systemservice.enums.admin.DepartmentIdEnum;
import cn.iocoder.mall.systemservice.rpc.admin.DepartmentRpc;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
* 部门 Manager
*/
@Service
@Slf4j
public class DepartmentManager {

    @Reference(version = "${dubbo.consumer.DepartmentRpc.version}")
    private DepartmentRpc departmentRpc;

    /**
    * 创建部门
    *
    * @param createDTO 创建部门 DTO
    * @return 部门
    */
    public Integer createDepartment(DepartmentCreateDTO createDTO) {
        CommonResult<Integer> createDepartmentResult = departmentRpc.createDepartment(DepartmentConvert.INSTANCE.convert(createDTO));
        createDepartmentResult.checkError();
        return createDepartmentResult.getData();
    }

    /**
    * 更新部门
    *
    * @param updateDTO 更新部门 DTO
    */
    public void updateDepartment(DepartmentUpdateDTO updateDTO) {
        CommonResult<Boolean> updateDepartmentResult = departmentRpc.updateDepartment(DepartmentConvert.INSTANCE.convert(updateDTO));
        updateDepartmentResult.checkError();
    }

    /**
    * 删除部门
    *
    * @param departmentId 部门编号
    */
    public void deleteDepartment(Integer departmentId) {
        CommonResult<Boolean> deleteDepartmentResult = departmentRpc.deleteDepartment(departmentId);
        deleteDepartmentResult.checkError();
    }

    /**
    * 获得部门
    *
    * @param departmentId 部门编号
    * @return 部门
    */
    public DepartmentVO getDepartment(Integer departmentId) {
        CommonResult<cn.iocoder.mall.systemservice.rpc.admin.vo.DepartmentVO> getDepartmentResult = departmentRpc.getDepartment(departmentId);
        getDepartmentResult.checkError();
        return DepartmentConvert.INSTANCE.convert(getDepartmentResult.getData());
    }

    /**
    * 获得部门列表
    *
    * @param departmentIds 部门编号列表
    * @return 部门列表
    */
    public List<DepartmentVO> listDepartments(List<Integer> departmentIds) {
        CommonResult<List<cn.iocoder.mall.systemservice.rpc.admin.vo.DepartmentVO>> listDepartmentResult = departmentRpc.listDepartments(departmentIds);
        listDepartmentResult.checkError();
        return DepartmentConvert.INSTANCE.convertList(listDepartmentResult.getData());
    }

    /**
     * 获得部门树结构
     *
     * @return 部门树结构
     */
    public List<DepartmentTreeNodeVO> treeDepartment() {
        // 获得资源全列表
        CommonResult<List<cn.iocoder.mall.systemservice.rpc.admin.vo.DepartmentVO>> listDepartmentResult = departmentRpc.listDepartments();
        listDepartmentResult.checkError();
        // 构建菜单树
        return buildDepartmentTree(listDepartmentResult.getData());
    }

    /**
     * 构建部门树
     *
     * @param departmentVOs 部门列表
     * @return 部门树
     */
    public static List<DepartmentTreeNodeVO> buildDepartmentTree(List<cn.iocoder.mall.systemservice.rpc.admin.vo.DepartmentVO> departmentVOs) {
        // 排序，保证菜单的有序性
        departmentVOs.sort(Comparator.comparing(cn.iocoder.mall.systemservice.rpc.admin.vo.DepartmentVO::getSort));
        // 构建菜单树
        // 使用 LinkedHashMap 的原因，是为了排序 。实际也可以用 Stream API ，就是太丑了。
        Map<Integer, DepartmentTreeNodeVO> treeNodeMap = new LinkedHashMap<>();
        departmentVOs.forEach(departmentVO -> treeNodeMap.put(departmentVO.getId(), DepartmentConvert.INSTANCE.convertTreeNode(departmentVO)));
        // 处理父子关系
        treeNodeMap.values().stream().filter(node -> !node.getPid().equals(DepartmentIdEnum.ROOT.getId())).forEach((childNode) -> {
            // 获得父节点
            DepartmentTreeNodeVO parentNode = treeNodeMap.get(childNode.getPid());
            if (parentNode == null) {
                log.error("[buildDepartmentTree][department({}) 找不到父部门({})]", childNode.getId(), childNode.getPid());
                return;
            }
            // 将自己添加到父节点中
            if (parentNode.getChildren() == null) {
                parentNode.setChildren(new ArrayList<>());
            }
            parentNode.getChildren().add(childNode);
        });
        // 获得到所有的根节点
        return treeNodeMap.values().stream().filter(node -> node.getPid().equals(DepartmentIdEnum.ROOT.getId())).collect(Collectors.toList());
    }

}
