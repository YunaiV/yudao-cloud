package cn.iocoder.mall.demo.business.dao;

import cn.iocoder.mall.demo.business.dataobject.order.DemoOrderDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoOrderMapper extends BaseMapper<DemoOrderDO> {

}
