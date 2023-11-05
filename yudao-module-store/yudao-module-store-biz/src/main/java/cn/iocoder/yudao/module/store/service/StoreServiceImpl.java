package cn.iocoder.yudao.module.store.service;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.store.api.dto.BookInfoCreateReqDTO;
import cn.iocoder.yudao.module.store.api.dto.BookInfoDto;
import cn.iocoder.yudao.module.store.converter.BookInfoConvert;
import cn.iocoder.yudao.module.store.dal.dataobject.BookInfoDO;
import cn.iocoder.yudao.module.store.dal.mysql.BookInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;

@Service
@Validated
public class StoreServiceImpl implements StoreService {
    @Resource
    private BookInfoMapper bookInfoMapper ;

    @Override
    public Long addBook(BookInfoCreateReqDTO createReqVO) {
        BookInfoDO bookInfo = BookInfoConvert.INSTANCE.convert(createReqVO) ;
        bookInfoMapper.insert(bookInfo) ;
        return bookInfo.getId() ;
    }

    @Override
    public PageResult<BookInfoDto> getBookInfoByIsbn(Long isbn) {
        return null;
    }

    @Override
    public PageResult<BookInfoDO> getAllBooks(Integer count) {
        return bookInfoMapper.selectBookList(count) ;
    }

    @Override
    public PageResult<String> modifyBookinfo(BookInfoDto bookInfoDto) {
        return null;
    }

    @Override
    public PageResult<String> removeBookByIsbn(Long isbn) {
        return null;
    }


}
