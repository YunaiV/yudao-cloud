package cn.iocoder.mall.user.convert;

import cn.iocoder.mall.user.dataobject.UserDO;
import cn.iocoder.mall.user.service.api.bo.UserBO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-03-05T10:35:05+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_181 (Oracle Corporation)"
)
public class UserConvertImpl implements UserConvert {

    @Override
    public UserBO convert(UserDO userDO) {
        if ( userDO == null ) {
            return null;
        }

        UserBO userBO = new UserBO();

        userBO.setMobile( userDO.getMobile() );

        return userBO;
    }
}
