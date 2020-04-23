package cn.iocoder.mall.system.rpc.convert.admn;

import cn.iocoder.mall.system.biz.bo.admin.AdminBO;
import cn.iocoder.mall.system.rpc.response.admin.AdminResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminCovert {

    AdminCovert INSTANCE = Mappers.getMapper(AdminCovert.class);

    AdminResponse convert(AdminBO bean);

}
