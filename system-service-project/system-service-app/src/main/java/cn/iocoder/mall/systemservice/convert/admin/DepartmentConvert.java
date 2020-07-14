package cn.iocoder.mall.systemservice.convert.admin;

import cn.iocoder.mall.systemservice.dal.mysql.dataobject.admin.DepartmentDO;
import cn.iocoder.mall.systemservice.rpc.admin.dto.DepartmentCreateDTO;
import cn.iocoder.mall.systemservice.rpc.admin.dto.DepartmentUpdateDTO;
import cn.iocoder.mall.systemservice.rpc.admin.vo.DepartmentVO;
import cn.iocoder.mall.systemservice.service.admin.bo.DepartmentBO;
import cn.iocoder.mall.systemservice.service.admin.bo.DepartmentCreateBO;
import cn.iocoder.mall.systemservice.service.admin.bo.DepartmentUpdateBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DepartmentConvert {

    DepartmentConvert INSTANCE = Mappers.getMapper(DepartmentConvert.class);

    DepartmentDO convert(DepartmentCreateBO bean);

    DepartmentBO convert(DepartmentDO bean);

    DepartmentDO convert(DepartmentUpdateBO bean);

    DepartmentUpdateBO convert(DepartmentUpdateDTO bean);

    DepartmentCreateBO convert(DepartmentCreateDTO bean);

    DepartmentVO convert(DepartmentBO bean);

    List<DepartmentBO> convertList(List<DepartmentDO> list);
    List<DepartmentVO> convertList02(List<DepartmentBO> list);

}
