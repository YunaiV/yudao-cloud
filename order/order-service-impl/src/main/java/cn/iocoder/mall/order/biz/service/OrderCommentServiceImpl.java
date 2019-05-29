package cn.iocoder.mall.order.biz.service;

import cn.iocoder.mall.order.api.OrderCommentService;
import cn.iocoder.mall.order.api.bo.OrderCommentCreateBO;
import cn.iocoder.mall.order.api.bo.OrderCommentInfoAndMerchantReplyBO;
import cn.iocoder.mall.order.api.bo.OrderCommentPageBO;
import cn.iocoder.mall.order.api.dto.OrderCommentCreateDTO;
import cn.iocoder.mall.order.api.dto.OrderCommentPageDTO;
import cn.iocoder.mall.order.biz.convert.OrderCommentConvert;
import cn.iocoder.mall.order.biz.dao.OrderCommentMapper;
import cn.iocoder.mall.order.biz.dao.OrderCommentReplayMapper;
import cn.iocoder.mall.order.biz.dataobject.OrderCommentDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 *
 * 订单评论 service impl
 *
 * @author wtz
 * @time 2019
 */
@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.OrderCommentService.version}")
public class OrderCommentServiceImpl implements OrderCommentService {

    @Autowired
    private OrderCommentMapper orderCommentMapper;


    @Autowired
    private OrderCommentService orderCommentService;

    @Override
    public OrderCommentCreateBO createOrderComment(OrderCommentCreateDTO orderCommentCreateDTO) {
        //首先判断订单状态是否处于待评价状态

        //接下来就是入库
        OrderCommentDO orderCommentDO=OrderCommentConvert.INSTANCE.convert(orderCommentCreateDTO);
        orderCommentDO.setCreateTime(new Date());
        orderCommentDO.setUpdateTime(new Date());
        orderCommentMapper.insert(orderCommentDO);
        return OrderCommentConvert.INSTANCE.convert(orderCommentDO);
    }

    @Override
    public OrderCommentPageBO getOrderCommentPage(OrderCommentPageDTO orderCommentPageDTO) {
        OrderCommentPageBO orderCommentPageBO=new OrderCommentPageBO();
        //分页内容
        List<OrderCommentDO> orderCommentDOList=orderCommentMapper.selectCommentPage(orderCommentPageDTO);
        //查询商家的回复
        //总数
        int totalCount=orderCommentMapper.selectCommentTotalCountByProductSkuId(orderCommentPageDTO.getProductSkuId());
        orderCommentPageBO.setOrderCommentItems(OrderCommentConvert.INSTANCE.convert(orderCommentDOList));
        orderCommentPageBO.setTotal(totalCount);
        return orderCommentPageBO;
    }

    @Override
    public OrderCommentInfoAndMerchantReplyBO getOrderCommentInfo(Integer commentId, Integer userType) {
        return null;
    }

    @Override
    public Boolean OrderCommentTimeOutProductCommentTask() {
        return null;
    }
}
