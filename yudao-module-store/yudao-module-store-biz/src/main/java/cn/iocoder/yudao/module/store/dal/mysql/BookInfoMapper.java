package cn.iocoder.yudao.module.store.dal.mysql;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.store.dal.dataobject.BookInfoDO;
import org.apache.ibatis.annotations.Mapper ;
import java.util.List;

@Mapper
public interface BookInfoMapper extends BaseMapperX<BookInfoDO> {

    default BookInfoDO getByIsbn(Long isbn) {
        return selectOne(BookInfoDO::getIsbn, isbn) ;
    }
    default PageResult<BookInfoDO> selectBookList(Integer count) {
        // 构建分页查询条件
        return selectPage(new PageParam().setPageSize(count), new LambdaQueryWrapperX<BookInfoDO>()
                .orderByDesc(BookInfoDO::getCreateTime)
        );
    }

}
