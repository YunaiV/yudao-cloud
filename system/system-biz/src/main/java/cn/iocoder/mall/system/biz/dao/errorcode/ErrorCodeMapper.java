package cn.iocoder.mall.system.biz.dao.errorcode;

import cn.iocoder.mall.mybatis.query.QueryWrapperX;
import cn.iocoder.mall.system.biz.dataobject.errorcode.ErrorCodeDO;
import cn.iocoder.mall.system.biz.enums.SystemErrorCodeEnum;
import cn.iocoder.mall.system.biz.enums.errorcode.ErrorCodeTypeEnum;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @author ding
 */
@Repository
public interface ErrorCodeMapper extends BaseMapper<ErrorCodeDO> {

    default ErrorCodeDO selectByCode(Integer code){
        //从db查询
        ErrorCodeDO errorCodeDO = selectOne(new QueryWrapperX<ErrorCodeDO>().eqIfPresent("code", code));
        if (null == errorCodeDO){
            //从enum查询
            for (SystemErrorCodeEnum item : SystemErrorCodeEnum.values()) {
                if(code.equals(item.getCode())){
                    return new ErrorCodeDO().setCode(item.getCode()).
                            setId(0).setType(ErrorCodeTypeEnum.SYSTEM.getType());
                }
            }
        }
        return null;

    }
}
