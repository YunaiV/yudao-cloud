package cn.iocoder.yudao.module.store.service;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;
import cn.iocoder.yudao.module.store.dal.dataobject.BookInfoDO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Import(StoreServiceImpl.class)
class StoreServiceImplTest extends BaseDbUnitTest {

    @Resource
    private StoreService storeService ;

    @Test
    void getAllBooks() {
        PageResult<BookInfoDO> result = storeService.getAllBooks(10) ;
        assertEquals(10, result.getList().size());
    }
}