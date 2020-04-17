package cn.iocoder.mall.admin.convert;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.system.api.bo.deptment.DeptmentBO;
import cn.iocoder.mall.system.api.dto.depetment.DeptmentAddDTO;
import cn.iocoder.mall.system.api.dto.depetment.DeptmentUpdateDTO;
import cn.iocoder.mall.admin.dataobject.DeptmentDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

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

    @Mappings({@Mapping(source = "records", target = "list")})
    PageResult<DeptmentBO> convert(IPage<DeptmentDO> list);

    @Mappings({})
    List<DeptmentBO> convert(List<DeptmentDO> list);

    @Mappings({})
    DeptmentDO convert(DeptmentUpdateDTO deptmentUpdateDTO);
}
