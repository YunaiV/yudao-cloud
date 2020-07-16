package cn.iocoder.mall.systemservice.convert.systemlog;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.systemservice.dal.mysql.dataobject.systemlog.SystemExceptionLogDO;
import cn.iocoder.mall.systemservice.rpc.systemlog.dto.SystemExceptionLogCreateDTO;
import cn.iocoder.mall.systemservice.rpc.systemlog.dto.SystemExceptionLogPageDTO;
import cn.iocoder.mall.systemservice.rpc.systemlog.vo.SystemExceptionLogVO;
import cn.iocoder.mall.systemservice.service.systemlog.bo.SystemExceptionLogBO;
import cn.iocoder.mall.systemservice.service.systemlog.bo.SystemExceptionLogCreateBO;
import cn.iocoder.mall.systemservice.service.systemlog.bo.SystemExceptionLogPageBO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SystemExceptionLogConvert {

    SystemExceptionLogConvert INSTANCE = Mappers.getMapper(SystemExceptionLogConvert.class);

    SystemExceptionLogDO convert(SystemExceptionLogCreateBO bean);

    SystemExceptionLogCreateBO convert(SystemExceptionLogCreateDTO bean);

    @Mapping(source = "records", target = "list")
    PageResult<SystemExceptionLogBO> convertPage(IPage<SystemExceptionLogDO> page);

    SystemExceptionLogBO convert(SystemExceptionLogDO bean);

    SystemExceptionLogVO convert(SystemExceptionLogBO bean);

    SystemExceptionLogPageBO convert(SystemExceptionLogPageDTO bean);

    PageResult<SystemExceptionLogVO> convertPage(PageResult<SystemExceptionLogBO> page);

}
