package cn.iocoder.mall.systemservice.convert.systemlog;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.systemservice.dal.mysql.dataobject.systemlog.SystemAccessLogDO;
import cn.iocoder.mall.systemservice.rpc.systemlog.dto.SystemAccessLogCreateDTO;
import cn.iocoder.mall.systemservice.rpc.systemlog.vo.SystemAccessLogPageDTO;
import cn.iocoder.mall.systemservice.rpc.systemlog.vo.SystemAccessLogVO;
import cn.iocoder.mall.systemservice.service.systemlog.bo.SystemAccessLogBO;
import cn.iocoder.mall.systemservice.service.systemlog.bo.SystemAccessLogCreateBO;
import cn.iocoder.mall.systemservice.service.systemlog.bo.SystemAccessLogPageBO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SystemAccessLogConvert {

    SystemAccessLogConvert INSTANCE = Mappers.getMapper(SystemAccessLogConvert.class);

    SystemAccessLogDO convert(SystemAccessLogCreateBO bean);

    SystemAccessLogCreateBO convert(SystemAccessLogCreateDTO bean);

    @Mapping(source = "records", target = "list")
    PageResult<SystemAccessLogBO> convertPage(IPage<SystemAccessLogDO> page);

    SystemAccessLogPageBO convert(SystemAccessLogPageDTO bean);

    PageResult<SystemAccessLogVO> convertPage(PageResult<SystemAccessLogBO> page);

}
