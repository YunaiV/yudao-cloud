package cn.iocoder.yudao.module.store.api;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.store.api.dto.BookInfoCreateReqDTO;
import cn.iocoder.yudao.module.store.enums.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;


@Tag(name = "Store service")
public interface StoreApi {
    String PREFIX = ApiConstants.PREFIX + "/store";

    @PostMapping(PREFIX + "/add")
    @Operation(summary = "add a book")
    CommonResult<Long> addBook(@RequestBody @Valid BookInfoCreateReqDTO createReqDTO);

}
