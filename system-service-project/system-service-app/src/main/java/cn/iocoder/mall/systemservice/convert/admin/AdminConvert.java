package cn.iocoder.mall.systemservice.convert.admin;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.systemservice.dal.mysql.dataobject.admin.AdminDO;
import cn.iocoder.mall.systemservice.rpc.admin.vo.AdminVO;
import cn.iocoder.mall.systemservice.service.admin.bo.AdminBO;
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

    AdminVO convert(AdminBO adminBO);

}
