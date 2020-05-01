package cn.iocoder.mall.system.biz.convert.admin;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.system.biz.bo.admin.AdminBO;
import cn.iocoder.mall.system.biz.dataobject.admin.AdminDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminConvert {

    AdminConvert INSTANCE = Mappers.getMapper(AdminConvert.class);

    AdminBO convert(AdminDO bean);

    @Mapping(source = "records", target = "list")
    PageResult<AdminBO> convertPage(IPage<AdminDO> bean);

}
