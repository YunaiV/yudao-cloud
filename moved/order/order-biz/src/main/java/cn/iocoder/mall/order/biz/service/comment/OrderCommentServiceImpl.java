package cn.iocoder.mall.order.biz.service.comment;

import cn.iocoder.common.framework.util.CollectionUtil;
import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.order.biz.bo.comment.OrderCommentInfoBO;
import cn.iocoder.mall.order.biz.bo.comment.OrderCommentPageBO;
import cn.iocoder.mall.order.biz.bo.comment.OrderCommentStateInfoPageBO;
import cn.iocoder.mall.order.biz.bo.comment.OrderCommentTimeOutBO;
import cn.iocoder.mall.order.biz.convert.comment.OrderCommentConvert;
import cn.iocoder.mall.order.biz.dataobject.comment.OrderCommentDO;
import cn.iocoder.mall.order.biz.dto.comment.OrderCommentAddDTO;
import cn.iocoder.mall.order.biz.dto.comment.OrderCommentPageDTO;
import cn.iocoder.mall.order.biz.dto.comment.OrderCommentStateInfoPageDTO;
import cn.iocoder.mall.order.biz.dto.comment.OrderCommentTimeOutPageDTO;
import java.util.Collections;
import java.util.List;
import javax.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * OrderCommentServiceImpl
 *
 * @author xiaofeng
 * @version 1.0
 * @date 2020/05/17 15:32
 */
@Service
public class OrderCommentServiceImpl implements OrderCommentService {

    private final MongoTemplate mongoTemplate;

    public OrderCommentServiceImpl(final MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Boolean addOrderComment(
            @Valid OrderCommentAddDTO orderCommentAddDTO) {

        OrderCommentDO orderCommentDO = mongoTemplate
                .save(OrderCommentConvert.INSTANCE.convert(orderCommentAddDTO));
        return null != orderCommentDO ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public PageResult<OrderCommentPageBO> page(
            OrderCommentPageDTO orderCommentPageDTO) {
        Query query = new Query();
        query.with(Sort.by(Direction.ASC, "_id"));
        List<OrderCommentDO> orderCommentList = Collections.EMPTY_LIST;
        if (orderCommentPageDTO.getPageNo() == 1) {
            query.limit(orderCommentPageDTO.getPageSize());
        } else {
            final int offset = orderCommentPageDTO.getOffset();
            query.limit(offset);
            List<OrderCommentDO> list = mongoTemplate.find(query, OrderCommentDO.class);
            if (!CollectionUtil.isEmpty(list)) {
                // 获取最后一条记录
                OrderCommentDO orderCommentDO = list.get(orderCommentList.size() - 1);
                final Integer orderCommentId = orderCommentDO.getId();
                // 从上一页最后一条开始查
                query.addCriteria(Criteria.where("_id").gt(orderCommentId));
                // 重新赋值
                query.limit(orderCommentPageDTO.getPageSize());
            }
        }
        orderCommentList = mongoTemplate.find(query, OrderCommentDO.class);

        PageResult<OrderCommentPageBO> pageResult = new PageResult<>();
        pageResult.setList(OrderCommentConvert.INSTANCE.convert(orderCommentList));
        return pageResult;
    }

    @Override
    public OrderCommentInfoBO getOrderCommentInfo(
            Integer commentId) {
        OrderCommentDO orderCommentDO = mongoTemplate
                .findOne(new Query(Criteria.where("_id").is(commentId)), OrderCommentDO.class);
        return OrderCommentConvert.INSTANCE.convert(orderCommentDO);
    }

    @Override
    public OrderCommentStateInfoPageBO getOrderCommentStateInfoPage(
            OrderCommentStateInfoPageDTO orderCommentStateInfoPageDTO) {
        return null;
    }

    @Override
    public List<OrderCommentTimeOutBO> getOrderCommentTimeOutPage(
            OrderCommentTimeOutPageDTO orderCommentTimeOutPageDTO) {
        return null;
    }

    @Override
    public void updateBatchOrderCommentState(
            List<OrderCommentTimeOutBO> orderCommentTimeOutBOList) {

    }
}
