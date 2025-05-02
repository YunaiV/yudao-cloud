package cn.iocoder.yudao.module.product.api.sku;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.product.api.sku.dto.ProductSkuRespDTO;
import cn.iocoder.yudao.module.product.api.sku.dto.ProductSkuUpdateStockReqDTO;
import cn.iocoder.yudao.module.product.enums.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertMap;

@FeignClient(name = ApiConstants.NAME) // TODO 芋艿：fallbackFactory =
@Tag(name = "RPC 服务 - 商品 SKU")
public interface ProductSkuApi {

    String PREFIX = ApiConstants.PREFIX + "/sku";

    @GetMapping(PREFIX + "/get")
    @Operation(summary = "查询 SKU 信息")
    @Parameter(name = "id", description = "SKU 编号", required = true, example = "1024")
    CommonResult<ProductSkuRespDTO> getSku(@RequestParam("id") Long id);

    @GetMapping(PREFIX + "/list")
    @Operation(summary = "批量查询 SKU 信息")
    @Parameter(name = "ids", description = "SKU 编号列表", required = true, example = "1024,2048")
    CommonResult<List<ProductSkuRespDTO>> getSkuList(@RequestParam("ids") Collection<Long> ids);

    /**
     * 批量查询 SKU MAP
     *
     * @param ids SKU 编号列表
     * @return SKU MAP
     */
    default Map<Long, ProductSkuRespDTO> getSkuMap(Collection<Long> ids) {
        return convertMap(getSkuList(ids).getCheckedData(), ProductSkuRespDTO::getId);
    }

    @GetMapping(PREFIX + "/list-by-spu-id")
    @Operation(summary = "批量查询 SKU 信息")
    @Parameter(name = "spuIds", description = "SPU 编号列表", required = true, example = "1024,2048")
    CommonResult<List<ProductSkuRespDTO>> getSkuListBySpuId(@RequestParam("spuIds") Collection<Long> spuIds);

    @PostMapping(PREFIX + "/update-stock")
    @Operation(summary = "更新 SKU 库存（增加 or 减少）")
    CommonResult<Boolean> updateSkuStock(@RequestBody @Valid ProductSkuUpdateStockReqDTO updateStockReqDTO);

}
