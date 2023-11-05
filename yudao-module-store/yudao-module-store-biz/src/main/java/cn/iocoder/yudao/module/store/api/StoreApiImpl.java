package cn.iocoder.yudao.module.store.api;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.store.api.dto.BookInfoCreateReqDTO;
import cn.iocoder.yudao.module.store.service.StoreService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


@RestController
@Validated
public class StoreApiImpl implements StoreApi {
    @Resource
    private StoreService storeService;

    @Override
    public CommonResult<Long> addBook(BookInfoCreateReqDTO createReqDTO) {
        return success(storeService.addBook(createReqDTO)) ;
    }
}
