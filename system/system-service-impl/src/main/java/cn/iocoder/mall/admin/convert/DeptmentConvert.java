package cn.iocoder.mall.admin.convert;

import cn.iocoder.mall.admin.api.bo.deptment.DeptmentBO;
import cn.iocoder.mall.admin.api.dto.depetment.DeptmentAddDTO;
import cn.iocoder.mall.admin.dataobject.DeptmentDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-06-14
 * @time: 20:06
 */
@Mapper
public interface DeptmentConvert {

    DeptmentConvert INSTANCE = Mappers.getMapper(DeptmentConvert.class);

    @Mappings({})
    DeptmentDO convert(DeptmentAddDTO deptmentAddDTO);

    @Mappings({})
    DeptmentBO convert(DeptmentDO deptmentDO);

}
