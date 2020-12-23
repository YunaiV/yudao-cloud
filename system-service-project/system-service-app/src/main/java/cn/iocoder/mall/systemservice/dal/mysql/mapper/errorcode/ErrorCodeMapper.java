package cn.iocoder.mall.systemservice.dal.mysql.mapper.errorcode;

import cn.iocoder.mall.mybatis.core.query.QueryWrapperX;
import cn.iocoder.mall.systemservice.dal.mysql.dataobject.errorcode.ErrorCodeDO;
import cn.iocoder.mall.systemservice.service.errorcode.bo.ErrorCodePageBO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Repository
public interface ErrorCodeMapper extends BaseMapper<ErrorCodeDO> {

    default IPage<ErrorCodeDO> selectPage(ErrorCodePageBO pageBO) {
        return selectPage(new Page<>(pageBO.getPageNo(), pageBO.getPageSize()),
            new QueryWrapperX<ErrorCodeDO>().likeIfPresent("`group`", pageBO.getGroup())
                .eqIfPresent("code", pageBO.getCode()).likeIfPresent("message", pageBO.getMessage()));
    }

    default List<ErrorCodeDO> selectListByCodes(Collection<Integer> codes) {
        return selectList(new QueryWrapper<ErrorCodeDO>().in("code", codes));
    }

    default ErrorCodeDO selectByCode(Integer code) {
        return selectOne(new QueryWrapper<ErrorCodeDO>().eq("code", code));
    }

    default List<ErrorCodeDO> selectListByGroup(String group, Date minUpdateTime) {
        return selectList(new QueryWrapperX<ErrorCodeDO>().eq("`group`", group)
            .gtIfPresent("update_time", minUpdateTime));
    }

}
