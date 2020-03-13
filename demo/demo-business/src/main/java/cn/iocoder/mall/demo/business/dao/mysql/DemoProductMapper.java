package cn.iocoder.mall.demo.business.dao.mysql;

import cn.iocoder.mall.demo.business.dataobject.product.DemoProductDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoProductMapper extends BaseMapper<DemoProductDO> {

    int updateQuantityReduce(@Param("id") Integer id,
                             @Param("quantity") Integer quantity);

}
