package cn.iocoder.mall.user.application.convert;

import cn.iocoder.mall.user.api.dto.UserAddressAddDTO;
import cn.iocoder.mall.user.api.dto.UserAddressUpdateDTO;
import cn.iocoder.mall.user.application.po.UserAddressAddPO;
import cn.iocoder.mall.user.application.po.UserAddressUpdatePO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-24T11:39:10+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class UserAddressConvertImpl implements UserAddressConvert {

    @Override
    public UserAddressAddDTO convert(UserAddressAddPO userAddressAddPO) {
        if ( userAddressAddPO == null ) {
            return null;
        }

        UserAddressAddDTO userAddressAddDTO = new UserAddressAddDTO();

        userAddressAddDTO.setAreaNo( userAddressAddPO.getAreaNo() );
        userAddressAddDTO.setName( userAddressAddPO.getName() );
        userAddressAddDTO.setMobile( userAddressAddPO.getMobile() );
        userAddressAddDTO.setAddress( userAddressAddPO.getAddress() );
        userAddressAddDTO.setHasDefault( userAddressAddPO.getHasDefault() );

        return userAddressAddDTO;
    }

    @Override
    public UserAddressUpdateDTO convert(UserAddressUpdatePO userAddressUpdatePO) {
        if ( userAddressUpdatePO == null ) {
            return null;
        }

        UserAddressUpdateDTO userAddressUpdateDTO = new UserAddressUpdateDTO();

        userAddressUpdateDTO.setId( userAddressUpdatePO.getId() );
        userAddressUpdateDTO.setAreaNo( userAddressUpdatePO.getAreaNo() );
        userAddressUpdateDTO.setName( userAddressUpdatePO.getName() );
        userAddressUpdateDTO.setMobile( userAddressUpdatePO.getMobile() );
        userAddressUpdateDTO.setAddress( userAddressUpdatePO.getAddress() );
        userAddressUpdateDTO.setHasDefault( userAddressUpdatePO.getHasDefault() );

        return userAddressUpdateDTO;
    }
}
