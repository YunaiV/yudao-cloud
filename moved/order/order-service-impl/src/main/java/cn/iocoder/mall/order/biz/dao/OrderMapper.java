package cn.iocoder.mall.order.biz.dao;

import cn.iocoder.mall.order.api.dto.OrderQueryDTO;
import cn.iocoder.mall.order.biz.dataobject.OrderDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单 mapper
 *
 * @author Sin
 * @time 2019-03-16 15:09
 */
@Repository
public interface OrderMapper extends BaseMapper<OrderDO> {

    /**
     * 更新 - 根据 id 更新
     *
     * @param orderDO
     * @return
     */
    int updateById(OrderDO orderDO);

    int updateByIdAndStatus(@Param("id") Integer id,
                            @Param("status") Integer status,
                            @Param("updateObj") OrderDO updateObj);

    /**
     * 查询 - 根据id 查询
     *
     * @param id
     * @return
     */
    OrderDO selectById(
            @Param("id") Integer id
    );

    /**
     * 查询 - 后台分页page
     *
     * @param orderQueryDTO
     * @return
     */
    int selectPageCount(OrderQueryDTO orderQueryDTO);

    /**
     * 查询 - 后台分页page
     *
     * @param orderQueryDTO
     * @return
     */
    List<OrderDO> selectPage(OrderQueryDTO orderQueryDTO);
}
