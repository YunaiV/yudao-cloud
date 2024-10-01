package cn.iocoder.yudao.module.product.api.spu;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.product.api.spu.dto.ProductSpuRespDTO;
import cn.iocoder.yudao.module.product.enums.ApiConstants;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertMap;

@FeignClient(name = ApiConstants.NAME) // TODO 芋艿：fallbackFactory =
@Tag(name = "RPC 服务 - 商品 SPU")
public interface ProductSpuApi {

    String PREFIX = ApiConstants.PREFIX + "/spu";

    @GetMapping(PREFIX + "/list")
    @Schema(description = "批量查询 SPU 数组")
    @Parameter(name = "ids", description = "SPU 编号列表", required = true, example = "1,3,5")
    CommonResult<List<ProductSpuRespDTO>> getSpuList(@RequestParam("ids") Collection<Long> ids);

    /**
     * 批量查询 SPU MAP
     *
     * @param ids SPU 编号列表
     * @return SPU MAP
     */
    default Map<Long, ProductSpuRespDTO> getSpusMap(Collection<Long> ids) {
        return convertMap(getSpuList(ids).getCheckedData(), ProductSpuRespDTO::getId);
    }

    @GetMapping(PREFIX + "/valid")
    @Schema(description = "批量查询 SPU 数组，并且校验是否 SPU 是否有效")
    @Parameter(name = "ids", description = "SPU 编号列表", required = true, example = "1,3,5")
    CommonResult<List<ProductSpuRespDTO>> validateSpuList(@RequestParam("ids") Collection<Long> ids);

    @GetMapping(PREFIX + "/get")
    @Schema(description = "获得 SPU")
    @Parameter(name = "id", description = "SPU 编号", required = true, example = "1")
    CommonResult<ProductSpuRespDTO> getSpu(@RequestParam("id") Long id);

}
