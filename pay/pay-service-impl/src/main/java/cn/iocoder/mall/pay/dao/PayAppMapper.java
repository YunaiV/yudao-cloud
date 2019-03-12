package cn.iocoder.mall.pay.dao;

import cn.iocoder.mall.pay.dataobject.PayAppDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PayAppMapper {

    PayAppDO selectById(@Param("id") String id);

}