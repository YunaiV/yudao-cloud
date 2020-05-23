package cn.iocoder.mall.system.biz.dao.errorcode;

import cn.iocoder.mall.mybatis.query.QueryWrapperX;
import cn.iocoder.mall.system.biz.dataobject.authorization.RoleDO;
import cn.iocoder.mall.system.biz.dataobject.authorization.RoleResourceDO;
import cn.iocoder.mall.system.biz.dataobject.errorcode.ErrorCodeDO;
import cn.iocoder.mall.system.biz.dto.authorization.RolePageDTO;
import cn.iocoder.mall.system.biz.dto.errorcode.ErrorCodeDTO;
import cn.iocoder.mall.system.biz.dto.errorcode.ErrorCodePageDTO;
import cn.iocoder.mall.system.biz.enums.SystemErrorCodeEnum;
import cn.iocoder.mall.system.biz.enums.errorcode.ErrorCodeTypeEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * @author ding
 */
@Repository
public interface ErrorCodeMapper extends BaseMapper<ErrorCodeDO> {

    default ErrorCodeDO selectByCode(Integer code){
        return selectOne(new QueryWrapperX<ErrorCodeDO>().eqIfPresent("code", code));
    }

    default IPage<ErrorCodeDO> selectPage(ErrorCodePageDTO errorCodePageDTO) {
        return selectPage(new Page<>(errorCodePageDTO.getPageNo(), errorCodePageDTO.getPageSize()),
                new QueryWrapperX<ErrorCodeDO>().likeIfPresent("message", errorCodePageDTO.getMessage()));
    }

    default List<ErrorCodeDO> selectListByIds(Collection<Integer> ids) {
        return selectList(new QueryWrapperX<ErrorCodeDO>().inIfPresent("id", ids));
    }

    default ErrorCodeDO selectByMessage(String message) {
        return selectOne(new QueryWrapperX<ErrorCodeDO>().eqIfPresent("message", message));
    }

    default List<ErrorCodeDO> selectByGroup(Integer group) {
        return selectList(new QueryWrapperX<ErrorCodeDO>().eqIfPresent("group", group));
    }


    default int deleteSyStemErrorCode(int group){
        return delete(new QueryWrapper<ErrorCodeDO>().eq("group", group));
    }
}
