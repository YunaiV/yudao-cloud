package cn.iocoder.mall.admin.application.convert;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.admin.api.bo.deptment.DeptmentBO;
import cn.iocoder.mall.admin.application.vo.deptment.DeptmentVO;
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
 * @date: 2019-06-22
 * @time: 00:23
 */
@Mapper
public interface DeptmentConvert {

    DeptmentConvert INSTANCE = Mappers.getMapper(DeptmentConvert.class);

    @Mappings({@Mapping(source = "list", target = "list")})
    PageResult<DeptmentVO> convert(PageResult<DeptmentBO> pageResult);

    @Mappings({})
    List<DeptmentVO> convert(List<DeptmentBO> list);
}
