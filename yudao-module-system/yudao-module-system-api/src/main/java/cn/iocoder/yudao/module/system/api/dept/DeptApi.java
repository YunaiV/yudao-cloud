package cn.iocoder.yudao.module.system.api.dept;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.module.system.api.dept.dto.DeptRespDTO;
import cn.iocoder.yudao.module.system.enums.ApiConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@FeignClient(name = ApiConstants.NAME) // TODO 芋艿：fallbackFactory =
@Api(tags = "RPC 服务 - 部门")
public interface DeptApi {

    String PREFIX = ApiConstants.PREFIX + "/dept";

    @GetMapping(PREFIX + "/get")
    @ApiOperation("获得部门信息")
    @ApiImplicitParam(name = "id", value = "部门编号", example = "1024", required = true, dataTypeClass = Long.class)
    CommonResult<DeptRespDTO> getDept(@RequestParam("id") Long id);

    @GetMapping(PREFIX + "/list")
    @ApiOperation("获得部门信息数组")
    @ApiImplicitParam(name = "ids", value = "部门编号数组", example = "1,2", required = true, allowMultiple = true)
    CommonResult<List<DeptRespDTO>> getDepts(@RequestParam("ids") Collection<Long> ids);

    @GetMapping(PREFIX + "/valid")
    @ApiImplicitParam(name = "ids", value = "部门编号数组", example = "1,2", required = true, allowMultiple = true)
    CommonResult<Boolean> validDepts(@RequestParam("ids") Collection<Long> ids);

    /**
     * 获得指定编号的部门 Map
     *
     * @param ids 部门编号数组
     * @return 部门 Map
     */
    default Map<Long, DeptRespDTO> getDeptMap(Set<Long> ids) {
        CommonResult<List<DeptRespDTO>> result = getDepts(ids);
        result.checkError();
        return CollectionUtils.convertMap(result.getData(), DeptRespDTO::getId);
    }

}
