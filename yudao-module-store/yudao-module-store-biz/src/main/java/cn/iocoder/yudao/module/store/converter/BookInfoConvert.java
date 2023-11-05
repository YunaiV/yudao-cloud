package cn.iocoder.yudao.module.store.converter;

import cn.iocoder.yudao.module.store.api.dto.BookInfoCreateReqDTO;
import cn.iocoder.yudao.module.store.dal.dataobject.BookInfoDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookInfoConvert {
    BookInfoConvert INSTANCE = Mappers.getMapper(BookInfoConvert.class);

    BookInfoDO convert(BookInfoCreateReqDTO createReqDTO);

}
