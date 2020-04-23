package cn.iocoder.mall.system.biz.convert.admin;

import cn.iocoder.mall.system.biz.bo.admin.AdminBO;
import cn.iocoder.mall.system.biz.dataobject.admin.AdminDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminConvert {

    AdminConvert INSTANCE = Mappers.getMapper(AdminConvert.class);

    AdminBO convert(AdminDO bean);

}
