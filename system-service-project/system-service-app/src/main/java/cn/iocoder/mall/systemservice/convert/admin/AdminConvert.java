package cn.iocoder.mall.systemservice.convert.admin;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.systemservice.dal.mysql.dataobject.admin.AdminDO;
import cn.iocoder.mall.systemservice.rpc.admin.dto.AdminCreateDTO;
import cn.iocoder.mall.systemservice.rpc.admin.dto.AdminPageDTO;
import cn.iocoder.mall.systemservice.rpc.admin.dto.AdminUpdateDTO;
import cn.iocoder.mall.systemservice.rpc.admin.vo.AdminVO;
import cn.iocoder.mall.systemservice.service.admin.bo.AdminBO;
import cn.iocoder.mall.systemservice.service.admin.bo.AdminCreateBO;
import cn.iocoder.mall.systemservice.service.admin.bo.AdminPageBO;
import cn.iocoder.mall.systemservice.service.admin.bo.AdminUpdateBO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminConvert {

    AdminConvert INSTANCE = Mappers.getMapper(AdminConvert.class);

    AdminBO convert(AdminDO bean);

    AdminVO convert(AdminBO bean);

    AdminDO convert(AdminCreateBO bean);

    AdminDO convert(AdminUpdateBO bean);

    AdminCreateBO convert(AdminCreateDTO bean);

    AdminUpdateBO convert(AdminUpdateDTO bean);

    @Mapping(source = "records", target = "list")
    PageResult<AdminBO> convertPage(IPage<AdminDO> page);

    AdminPageBO convert(AdminPageDTO page);

    PageResult<AdminVO> convert(PageResult<AdminBO> adminPage);

}
