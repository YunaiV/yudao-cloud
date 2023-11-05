package cn.iocoder.yudao.module.store.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.store.api.dto.BookInfoCreateReqDTO;
import cn.iocoder.yudao.module.store.api.dto.BookInfoDto;
import cn.iocoder.yudao.module.store.dal.dataobject.BookInfoDO;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public interface StoreService {
    Long addBook(BookInfoCreateReqDTO createReqVO);

    PageResult<BookInfoDto> getBookInfoByIsbn(Long isbn);
    PageResult<BookInfoDO> getAllBooks(Integer count);
    PageResult<String> modifyBookinfo(BookInfoDto bookInfoDto);
    PageResult<String> removeBookByIsbn(Long isbn);
}
